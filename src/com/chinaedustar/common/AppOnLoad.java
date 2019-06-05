package com.chinaedustar.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chinaedustar.map.GenralUserMap;

public class AppOnLoad extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3581597132156727994L;

	/**
	 * 日志
	 */
	private static final Log log = LogFactory.getLog(AppOnLoad.class);

	/**
	 * USERMAP
	 */
	public static GenralUserMap USERMAP = new GenralUserMap();

	/**
	 * Constructor of the object
	 */
	public AppOnLoad() {
		super();
	}

	/* (non-Javadoc)
	 *
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		super.destroy();
		TimeOutClearEngine.CloseEngine();
	}

	/* (non-Javadoc)
	 *
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		String sTimeOut = this.getServletConfig().getInitParameter("TimeOutSec");
		if (sTimeOut.equals(""))
			sTimeOut = "18000";

		TimeOutClearEngine.InitEngine(Long.valueOf(sTimeOut));
		log.info("统一用户认证系统已加载");
	}

}
