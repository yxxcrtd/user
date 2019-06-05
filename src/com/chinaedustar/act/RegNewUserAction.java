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
import com.chinaedustar.form.RegNewUserForm;
import com.chinaedustar.hbm.TUser;

/**
 * @input="/RegNewUser.jsp" name="RegNewUserErr" path="/RegNewUser.jsp"
 */
public class RegNewUserAction extends Action {

	@SuppressWarnings("deprecation")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegNewUserForm fForm = (RegNewUserForm) form;
		ComMsgInfo oMsg = new ComMsgInfo();

		TUser oNewUser = new TUser();
		oNewUser.setAnswer(fForm.getAnswer());
		oNewUser.setCode(fForm.getCode());
		oNewUser.setLoginId(fForm.getLoginId());
		oNewUser.setLoginPw(fForm.getLoginPw());
		oNewUser.setName(fForm.getName());
		oNewUser.setQuestion(fForm.getQuestion());
		oNewUser.setActive(fForm.getActive() == null ? Short.valueOf("0") : Short.valueOf("1"));
		oNewUser.setSystem(fForm.getSystem() == null ? Short.valueOf("0") : Short.valueOf("1"));
		oNewUser.setTeacher(fForm.getTeacher() == null ? Short.valueOf("0") : Short.valueOf("1"));

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
				oNewUser.setIcon("UpLoad/" + fForm.getId());
			} catch (Exception e) {
				System.out.println("附件上传失败");
			}
		}

		// 执行注册用户操作，根据返回值设定信息
		switch (AppOnLoad.USERMAP.RegNewUser(oNewUser)) {
		case -9:
			oMsg.setTitle("错误");
			oMsg.setContent("用户保存失败，请重试！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:window.history.back()");
			break;
		case 0:
			oMsg.setTitle("错误");
			oMsg.setContent("用户登录名重复，请修改后重试！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:window.history.back()");
			break;
		case 1:
			oMsg.setTitle("成功");
			oMsg.setContent("用户注册成功！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("UserList.jsp");
			break;
		}
		request.setAttribute("MsgInfo", oMsg);
		return mapping.findForward("CommonMsg");
	}

}
