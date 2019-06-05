package com.chinaedustar.common;

public class ViewPageInfo {
	private int alldatacou;
	private int perpagecou;
	private int allpagecou;
	private int curpagenum;

	/** default constructor */
	public ViewPageInfo() {
	}

	// Property accessors

	public int getAlldatacou() {
		return this.alldatacou;
	}

	public void setAlldatacou(int alldatacou) {
		this.alldatacou = alldatacou;
	}

	public int getPerpagecou() {
		return this.perpagecou;
	}

	public void setPerpagecou(int perpagecou) {
		this.perpagecou = perpagecou;
	}

	public int getAllpagecou() {
		return this.allpagecou;
	}

	public void setAllpagecou(int allpagecou) {
		this.allpagecou = allpagecou;
	}

	public int getCurpagenum() {
		return this.curpagenum;
	}

	public void setCurpagenum(int curpagenum) {
		this.curpagenum = curpagenum;
	}

}