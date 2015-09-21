package com.xc.financial.enums.column;

public enum RoleColumnEnum {

	COLUMN_BLANK("",""),COLUMN_ID("id","编号"),COLUMN_NAME("name","角色名称"),
	COLUMN_TYPE("status","状态"),COLUMN_CREATE_DATE("create_date","创建时间"),COLUMN_MODIFY_DATE("modify_date","修改时间"),
	COLUMN_COMMENTS("comments","备注"),COLUMN_OPERATE("operate","操作员");
	
	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private RoleColumnEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static RoleColumnEnum getRoleColumnKeyByValue(String values){
		for(RoleColumnEnum roleColumnEnum : RoleColumnEnum.values()){
			if(roleColumnEnum.getValue().equals(values)){
				return roleColumnEnum;
			}
		}
		return null;
	}
	
	public static RoleColumnEnum getRoleColumnValueByKey(String keys){
		for(RoleColumnEnum roleColumnEnum : RoleColumnEnum.values()){
			if(roleColumnEnum.getKey().equals(keys)){
				return roleColumnEnum;
			}
		}
		return null;
	}
	
}
