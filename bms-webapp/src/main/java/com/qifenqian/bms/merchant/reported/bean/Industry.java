package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class Industry implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -322464507383872217L;



	private String industryCode;//行业编号
	
	private String industryName;//行业名字

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}


	
	
}
