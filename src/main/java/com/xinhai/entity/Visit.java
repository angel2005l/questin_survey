package com.xinhai.entity;

import java.sql.Timestamp;

public class Visit {
	private int id;
	private String naireCode;
	private String naireTitle;
	private String openId;
	private Timestamp sumbitDate;

	public Visit() {
		super();
	}

	public Visit(String naireCode, String openId) {
		super();
		this.naireCode = naireCode;
		this.openId = openId;
	}

	public Visit(int id, String naireCode, String naireTitle, String openId, Timestamp sumbitDate) {
		super();
		this.id = id;
		this.naireCode = naireCode;
		this.naireTitle = naireTitle;
		this.openId = openId;
		this.sumbitDate = sumbitDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaireCode() {
		return naireCode;
	}

	public void setNaireCode(String naireCode) {
		this.naireCode = naireCode;
	}

	public String getNaireTitle() {
		return naireTitle;
	}

	public void setNaireTitle(String naireTitle) {
		this.naireTitle = naireTitle;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Timestamp getSumbitDate() {
		return sumbitDate;
	}

	public void setSumbitDate(Timestamp sumbitDate) {
		this.sumbitDate = sumbitDate;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", naireCode=" + naireCode + ", naireTitle=" + naireTitle + ", openId=" + openId
				+ ", sumbitDate=" + sumbitDate + "]";
	}

}
