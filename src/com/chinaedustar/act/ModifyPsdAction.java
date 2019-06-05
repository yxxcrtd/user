package com.chinaedustar.act;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.chinaedustar.common.AppOnLoad;
import com.chinaedustar.common.ComMsgInfo;
import com.chinaedustar.form.ModifyPsdForm;

/**
 * @path="/modifyPsd" name="modifyPsdForm" input="/ModUserPsd.jsp" scope="request"
 */
public class ModifyPsdAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ModifyPsdForm fForm = (ModifyPsdForm) form;
		ComMsgInfo oMsg = new ComMsgInfo();

		// 执行修改口令操作，根据返回值设定信息
		switch (AppOnLoad.USERMAP.ModifyUserPsd(fForm.getLoginId(), fForm.getNewPsd())) {
		case -9:
			oMsg.setTitle("错误");
			oMsg.setContent("用户口令保存失败，请重试！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:history.back()");
			break;

		case -2:
			oMsg.setTitle("错误");
			oMsg.setContent("当前用户已禁用！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("login.jsp");
			break;

		case -1:
			oMsg.setTitle("错误");
			oMsg.setContent("用户不存在！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("login.jsp");
			break;

		case 1:
			oMsg.setTitle("成功");
			oMsg.setContent("用户口令保存成功！");
			oMsg.setLinktext("关闭");
			oMsg.setLinkurl("javascript:window.close()");
			break;
		}

		request.setAttribute("MsgInfo", oMsg);
		return mapping.findForward("CommonMsg");
	}

}
