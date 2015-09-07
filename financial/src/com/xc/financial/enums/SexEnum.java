package com.xc.financial.enums;

public enum SexEnum {

	MAN(0,"男"),WOMAN(1,"女");
	
	private Integer key;
	
	private String value;
	
	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private SexEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static SexEnum getSexValueByKey(Integer keys){
		for(SexEnum sexEnum : SexEnum.values()){
			if(sexEnum.getKey().equals(keys)){
				return sexEnum;
			}
		}
		return null;
	}
}
