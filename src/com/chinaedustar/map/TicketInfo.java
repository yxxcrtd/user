package com.chinaedustar.map;

import java.util.Date;

/**
 * 票证对象
 *
 * @author Yang Xinxin
 * @version 1.0.0 Mar 21, 2007 8:52:40 PM
 */
public class TicketInfo {

	/**
	 * 票证 Id
	 */
	private Long id;

	/**
	 * 创建日期
	 */
	private Date create;

	/**
	 * IP
	 */
	private String ip;

	/**
	 * Default Constructor
	 */
	public TicketInfo() {
		// 
	}

	/**
	 * full constructor
	 * 
	 * @param id
	 * @param create
	 * @param ip
	 */
	public TicketInfo(Long id, Date create, String ip) {
		this.id = id;
		this.create = create;
		this.ip = ip;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreate() {
		return this.create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
