package com.qifenqian.bms.merchant.reported.enums;
/**
 * 渠道编码
 * @author shili
 *
 */
public enum ChannelCodeType {
	支付宝("ALIPAY","支付宝"),
	微信("WX","微信");
	
	private String code;
	private String name;
	ChannelCodeType(String code,String name){
		this.code = code;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
