package com.xc.financial.enums.column;

public enum OutstockColumnEnum {

	COLUMN_BLANK("",""),COLUMN_INDEX("index","序号"),COLUMN_CODE("code","编码"),
	COLUMN_MEMBER("member","家庭成员"),COLUMN_TYPE("type","消费类型"),COLUMN_AMOUNT("amount","金额(元)"),COLUMN_PUR_SOURCE("pur_source","消费来源"),
	COLUMN_CREATE_DATE("create_date","创建时间"),COLUMN_MODIFY_DATE("modify_date","修改时间"),COLUMN_COMMENTS("comments","备注"),
	COLUMN_OPERATE("operate","操作员");
	
	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private OutstockColumnEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static OutstockColumnEnum getOutstockColumnKeyByValue(String values){
		for(OutstockColumnEnum outstockColumn : OutstockColumnEnum.values()){
			if(outstockColumn.getValue().equals(values)){
				return outstockColumn;
			}
		}
		return null;
	}
	
	public static OutstockColumnEnum getOutstockColumnValueByKey(String keys){
		for(OutstockColumnEnum outstockColumn : OutstockColumnEnum.values()){
			if(outstockColumn.getKey().equals(keys)){
				return outstockColumn;
			}
		}
		return null;
	}
	
}
