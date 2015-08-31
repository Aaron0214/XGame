package com.xc.financial.enums;

public enum StatusEnum {

	NORMAL("Y","正常"),un_normal("N","异常");
	
	private String key;
	
	private String value;
	
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private StatusEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static StatusEnum getStatusValueByKey(String keys){
		for(StatusEnum statusEnum : StatusEnum.values()){
			if(statusEnum.getKey().equals(keys)){
				return statusEnum;
			}
		}
		return null;
	}
}
