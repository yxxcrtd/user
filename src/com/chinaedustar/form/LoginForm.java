package com.chinaedustar.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends ActionForm {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5726308370018689867L;

	private String redUrl;
	private String username;
	private String userpsd;

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	public String getRedUrl() {
		return redUrl;
	}

	public void setRedUrl(String redUrl) {
		this.redUrl = redUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpsd() {
		return userpsd;
	}

	public void setUserpsd(String userpsd) {
		this.userpsd = userpsd;
	}

}
