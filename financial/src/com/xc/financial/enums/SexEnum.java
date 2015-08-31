package com.xc.financial.enums;

public enum SexEnum {

	NORMAL("Y","正常"),un_normal("N","异常");
	
	private String key;
	
	private String value;
	
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private SexEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static SexEnum getStatusValueByKey(String keys){
		for(SexEnum statusEnum : SexEnum.values()){
			if(statusEnum.getKey().equals(keys)){
				return statusEnum;
			}
		}
		return null;
	}
}
