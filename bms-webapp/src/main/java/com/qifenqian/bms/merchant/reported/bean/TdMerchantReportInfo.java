package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class TdMerchantReportInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8673547233560020026L;
	//报备编号
	private String id;
	//商户编号
	private String merchantCode;
	//报备批次号
	private String patchNo;
	//报备渠道
	private String channelNo;
	//报备状态 : Y 提交报备, N 未报备 ,E 异常,F 报备提交失败,O 报备成功
	private String reportStatus;
	//报备时间
	private String reportTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getPatchNo() {
		return patchNo;
	}
	public void setPatchNo(String patchNo) {
		this.patchNo = patchNo;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	
	
	
}
