package com.chinaedustar.act;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.chinaedustar.common.AppOnLoad;
import com.chinaedustar.common.ComMsgInfo;
import com.chinaedustar.common.GetMD5Con;
import com.chinaedustar.form.RegNewUserForm;
import com.chinaedustar.hbm.BaseHibernateDAO;
import com.chinaedustar.hbm.TUser;

/** 
 * @path="/modifyUserInfo" name="regNewUserForm" scope="request" validate="true"
 */
public class ModifyUserInfoAction extends Action {
	
	@SuppressWarnings("deprecation")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ComMsgInfo oMsg = new ComMsgInfo();
		try {
			RegNewUserForm fForm = (RegNewUserForm) form;
			TUser oCurUser = new BaseHibernateDAO().GetUserById(Long.valueOf(fForm.getId()));
			
			// 设置用户类信息
			if (request.getParameter("_Level").equals("2")) {
				oCurUser.setActive(fForm.getActive() == null ? Short.valueOf("0") : Short.valueOf("1"));
				oCurUser.setTeacher(fForm.getTeacher() == null ? Short.valueOf("0") : Short.valueOf("1"));
			} 
			
			oCurUser.setName(fForm.getName());
			oCurUser.setQuestion(fForm.getQuestion());
			
			if (!fForm.getLoginPw().equals("")) oCurUser.setLoginPw(GetMD5Con.GetMD5Str(fForm.getLoginPw()));
			if (!fForm.getAnswer().equals("")) oCurUser.setAnswer(GetMD5Con.GetMD5Str(fForm.getAnswer()));
			
			if (oCurUser.getLoginPw()!=null &&oCurUser.getLoginPw().length() != 32) oCurUser.setLoginPw(GetMD5Con.GetMD5Str(oCurUser.getLoginPw()));
			if (oCurUser.getAnswer()!=null && oCurUser.getAnswer().length() != 32) oCurUser.setAnswer(GetMD5Con.GetMD5Str(oCurUser.getAnswer()));
			
			// 上传文件
			FormFile fFormFile = fForm.getUploads();
			
			if (fFormFile != null && !fFormFile.getFileName().equals("")) {
				try {
					String sFilePath = request.getRealPath("/") + "UpLoad";
				
					if (!new File(sFilePath).exists()) {
						new File(sFilePath).mkdirs();
					}
					
					sFilePath += "\\" + fForm.getId();
					
					InputStream isInputstream = fFormFile.getInputStream();
					ByteArrayOutputStream bosOutStream = new ByteArrayOutputStream();
					OutputStream bos = new FileOutputStream(sFilePath);

					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					
					while ((bytesRead = isInputstream.read(buffer, 0, 8192)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}
					bosOutStream.close();
					isInputstream.close();
					oCurUser.setIcon("UpLoad/" + fForm.getId());
				}catch(Exception e){
					System.out.println("附件上传失败");
				}
			}

			if (request.getParameter("DelIcon") != null) {
				try {
					String sFilePath = request.getRealPath("/") + "UpLoad\\" + oCurUser.getId();
					
					File fIconFile = new File(sFilePath);
					
					if (fIconFile.exists()) {
						fIconFile.delete();
					}
					
					oCurUser.setIcon("");
				}catch(Exception e){
					System.out.println("附件删除失败");
				}
			}
			
			// 执行修改用户操作，根据返回值设定信息
			switch (AppOnLoad.USERMAP.ModifyUserInfo(oCurUser)) {
			case -9:
				oMsg.setTitle("错误");
				oMsg.setContent("用户保存失败，请重试！");
				oMsg.setLinktext("返回");
				oMsg.setLinkurl("javascript:history.back()");
				break;
			case 0:
				oMsg.setTitle("错误");
				oMsg.setContent("无法从服务器获取用户信息！");
				oMsg.setLinktext("返回");
				oMsg.setLinkurl("javascript:history.back()");
				break;
			case 1:
				oMsg.setTitle("成功");
				oMsg.setContent("用户保存成功！");
				oMsg.setLinktext("返回");
				oMsg.setLinkurl(request.getParameter("_Byid").equals("") ? "javascript:history.back()" : "UserList.jsp");
				break;
			}
			request.setAttribute("MsgInfo", oMsg);
		} catch (Exception e) {
			e.printStackTrace();
			oMsg.setTitle("错误");
			oMsg.setContent("用户保存失败！"+e.toString()+"");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl(request.getParameter("_Byid").equals("") ? "javascript:history.back()" : "UserList.jsp");
			request.setAttribute("MsgInfo", oMsg);
		}
		return mapping.findForward("CommonMsg");
	}

}
