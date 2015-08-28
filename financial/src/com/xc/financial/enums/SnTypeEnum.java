package com.xc.financial.enums;

public enum SnTypeEnum {
	
	USER_CODE(0,"用户编码"),INSTOCK_CODE(1,"收入编码"),OUTSOCK_CODE(2,"支出编码"),FINANCIAL_CODE(3,"余额编码");
	
	private Integer key;
	
	private String value;
	
	public Integer getKey() {
		return key;
	}


	public String getValue() {
		return value;
	}

	private SnTypeEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
}
