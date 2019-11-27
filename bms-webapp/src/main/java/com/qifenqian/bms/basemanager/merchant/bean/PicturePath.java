package com.qifenqian.bms.basemanager.merchant.bean;

import java.io.Serializable;

public class PicturePath implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7088932106537173148L;
	//00 个人身份证正面
	private String idCardOPath;
	//16 个人身份证反面
	private String idCardFPath;
	//02 营业执照
	private String bussinessPath;
	//03 开户证件
	private String openAccountPath;
	//07银行卡正面
	private String bankCardPath;
	public String getIdCardOPath() {
		return idCardOPath;
	}
	public void setIdCardOPath(String idCardOPath) {
		this.idCardOPath = idCardOPath;
	}
	public String getIdCardFPath() {
		return idCardFPath;
	}
	public void setIdCardFPath(String idCardFPath) {
		this.idCardFPath = idCardFPath;
	}
	public String getBussinessPath() {
		return bussinessPath;
	}
	public void setBussinessPath(String bussinessPath) {
		this.bussinessPath = bussinessPath;
	}
	public String getOpenAccountPath() {
		return openAccountPath;
	}
	public void setOpenAccountPath(String openAccountPath) {
		this.openAccountPath = openAccountPath;
	}
	public String getBankCardPath() {
		return bankCardPath;
	}
	public void setBankCardPath(String bankCardPath) {
		this.bankCardPath = bankCardPath;
	}
	
	

}
