package com.sevenpay.bms.basemanager.busstransfer.bean;

public class BussTransferExcel {
	private String orderId;
	private String orderName;
	private String orderDesc;
	private String payerCustId;
	private String payerCustName;
	private String payeeCustId;
	private String payeeCustName;
	private String orderCreateTime;
	private String sumAmt;
	private String orderState;
	private String workDate;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getPayerCustId() {
		return payerCustId;
	}

	public void setPayerCustId(String payerCustId) {
		this.payerCustId = payerCustId;
	}

	public String getPayerCustName() {
		return payerCustName;
	}

	public void setPayerCustName(String payerCustName) {
		this.payerCustName = payerCustName;
	}

	public String getPayeeCustId() {
		return payeeCustId;
	}

	public void setPayeeCustId(String payeeCustId) {
		this.payeeCustId = payeeCustId;
	}

	public String getPayeeCustName() {
		return payeeCustName;
	}

	public void setPayeeCustName(String payeeCustName) {
		this.payeeCustName = payeeCustName;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
}
