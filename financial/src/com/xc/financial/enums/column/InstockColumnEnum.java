package com.xc.financial.enums.column;

public enum InstockColumnEnum {

	COLUMN_BLANK("",""),COLUMN_INDEX("index","序号"),COLUMN_CODE("code","编码"),
	COLUMN_MEMBER("member","家庭成员"),COLUMN_TYPE("type","收入类型"),COLUMN_AMOUNT("amount","金额(元)"),
	COLUMN_CREATE_DATE("create_date","创建时间"),COLUMN_MODIFY_DATE("modify_date","修改时间"),COLUMN_OPERATE("operate","操作员");
	
	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private InstockColumnEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static InstockColumnEnum getInstockColumnKeyByValue(String values){
		for(InstockColumnEnum instockColumn : InstockColumnEnum.values()){
			if(instockColumn.getValue().equals(values)){
				return instockColumn;
			}
		}
		return null;
	}
	
	public static InstockColumnEnum getInstockColumnValueByKey(String keys){
		for(InstockColumnEnum instockColumn : InstockColumnEnum.values()){
			if(instockColumn.getKey().equals(keys)){
				return instockColumn;
			}
		}
		return null;
	}
	
}
