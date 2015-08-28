package com.xc.financial.enums;

public enum FinancialColumnEnum {

	COLUMN_BLANK("",""),COLUMN_INDEX("index","序号"),COLUMN_CODE("code","编码"),
	COLUMN_MEMBER("member","家庭成员"),COLUMN_TYPE("type","存储类型"),COLUMN_AMOUNT("amount","金额(元)"),COLUMN_CREATE_DATE("create_date","创建时间"),
	COLUMN_MODIFY_DATE("modify_date","修改时间"),COLUMN_COMMENTS("comments","备注"),COLUMN_OPERATE("operate","操作员");
	
	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private FinancialColumnEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static FinancialColumnEnum getFinancialColumnKeyByValue(String values){
		for(FinancialColumnEnum financialColumn : FinancialColumnEnum.values()){
			if(financialColumn.getValue().equals(values)){
				return financialColumn;
			}
		}
		return null;
	}
	
	public static FinancialColumnEnum getFinancialColumnValueByKey(String keys){
		for(FinancialColumnEnum financialColumn : FinancialColumnEnum.values()){
			if(financialColumn.getKey().equals(keys)){
				return financialColumn;
			}
		}
		return null;
	}
	
}
