package com.xinhai.enums;

public enum QuestionEnum {
	TEXT("text","填空题"),
	CHECKBOX("checkbox","多选题"),
	RADIO("radio","单选题"),
	RADIOS("radios","矩阵量表图");


	private String code;
	private String name; 

	private QuestionEnum(String code,String name) {
		this.code = code;
		this.name = name;
	}
	
	 public static String getName(String code) {  
	        for (QuestionEnum c : QuestionEnum.values()) {  
	            if (code.equals(c.getCode())) {  
	                return c.getName(); 
	            }  
	        }  
	        return null;  
	    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}  
	 
	 
}	