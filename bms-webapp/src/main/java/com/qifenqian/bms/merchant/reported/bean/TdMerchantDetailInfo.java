package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class TdMerchantDetailInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6376793115162809719L;

	private String patchNo;//报备批次号
	
	private String merchantCode;//商户编号
	
	private String reportStatus;//商户进件状态
	
	private String fileStatus;//商户文件状态
	
	private String outMerchantCode;//外部商户号
	
	private String remark;//备注
	
	private String custName;//商户名称
	
	private String channelNo;//报备渠道
	
	private String id;//报备编号
	
	private String reportTime;//报备时间
	
	private String provCode;//省份编号
	
	private String cityCode;//城市编号
	
	private String contryCode;//区县编号
	
	private String bankCode;//开户行号
	
	private String branchBankCode;//开户支行联行号
	
	private String branchBankName;//开户支行名称
	
	private String mobileNo;//银行卡预留手机号
	
	private String customerPhone;//客服电话
	
	private String qq;//联系人QQ
	
	private String detailStatus;//商户报备状态
	
	private String powerId;//支付功能id
	
	private String appId;//支付功能id
	
	private String category;//支付功能id
	
	private String loginNo; //翼支付企业LoginNo
	
	private String bestMerchantType;  //翼支付企业类型
	
	private String bestpayMctNo;//翼支付返回商户号
	
	private String channelCode;
	
	private String resultMsg; //返回信息
	
	private String shortName;
	
	private String email;
	
	private String startModifyTime;

	private String endModifyTime;
	
	
	
	public String getStartModifyTime() {
		return startModifyTime;
	}

	public void setStartModifyTime(String startModifyTime) {
		this.startModifyTime = startModifyTime;
	}

	public String getEndModifyTime() {
		return endModifyTime;
	}

	public void setEndModifyTime(String endModifyTime) {
		this.endModifyTime = endModifyTime;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getContryCode() {
		return contryCode;
	}

	public void setContryCode(String contryCode) {
		this.contryCode = contryCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchBankCode() {
		return branchBankCode;
	}

	public void setBranchBankCode(String branchBankCode) {
		this.branchBankCode = branchBankCode;
	}

	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPatchNo() {
		return patchNo;
	}

	public void setPatchNo(String patchNo) {
		this.patchNo = patchNo;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getOutMerchantCode() {
		return outMerchantCode;
	}

	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoginNo() {
		return loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	public String getBestMerchantType() {
		return bestMerchantType;
	}

	public void setBestMerchantType(String bestMerchantType) {
		this.bestMerchantType = bestMerchantType;
	}

	public String getBestpayMctNo() {
		return bestpayMctNo;
	}

	public void setBestpayMctNo(String bestpayMctNo) {
		this.bestpayMctNo = bestpayMctNo;
	}
	
	
}
