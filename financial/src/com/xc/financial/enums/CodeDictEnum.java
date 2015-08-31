package com.xc.financial.enums;

public enum CodeDictEnum {

	INSTOCK("0","收入"),OUTSTOCK("1","支出"),FINANCIAL("2","余额");
	
	private String key;
	
	private String value;
	
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private CodeDictEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static CodeDictEnum getCodeDictValueByKey(String keys){
		for(CodeDictEnum codeDictEnum : CodeDictEnum.values()){
			if(codeDictEnum.getKey().equals(keys)){
				return codeDictEnum;
			}
		}
		return null;
	}
}
