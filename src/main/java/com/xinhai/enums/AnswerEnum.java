package com.xinhai.enums;

public enum AnswerEnum {
	STRONGLYDISAGREE(1, "非常不同意"), DISAGREE(2, "不同意"), INDETERMINATION(3, "不确定"), AGREE(4, "同意"), STRINGLYAGREE(5,
			"非常同意");

	private int code;
	private String name;

	private AnswerEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(String code) {
		for (AnswerEnum c : AnswerEnum.values()) {
			if (code.equals(c.getCode() + "")) {
				return c.getName();
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
