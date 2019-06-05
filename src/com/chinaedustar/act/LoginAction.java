package com.chinaedustar.act;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.chinaedustar.common.AppOnLoad;
import com.chinaedustar.common.ComMsgInfo;
import com.chinaedustar.form.LoginForm;

/**
 * 登录
 *
 * @author Yang Xinxin
 * @version 1.0.0 Mar 21, 2007 9:12:38 PM
 */
public class LoginAction extends Action {
	@SuppressWarnings("static-access")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LoginForm fForm = (LoginForm) form;
		ComMsgInfo oMsg = new ComMsgInfo();
		
		// 执行修改口令操作，根据返回值设定信息
		switch (AppOnLoad.USERMAP.RegUserLogin(request, response, fForm.getUsername(), fForm.getUserpsd())) {
		case -2:
			oMsg.setTitle("错误");
			oMsg.setContent("当前用户已禁用！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl((fForm.getRedUrl() == null || fForm.getRedUrl().equals("")) ? "login.jsp" : "javascript:history.back()");
			AppOnLoad.USERMAP.log.info("接收一个用户登录请求,userid=" + fForm.getUsername() + " ip=" + request.getRemoteAddr() + " QueryString=" + request.getQueryString() + " 请求处理结果=用户已禁用");
			break;
		case -1:
			oMsg.setTitle("错误");
			oMsg.setContent("用户 " + fForm.getUsername() + " 不存在！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl((fForm.getRedUrl() == null || fForm.getRedUrl() .equals("")) ? "login.jsp" : "javascript:history.back()");
			AppOnLoad.USERMAP.log.info("接收一个用户登录请求,userid=" + fForm.getUsername() + " ip=" + request.getRemoteAddr() + " QueryString=" + request.getQueryString() + " 请求处理结果=用户不存在");
			break;
		case 0:
			oMsg.setTitle("错误");
			oMsg.setContent("用户口令错误，请重试！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:history.back()");
			AppOnLoad.USERMAP.log.info("接收一个用户登录请求,userid=" + fForm.getUsername() + " ip=" + request.getRemoteAddr() + " QueryString=" + request.getQueryString() + " 请求处理结果=口令错误");
			break;
		case 1:
			oMsg.setTitle("成功");
			oMsg.setContent("登录成功！");
			oMsg.setLinktext("跳转");
			oMsg.setLinkurl((fForm.getRedUrl() == null || fForm.getRedUrl().equals("")) ? "UserList.jsp" : fForm.getRedUrl());
			AppOnLoad.USERMAP.log.info("接收一个用户登录请求,userid=" + fForm.getUsername() + " ip=" + request.getRemoteAddr() + " QueryString=" + request.getQueryString() + " 请求处理结果=登录成功");
			break;
		}
		request.setAttribute("MsgInfo", oMsg);
		return mapping.findForward("CommonMsg");
	}
	
}
