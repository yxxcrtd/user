package com.chinaedustar.map;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Transaction;

import com.chinaedustar.common.GetMD5Con;
import com.chinaedustar.hbm.BaseHibernateDAO;
import com.chinaedustar.hbm.TUser;

/**
 * 
 */
public class GenralUserMap {
	// UserTicket -> Ticket map
	public static Hashtable<String, TicketInfo> ACCESSSESSIONMAP = new Hashtable<String, TicketInfo>();
	
	public static final Log log = LogFactory.getLog(GenralUserMap.class);
	
	/**
	 * 从 Cookie 中的 uniqueid 得到当前用户
	 * @param request
	 * @return
	 * 		0 : 未登录　或　已超时
	 * 		N : 用户的在数据库 T_User 中的 ID
	 */
	public TUser GetUserByUserTicket(String sUserTicket, String sLoginIp) {
		if (sUserTicket.equals("")) {
			return null;
		} else {
			TicketInfo oTicketInfo = ACCESSSESSIONMAP.get(sUserTicket);
			
			if (oTicketInfo == null) {
				return null;
			} else {
				if (oTicketInfo.getIp().equals(sLoginIp)) {
					oTicketInfo.setCreate(new Date());
					
					return new BaseHibernateDAO().GetUserById(oTicketInfo.getId());
				} else {
					return null;
				}
			}
		}
	}
	
	/**
	 * 从服务器端删除 Cookie 中所包含的用户信息
	 * @param request
	 */
	public void RemoveUserByUserTicket(String sUserTicket) {
		if (!sUserTicket.equals("")) {
			ACCESSSESSIONMAP.remove(sUserTicket);
		}
	}
	
	/**
	 * 注册用户登录信息 
	 * @param request
	 * @param response
	 * @param sUserName
	 * @param sUserPsd
	 * @return
	 * 		-2 : 用户已禁用
	 * 		-1 : 用户不存在
	 * 		 0 : 用户口令不正确
	 * 		 1 : 注册成功
	 */
	public synchronized int RegUserLogin(HttpServletRequest request, HttpServletResponse response, String sUserTicket, String sUserPsd) {
		int iRetVal = 0;
		
		TUser oTUser = new BaseHibernateDAO().GetUserByUserId(sUserTicket);
		
		if (oTUser == null) {
			iRetVal = -1;
		} else if(oTUser.getActive().intValue() == 0) {
			iRetVal = -2;
		} else if (oTUser.getLoginPw().trim().equals(sUserPsd) || oTUser.getLoginPw().equals(GetMD5Con.GetMD5Str(sUserPsd))) {
			String strCurDate = "";
			Calendar oCurSysDate = null;
			SimpleDateFormat strDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			
			while (true){
				oCurSysDate = Calendar.getInstance();
				
				strCurDate = strDateFormat.format(oCurSysDate.getTime()) + (int)((99 - 10) * Math.random() + 10);
				
				if (ACCESSSESSIONMAP.get(strCurDate) == null) break;
			}
			
			// 保存到 Hashtable 中
			TicketInfo oTicketInfo = new TicketInfo();
			
			oTicketInfo.setCreate(oCurSysDate.getTime());
			oTicketInfo.setId(oTUser.getId());
			oTicketInfo.setIp(request.getRemoteAddr());
			
			ACCESSSESSIONMAP.put(strCurDate, oTicketInfo);
			
			// 更新 Cookie ，添加认证信息
			Cookie cNewCookie = new Cookie("UserTicket", strCurDate);
			cNewCookie.setMaxAge(-1);
			
			String sDomain = GetDomain(request.getServerName());
			if (!sDomain.equals("")) cNewCookie.setDomain(sDomain);
			
			cNewCookie.setPath("/");
			response.addCookie(cNewCookie);
			
			iRetVal = 1;
		}
		
		return iRetVal;
	}
	
	private String GetDomain(String sDomain) {
		String sResStr = "";
		
		if (sDomain.contains(".")) {
			String[] sTempVar = sDomain.split("\\.");
			boolean bIpNum = true;
			
			for (int i = 0; i < sTempVar.length; i ++) {
				try{
					Integer.parseInt(sTempVar[i]);
				} catch(NumberFormatException e) {
					bIpNum = false;
					break;
				}
			}
			
			if (bIpNum) {
				sResStr = "";
			} else {
				switch (sTempVar.length) {
				case 2:
					sResStr = sDomain;
					
					break;
					
				default:
					sResStr = sDomain.substring(sDomain.indexOf(".") + 1);
					
					break;
				}
			}
		} else {
			sResStr = "";
		}
			
		return sResStr;
	}
	
	/**
	 * 注册新用户
	 * @param oNewUser
	 * @return
	 * 		-9 : 保存时出现错误
	 * 		 0 : 用户登录名重复
	 * 		 1 : 注册成功
	 */
	public int RegNewUser(TUser oNewUser) {
		int iRetVal = 0;
		
		BaseHibernateDAO ODao = new BaseHibernateDAO();
		
		if (ODao.GetUserByUserId(oNewUser.getLoginId()) == null) {
			oNewUser.setLoginPw(GetMD5Con.GetMD5Str(oNewUser.getLoginPw()));
			oNewUser.setAnswer(GetMD5Con.GetMD5Str(oNewUser.getAnswer()));
			
			try {
				Transaction oTraSes = ODao.getSession().beginTransaction();
				ODao.getSession().saveOrUpdate(oNewUser);
				oTraSes.commit();
			} catch (RuntimeException re) {
	            return -9;
	        }
			
			iRetVal = 1;
		} else {
			iRetVal = 0;
		}
		
		return iRetVal;
	}
	
	/**
	 * 修改用户信息
	 * @param oSelUser
	 * @return
	 * 		-9 : 保存时出现错误
	 * 		 0 : 无法从服务器获取用户信息
	 * 		 1 : 保存成功
	 */
	public int ModifyUserInfo(TUser oSelUser) {
		int iRetVal = 0;
		
		BaseHibernateDAO ODao = new BaseHibernateDAO();
		
		TUser oTUser = ODao.GetUserByUserId(oSelUser.getLoginId());
		
		if (oTUser == null) {
			iRetVal = 0;
		} else {
			try {
				Transaction oTraSes = ODao.getSession().beginTransaction();
				ODao.getSession().saveOrUpdate(oSelUser);
				oTraSes.commit();
			} catch (RuntimeException re) {
	            return -9;
	        }
			
			iRetVal = 1;
		}
		
		return iRetVal;
	}
	
	/**
	 * 根据用户输入问题和答案，重置口令
	 * @param sUserName
	 * @param sQuestion
	 * @param sAnswer
	 * @return
	 * 		-2 : 用户已禁用
	 * 		-1 : 用户不存在
	 * 		 0 : 问题或答案错误
	 * 		 1 : 验证正确
	 */
	public int CheckUserAnswer(HttpServletRequest request, String sUserId, String sAnswer) {
		int iRetVal = 0;
		
		TUser oTUser = new BaseHibernateDAO().GetUserByUserId(sUserId);
		
		if (oTUser == null) {
			iRetVal = -1;
		} else if(oTUser.getActive().equals(0)) {
			iRetVal = -2;
		} else if (oTUser.getAnswer().equals(sAnswer) || oTUser.getAnswer().equals(GetMD5Con.GetMD5Str(sAnswer))) {
			iRetVal = 1;
			
			request.setAttribute("user", oTUser.getLoginId());
		}
		
		return iRetVal;
	}
	
	/**
	 * 修改用户口令
	 * @param sUserId
	 * @param sNewPsd
	 * @return
	 * 		-9 : 修改口令保存时发生错误
	 * 		-2 : 用户已禁用
	 * 		-1 : 用户不存在
	 * 		 1 : 重置密码成功（重置后的密码为用户名）
	 */
	public int ModifyUserPsd(String sUserId, String sNewPsd) {
		BaseHibernateDAO ODao = new BaseHibernateDAO();
		
		TUser oTUser = ODao.GetUserByUserId(sUserId);
		
		if (oTUser == null) {
			return -1;
		} else if(oTUser.getActive().equals(0)) {
			return -2;
		} else {
			oTUser.setLoginPw(GetMD5Con.GetMD5Str(sNewPsd));
			
			try {
				Transaction oTraSes = ODao.getSession().beginTransaction();
				ODao.getSession().saveOrUpdate(oTUser);
				oTraSes.commit();
			} catch (RuntimeException re) {
	            return -9;
	        }
			
			return 1;
		}
	}
	
	
}
