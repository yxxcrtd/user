package com.chinaedustar.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chinaedustar.hbm.TUser;

public class VerUser extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7270777514402607132L;
	
	/**
	 * 日志
	 */
	private static final Log log = LogFactory.getLog(VerUser.class);
	
	/**
	 * Constructor of the object
	 */
	public VerUser() {
		super();
	}

	/* (non-Javadoc)
	 *
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		super.destroy();
		log.info("VerUser destoried!!!");
	}

	/* (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("static-access")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		TUser oTUser = AppOnLoad.USERMAP.GetUserByUserTicket(request.getParameter("UserTicket"), request.getParameter("Reip"));
		
		if (oTUser != null) {
			out.println(oTUser.getName());
			out.println(oTUser.getCode());
			out.println(oTUser.getLoginId());
			out.println(oTUser.getLoginPw());
			out.println(oTUser.getIcon());
			out.println(oTUser.getTeacher());
			
			AppOnLoad.USERMAP.log.info("接收一个用户验证请求,userid=" + oTUser.getLoginId() + " ip=" + request.getRemoteAddr() + " QueryString=" + request.getQueryString() + " 请求处理结果=获取成功");
		} else {
			AppOnLoad.USERMAP.log.info("接收一个用户验证请求,ip=" + request.getRemoteAddr() + " QueryString=" + request.getQueryString() + " 请求处理结果=获取失败");
		}		
		out.flush();
		out.close();
	}

	/* (non-Javadoc)
	 *
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		log.info("Verify user engine loaded!!!");
	}

}
