package com.qifenqian.bms.basemanager.admanage.bean;

import java.io.Serializable;
import java.util.Date;

public class TdMachineAdvert implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4638444087999185114L;
	//商户号
	private String custId;
	//设备号
	private String terminalNo;
	//设备类别  QINGTING-蜻蜓  QINGWA-青蛙
	private String machineType;
	//广告图片路径
	private String picture;
	//广告图片顺序
	private String sequence;
	//状态 00-可用 99-失效
	private String state;
	//创建人
	private String creator;
	//创建时间
	private Date createTime;
	//备注
	private String memo;
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
