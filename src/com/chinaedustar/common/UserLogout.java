package com.chinaedustar.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserLogout extends HttpServlet {

	/** serialVersionUID */
	private static final long serialVersionUID = -6491107448235302229L;
	
	/** 日志 */
	private static final Log log = LogFactory.getLog(UserLogout.class);

	/**
	 * Constructor of the object.
	 */
	public UserLogout() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		System.out.println("UserLogout destoried!!!");
	}

	/* (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("static-access")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String sUniqueId = request.getParameter("UserTicket");

		if (sUniqueId == null || sUniqueId.equals("")) {
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cCookie = cookies[i];

					if (cCookie.getName().equalsIgnoreCase("UserTicket")) {
						sUniqueId = cCookie.getValue();
						break;
					}
				}
			}
		}

		if (sUniqueId != null && !sUniqueId.equals("")) {
			AppOnLoad.USERMAP.RemoveUserByUserTicket(sUniqueId);

			com.chinaedustar.common.AppOnLoad.USERMAP.log.info("接收一个用户注销请求,ip="
					+ request.getRemoteAddr() + " QueryString="
					+ request.getQueryString() + " 请求处理结果=注销成功");
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>注销登陆</TITLE></HEAD>");
			out.println("  <BODY>");
			if(request.getParameter("RedUrl")!=null) {
				out.print(" <script>alert('  成功注销登陆！');window.location='"+request.getParameter("RedUrl")+"';</script>");
			} else {
				out.print(" <script>alert('  成功注销登陆！');window.location='innerlogin.jsp';</script>");
			}
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		} else {
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>用户尚未登录</TITLE></HEAD>");
			out.println("  <BODY>");
			if(request.getParameter("RedUrl") != null) {
				out.print(" <script>alert('用户尚未登录！');window.location='"+request.getParameter("RedUrl")+"';</script>");
			} else {
				out.print(" <script>alert('用户尚未登录！');window.location='innerlogin.jsp';</script>");
			}
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}
		out.flush();
		out.close();
	}

	/* (non-Javadoc)
	 *
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		log.info("UserLogout loaded!!!");
	}

}
