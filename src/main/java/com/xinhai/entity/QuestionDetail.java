package com.xinhai.entity;

public class QuestionDetail {
	private int id;
	private String detContext;
	private int queId;

	public QuestionDetail() {
		super();
	}
	
	public QuestionDetail(String detContext, int queId) {
		super();
		this.detContext = detContext;
		this.queId = queId;
	}

	public QuestionDetail(int id, String detContext, int queId) {
		super();
		this.id = id;
		this.detContext = detContext;
		this.queId = queId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetContext() {
		return detContext;
	}

	public void setDetContext(String detContext) {
		this.detContext = detContext;
	}

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	@Override
	public String toString() {
		return "QuestionDetail [id=" + id + ", detContext=" + detContext + ", queId=" + queId + "]";
	}

}
