package com.chinaedustar.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * @struts.form name="regNewUserForm"
 */
public class RegNewUserForm extends ActionForm {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5335744567120839699L;

	private Integer active;
	private String code;
	private String loginId;
	private String icon;
	private String answer;
	private Integer teacher;
	private Integer system;
	private String loginPw;
	private String name;
	private String question;
	private Integer id;
	private FormFile uploads;

	/*
	 * Generated Methods
	 */

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
		// 
	}

	/**
	 * Returns the active.
	 * 
	 * @return Integer
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * Set the active.
	 * 
	 * @param active
	 *            The active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}

	/**
	 * Returns the code.
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Set the code.
	 * 
	 * @param code
	 *            The code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Returns the loginId.
	 * 
	 * @return String
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Set the loginId.
	 * 
	 * @param loginId
	 *            The loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Returns the icon.
	 * 
	 * @return String
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Set the icon.
	 * 
	 * @param icon
	 *            The icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Returns the answer.
	 * 
	 * @return String
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Set the answer.
	 * 
	 * @param answer
	 *            The answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * Returns the teacher.
	 * 
	 * @return Integer
	 */
	public Integer getTeacher() {
		return teacher;
	}

	/**
	 * Set the teacher.
	 * 
	 * @param teacher
	 *            The teacher to set
	 */
	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}

	/**
	 * Returns the system.
	 * 
	 * @return Integer
	 */
	public Integer getSystem() {
		return system;
	}

	/**
	 * Set the system.
	 * 
	 * @param system
	 *            The system to set
	 */
	public void setSystem(Integer system) {
		this.system = system;
	}

	/**
	 * Returns the loginPw.
	 * 
	 * @return String
	 */
	public String getLoginPw() {
		return loginPw;
	}

	/**
	 * Set the loginPw.
	 * 
	 * @param loginPw
	 *            The loginPw to set
	 */
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	/**
	 * Returns the name.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name.
	 * 
	 * @param name
	 *            The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the question.
	 * 
	 * @return String
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * Set the question.
	 * 
	 * @param question
	 *            The question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Returns the id.
	 * 
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the id.
	 * 
	 * @param id
	 *            The id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Returns the uploads.
	 * 
	 * @return CommonsMultipartRequestHandler$CommonsFormFile
	 */
	public FormFile getUploads() {
		return uploads;
	}

	/**
	 * Set the uploads.
	 * 
	 * @param uploads
	 *            The uploads to set
	 */
	public void setUploads(FormFile uploads) {
		this.uploads = uploads;
	}
}