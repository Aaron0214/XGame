package com.xc.financial.enums;

public enum CodeDictEnum {

	DISTRICT(0,"区域"),INSTOCK(1,"收入"),OUTSTOCK(2,"支出"),FINANCIAL(3,"余额");
	
	private Integer key;
	
	private String value;
	
	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private CodeDictEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static CodeDictEnum getCodeDictValueByKey(Integer keys){
		for(CodeDictEnum codeDictEnum : CodeDictEnum.values()){
			if(codeDictEnum.getKey().equals(keys)){
				return codeDictEnum;
			}
		}
		return null;
	}
}
