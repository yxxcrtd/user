package com.chinaedustar.act;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.chinaedustar.common.AppOnLoad;
import com.chinaedustar.common.ComMsgInfo;
import com.chinaedustar.form.ForgetUserPsdForm;

/**
 * @path="/forgetUserPsd" name="forgetUserPsdForm" scope="request" validate="true"
 */
public class ForgetUserPsdAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ForgetUserPsdForm fForm = (ForgetUserPsdForm) form;
		ComMsgInfo oMsg = new ComMsgInfo();

		// 执行注册用户操作，根据返回值设定信息
		switch (AppOnLoad.USERMAP.CheckUserAnswer(request, fForm.getUserId(), fForm.getAnswer())) {
		case -2:
			oMsg.setTitle("错误");
			oMsg.setContent("当前用户已禁用！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:history.back()");
			break;
		case -1:
			oMsg.setTitle("错误");
			oMsg.setContent("用户 " + fForm.getUserId() + " 不存在！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:history.back()");
			break;
		case 0:
			oMsg.setTitle("错误");
			oMsg.setContent("问题或答案错误！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:history.back()");
			break;
		case 1:
			return mapping.findForward("VerSuc");
		}
		request.setAttribute("MsgInfo", oMsg);
		return mapping.findForward("CommonMsg");
	}

}
