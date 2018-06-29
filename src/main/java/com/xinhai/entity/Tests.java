package com.xinhai.entity;

public class Tests {
	private int id;
	private String testName;
	private String testType;

	public Tests() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	@Override
	public String toString() {
		return "Tests [id=" + id + ", testName=" + testName + ", testType=" + testType + "]";
	}

}
