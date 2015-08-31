package com.xc.financial.enums.column;

public enum UserColumnEnum {

	COLUMN_BLANK("",""),COLUMN_INDEX("index","序号"),COLUMN_CODE("code","编码"),
	COLUMN_NAME("name","姓名"),COLUMN_SEX("sex","性别"),COLUMN_STATUS("status","状态"),
	COLUMN_ADDRESS("adress","地址"),COLUMN_USERNAME("username","用户名"),COLUMN_EMAIL("email","邮箱"),
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

	private UserColumnEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static UserColumnEnum getUserColumnKeyByValue(String values){
		for(UserColumnEnum userColumn : UserColumnEnum.values()){
			if(userColumn.getValue().equals(values)){
				return userColumn;
			}
		}
		return null;
	}
	
	public static UserColumnEnum getUserColumnValueByKey(String keys){
		for(UserColumnEnum userColumn : UserColumnEnum.values()){
			if(userColumn.getKey().equals(keys)){
				return userColumn;
			}
		}
		return null;
	}
	
}
