package com.xc.financial.enums.column;

public enum CodeDictColumnEnum {
	
	COLUMN_ID("id","编号"),COLUMN_TYPE("type","分类"),COLUMN_VALUE("value","内容"),COLUMN_PARENT("pid","上级分类");
	
	private String key;
	
	private String value;
	
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private CodeDictColumnEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static CodeDictColumnEnum getCodeDictColumnKeyByValue(String values){
		for(CodeDictColumnEnum codeDictColumn : CodeDictColumnEnum.values()){
			if(codeDictColumn.getValue().equals(values)){
				return codeDictColumn;
			}
		}
		return null;
	}
	
	public static CodeDictColumnEnum getCodeDictColumnValueByKey(String keys){
		for(CodeDictColumnEnum codeDictColumn : CodeDictColumnEnum.values()){
			if(codeDictColumn.getKey().equals(keys)){
				return codeDictColumn;
			}
		}
		return null;
	}
}
