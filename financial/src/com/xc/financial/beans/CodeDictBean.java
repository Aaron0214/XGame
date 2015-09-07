package com.xc.financial.beans;


public class CodeDictBean{
	
	private Integer id;
	
	private String typeValue;
	
	private String value;
	
	private String pidValue;
	
	private Integer type;
	
	private Integer pid;
	
	private String code;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getPidValue() {
		return pidValue;
	}

	public void setPidValue(String pidValue) {
		this.pidValue = pidValue;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
