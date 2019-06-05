package com.chinaedustar.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.chinaedustar.hbm.BaseHibernateDAO;
import com.chinaedustar.hbm.TUser;

/**
 * 页面加载类
 */
public final class PageVerifyOpe {
	/**
	 * 从页面中得到当前登录用户信息
	 * @param pageContext
	 * @return
	 */	
	public static TUser GetPageUserInfo(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		String sUniqueId = "";
		Cookie[] cookies = request.getCookies();
		
		if (cookies == null) {
			return null;
		} else {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cCookie = cookies[i];
				
				if (cCookie.getName().equalsIgnoreCase("UserTicket")) {
					sUniqueId = cCookie.getValue();
					
					break;
				}
			}
		}
		
		return com.chinaedustar.common.AppOnLoad.USERMAP.GetUserByUserTicket(sUniqueId, request.getRemoteAddr());
	}
	
	/**
	 * RegNewUser.jsp 加载类
	 * @param pageContext
	 */
	public static void DoRegNewUser(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		com.chinaedustar.common.ComMsgInfo oMsg = new com.chinaedustar.common.ComMsgInfo();
		
		TUser oCurUser = GetPageUserInfo(pageContext);
		
		if (oCurUser == null || !oCurUser.getLoginId().equals("admin")) {
			oMsg.setTitle("错误");
			oMsg.setContent("只有管理员才能注册用户，请重新登录！");
			oMsg.setLinktext("登录");
			oMsg.setLinkurl("login.jsp");
			
			request.setAttribute("MsgInfo", oMsg);
			
			try {
				pageContext.forward("CommonMsg.jsp");
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			TUser oNewUser = new TUser();
			
			if (request.getAttribute("CurDataInfo") == null) {
				oNewUser.setCode("");
				oNewUser.setName("");
				
				oNewUser.setLoginId("");
				oNewUser.setLoginPw("");
				
				oNewUser.setIcon("");
				
				oNewUser.setAnswer("");
				oNewUser.setQuestion("");
				oNewUser.setActive(Short.valueOf("1"));
				oNewUser.setSystem(Short.valueOf("1"));
				oNewUser.setTeacher(Short.valueOf("1"));
				
				pageContext.setAttribute("CurDataInfo", oNewUser);
			}
		}
	}
	
	/**
	 * ModUserInfo.jsp 加载类
	 * @param pageContext
	 */
	public static void DoModUserInfo(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		com.chinaedustar.common.ComMsgInfo oMsg = new com.chinaedustar.common.ComMsgInfo();
		
		TUser oCurUser = GetPageUserInfo(pageContext);
		TUser oSelUser = null;
		
		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			if (oCurUser == null) {
				try {
					pageContext.forward("login.jsp");
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				oSelUser = oCurUser;
			}
		} else {
			oSelUser = new BaseHibernateDAO().GetUserById(Long.valueOf(request.getParameter("id")));
		}
			
		if (oSelUser == null) {
			oMsg.setTitle("错误");
			oMsg.setContent("该用户不存在！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:history.back()");
			
			request.setAttribute("MsgInfo", oMsg);
			
			try {
				pageContext.forward("CommonMsg.jsp");
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (oCurUser == null) {
				request.setAttribute("UserLev", 0);
			} else if (oCurUser.getLoginId().equals("admin")) {
				request.setAttribute("UserLev", 2);
			} else if (oCurUser.getLoginId().equals(oSelUser.getLoginId())) {
				request.setAttribute("UserLev", 1);
			} else {
				request.setAttribute("UserLev", 0);
			}
			
			pageContext.setAttribute("CurDataInfo", oSelUser);
		}
	}
	
	/**
	 * UserListPage.jsp 页面加载类
	 * @param pageContext
	 */
	public static void DoUserListPage(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		String sSearchKey = "";
		
		String[] sSeaVal = {"loginId", "name"};
		String[] sSymVal = {"Like", "Like"};
		String[] sKeyVal = {"", ""};
		String[] sReaVal = {"OR"};

		if (request.getParameter("sk") != null && !request.getParameter("sk").equals("")) {
			try {
				sSearchKey = new String(request.getParameter("sk").getBytes("iso-8859-1"), "gbk");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			sKeyVal[0] = sKeyVal[1] = "%" + sSearchKey + "%";
		} else {
			sSeaVal = null;
			sSymVal = null;
			sKeyVal = null;
			sReaVal = null;
		}
		
		ViewPageInfo oViewPageInfo = new ViewPageInfo();
		
		oViewPageInfo.setAlldatacou(new BaseHibernateDAO().GetUserCount(sSeaVal, sSymVal, sKeyVal, sReaVal));
		
		oViewPageInfo.setPerpagecou((request.getParameter("Count") == null) ? 20 : Integer.parseInt(request.getParameter("Count")));
		
		oViewPageInfo.setAllpagecou((oViewPageInfo.getAlldatacou() / oViewPageInfo.getPerpagecou()) + (((oViewPageInfo.getAlldatacou() % oViewPageInfo.getPerpagecou()) == 0) ? 0 : 1));
		if (oViewPageInfo.getAllpagecou() == 0) {
			oViewPageInfo.setAllpagecou(1);
		}
		
		oViewPageInfo.setCurpagenum((request.getParameter("PageNum") == null) ? 1 : Integer.parseInt(request.getParameter("PageNum")));
		if (oViewPageInfo.getAllpagecou() < oViewPageInfo.getCurpagenum()) {
			oViewPageInfo.setCurpagenum(oViewPageInfo.getAllpagecou());
		}
		
		@SuppressWarnings("rawtypes")
		List lDataList = new BaseHibernateDAO().GetAllUserByKey(sSeaVal, sSymVal, sKeyVal, sReaVal, (oViewPageInfo.getCurpagenum() - 1) * oViewPageInfo.getPerpagecou(), oViewPageInfo.getPerpagecou());
		
		ArrayList<Object[]> oViewData = new ArrayList<Object[]>();
		
		for (int i = 0; i < lDataList.size(); i ++) {
			Object[] oRowData = new Object[6];
			
			TUser oTUser = (TUser) lDataList.get(i);
			
			oRowData[0] = oTUser.getId();
			oRowData[1] = oTUser.getActive();
			oRowData[2] = oTUser.getTeacher();
			oRowData[3] = oTUser.getName();
			oRowData[4] = oTUser.getLoginId();
			oRowData[5] = oTUser.getIcon();
			
			oViewData.add(oRowData);
		}
		
		request.setAttribute("ViewPageInfo", oViewPageInfo);
		request.setAttribute("ViewDataInfo", oViewData);
	}
	
	/**
	 * ForgetPsd.jsp 页面加载类
	 * @param pageContext
	 */
	public static void DoForgetPsd(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		com.chinaedustar.common.ComMsgInfo oMsg = new com.chinaedustar.common.ComMsgInfo();
		
		if (request.getParameter("loginid") == null || request.getParameter("loginid").equals("")) {
			oMsg.setTitle("错误");
			oMsg.setContent("请先输入用户名！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("ForgetPsd.jsp");
			
			request.setAttribute("MsgInfo", oMsg);
			
			try {
				pageContext.forward("CommonMsg.jsp");
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			TUser oSelUser = new BaseHibernateDAO().GetUserByUserId(request.getParameter("loginid"));
			
			if (oSelUser == null) {
				oMsg.setTitle("错误");
				oMsg.setContent("用户 " + request.getParameter("loginid") + " 不存在！");
				oMsg.setLinktext("返回");
				oMsg.setLinkurl("ForgetPsd.jsp");
				
				request.setAttribute("MsgInfo", oMsg);
				
				try {
					pageContext.forward("CommonMsg.jsp");
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (oSelUser.getQuestion().equals("")) {
				oMsg.setTitle("错误");
				oMsg.setContent("该用户未设置恢复口令信息");
				oMsg.setLinktext("返回");
				oMsg.setLinkurl("ForgetPsd.jsp");
				
				request.setAttribute("MsgInfo", oMsg);
				
				try {
					pageContext.forward("CommonMsg.jsp");
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				pageContext.setAttribute("CurDataInfo", oSelUser);
			}
		}
	}
	
	/**
	 * ModifyPsd.jsp 页面加载类
	 * @param pageContext
	 */
	public static void DoModifyPsd(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		com.chinaedustar.common.ComMsgInfo oMsg = new com.chinaedustar.common.ComMsgInfo();
		
		if (request.getAttribute("user") == null || request.getAttribute("user").equals("")) {
			oMsg.setTitle("错误");
			oMsg.setContent("非法进入该页面！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("login.jsp");
			
			request.setAttribute("MsgInfo", oMsg);
			
			try {
				pageContext.forward("CommonMsg.jsp");
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
