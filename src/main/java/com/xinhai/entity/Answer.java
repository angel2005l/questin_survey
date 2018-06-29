package com.xinhai.entity;

public class Answer {
	private int id;
	private int queId;
	private String queTitle;
	private String ansResult;
	private String naireCode;
	private String ansOpenId;

	public Answer() {
		super();
	}

	public Answer(int id, int queId, String queTitle, String ansResult, String naireCode, String ansOpenId) {
		super();
		this.id = id;
		this.queId = queId;
		this.queTitle = queTitle;
		this.ansResult = ansResult;
		this.naireCode = naireCode;
		this.ansOpenId = ansOpenId;
	}

	public Answer(int queId, String queTitle, String ansResult, String naireCode, String ansOpenId) {
		super();
		this.queId = queId;
		this.queTitle = queTitle;
		this.ansResult = ansResult;
		this.naireCode = naireCode;
		this.ansOpenId = ansOpenId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	public String getQueTitle() {
		return queTitle;
	}

	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}

	public String getAnsResult() {
		return ansResult;
	}

	public void setAnsResult(String ansResult) {
		this.ansResult = ansResult;
	}

	public String getNaireCode() {
		return naireCode;
	}

	public void setNaireCode(String naireCode) {
		this.naireCode = naireCode;
	}

	public String getAnsOpenId() {
		return ansOpenId;
	}

	public void setAnsOpenId(String ansOpenId) {
		this.ansOpenId = ansOpenId;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", queId=" + queId + ", queTitle=" + queTitle + ", ansResult=" + ansResult
				+ ", naireCode=" + naireCode + ", ansOpenId=" + ansOpenId + "]";
	}

}
