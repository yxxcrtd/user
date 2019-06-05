package com.chinaedustar.act;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.chinaedustar.common.ComMsgInfo;
import com.chinaedustar.hbm.BaseHibernateDAO;

/**
 * 删除选择的用户
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Mar 21, 2007 9:15:25 PM
 */
public class DelSelUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ComMsgInfo oMsg = new ComMsgInfo();
		if (request.getParameter("ids") == null || request.getParameter("ids").equals("")) {
			oMsg.setTitle("错误");
			oMsg.setContent("请先选择要删除的用户！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("javascript:history.back()");
		} else {
			String[] sIdList = request.getParameter("ids").split(",");
			int iDelCou = 0;

			// 删除用户
			for (int i = 0; i < sIdList.length; i++) {
				iDelCou += new BaseHibernateDAO().DelUserByKey(sIdList[i]);
			}

			oMsg.setTitle("成功");
			oMsg.setContent("成功删除 " + iDelCou + " 个用户！");
			oMsg.setLinktext("返回");
			oMsg.setLinkurl("UserList.jsp");
		}
		request.setAttribute("MsgInfo", oMsg);
		return mapping.findForward("CommonMsg");
	}

}
