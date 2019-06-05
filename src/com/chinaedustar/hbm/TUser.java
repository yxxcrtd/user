package com.chinaedustar.hbm;

import java.io.Serializable;

/**
 * 用户对象
 *
 * @author Yang Xinxin
 * @version 1.0.0 Mar 21, 2007 8:54:39 PM
 */
public class TUser implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 146208033098344975L;

	private Long id;
	private String name;
	private String code;
	private String loginId;
	private String loginPw;
	private String question;
	private String answer;
	private String icon;
	private Short active;
	private Short system;
	private Short teacher;

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(String name, String code, String loginId, String loginPw,
			Short active, Short system, Short teacher) {
		this.name = name;
		this.code = code;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.active = active;
		this.system = system;
		this.teacher = teacher;
	}

	/** full constructor */
	public TUser(String name, String code, String loginId, String loginPw,
			String question, String answer, String icon, Short active,
			Short system, Short teacher) {
		this.name = name;
		this.code = code;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.question = question;
		this.answer = answer;
		this.icon = icon;
		this.active = active;
		this.system = system;
		this.teacher = teacher;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return this.loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Short getSystem() {
		return this.system;
	}

	public void setSystem(Short system) {
		this.system = system;
	}

	public Short getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Short teacher) {
		this.teacher = teacher;
	}

}
