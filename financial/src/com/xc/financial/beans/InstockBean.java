package com.xc.financial.beans;

import java.math.BigDecimal;

public class InstockBean{

	private Integer index;
	
	private String code;
	
	private String member;
	
	private String typeValue;
	
	private BigDecimal amount;
	
	private String storeTypeValue;
	
	private String createDate;
	
	private String modifyDate;
	
	private String operate;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStoreTypeValue() {
		return storeTypeValue;
	}

	public void setStoreTypeValue(String storeTypeValue) {
		this.storeTypeValue = storeTypeValue;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

}
