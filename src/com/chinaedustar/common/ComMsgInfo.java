package com.chinaedustar.common;

public class ComMsgInfo {
	private String title;
	private String content;
	private String linktext;
	private String linkurl;

	/**
	 * Default Constructor
	 */
	public ComMsgInfo() { 
		// 
	} 

	/** 
	 * Full Constructor
	 */
	public ComMsgInfo(String title, String content, String linktext, String linkurl) {
		this.title = title;
		this.content = content;
		this.linktext = linktext;
		this.linkurl = linkurl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinktext() {
		return this.linktext;
	}

	public void setLinktext(String linktext) {
		this.linktext = linktext;
	}

	public String getLinkurl() {
		return this.linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

}
