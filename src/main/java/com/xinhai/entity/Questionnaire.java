package com.xinhai.entity;

public class Questionnaire {
	private int id;
	private String naireCode;
	private String naireTitle;
	private int naireLevleNum;
	private String naireRemark;

	public Questionnaire() {
		super();
	}

	public Questionnaire(String naireCode, String naireTitle, int naireLevleNum, String naireRemark) {
		super();
		this.naireCode = naireCode;
		this.naireTitle = naireTitle;
		this.naireLevleNum = naireLevleNum;
		this.naireRemark = naireRemark;
	}

	public Questionnaire(int id, String naireCode, String naireTitle, int naireLevleNum, String naireRemark) {
		super();
		this.id = id;
		this.naireCode = naireCode;
		this.naireTitle = naireTitle;
		this.naireLevleNum = naireLevleNum;
		this.naireRemark = naireRemark;
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

	public int getNaireLevleNum() {
		return naireLevleNum;
	}

	public void setNaireLevleNum(int naireLevleNum) {
		this.naireLevleNum = naireLevleNum;
	}

	public String getNaireRemark() {
		return naireRemark;
	}

	public void setNaireRemark(String naireRemark) {
		this.naireRemark = naireRemark;
	}

	@Override
	public String toString() {
		return "Questionnaire [id=" + id + ", naireCode=" + naireCode + ", naireTitle=" + naireTitle
				+ ", naireLevleNum=" + naireLevleNum + ", naireRemark=" + naireRemark + "]";
	}
}
