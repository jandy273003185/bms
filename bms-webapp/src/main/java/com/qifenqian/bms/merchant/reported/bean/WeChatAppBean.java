package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class WeChatAppBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8898489885772662047L;
	//渠道
	private String channelNo;
	//商户号
	private String merchantCode;
	//商户名称
	private String custName;
	//门店名称
	private String storeName;
	//商户简称
	private String shortName;
	//法人姓名
	private String representativeName;
	//法人证件号
	private String representativeCertNo;
	//费率
	private String rate;
	//身份证有效起始期
	private String identityEffDate;
	//身份证有效截止期
	private String identityValDate;
	//行业编号
	private String industryCode;
	//商户地址省
	private String merchantProvince;
	//商户地址市
	private String merchantCity;
	//商户地址区
	private String merchantArea;
	//商户详细地址
	private String cprRegAddr;
	//结算账号名称
	private String accountNm;
	//结算账号
	private String accountNo;
	//开户支行名称
	private String interBankName;
	//开户行
	private String bank;
	//银行开户市
	private String bankCity;
	//身份证人像照
	private String legalCertAttribute1Path;
	//身份证国徽照
	private String legalCertAttribute2Path;
	//门头照路径
	private String doorPhotoPath;
	//店内照路径
	private String shopInteriorPath;
	//客服号码
	private String contactPhone;
	//手机号
	private String mobileNo;
	//邮箱
	private String email;
	//主体类型
	private String merchantProperty;
	//营业执照类型 1762-已三证合一    1763-未三证合一
	private String businessLicenceType;
	//经营场景
	private String businessScene;
	//联系邮箱
	private String contactEmail;
	//公众号APPID
	private String mpAppid;
	//公众号页面截图
	private String mpAppScreenShotsPath;
	//小程序APPID
	private String miniprogramAppid;
	//小程序页面截图
	private String miniprogramAppidPath;
	//应用APPID
	private String appAppid;
	//APP下载链接
	private String appDownloadUrl;
	//APP截图
	private String appAppidPath;
	//PC网站域名
	private String webUrl;
	//PC网站对应的公众号APPID
	private String webAppid;
	//网站授权函
	private String webUrlPath;
	public String getMpAppid() {
		return mpAppid;
	}
	public void setMpAppid(String mpAppid) {
		this.mpAppid = mpAppid;
	}
	public String getMpAppScreenShotsPath() {
		return mpAppScreenShotsPath;
	}
	public void setMpAppScreenShotsPath(String mpAppScreenShotsPath) {
		this.mpAppScreenShotsPath = mpAppScreenShotsPath;
	}
	public String getMiniprogramAppid() {
		return miniprogramAppid;
	}
	public void setMiniprogramAppid(String miniprogramAppid) {
		this.miniprogramAppid = miniprogramAppid;
	}
	public String getMiniprogramAppidPath() {
		return miniprogramAppidPath;
	}
	public void setMiniprogramAppidPath(String miniprogramAppidPath) {
		this.miniprogramAppidPath = miniprogramAppidPath;
	}
	public String getAppAppid() {
		return appAppid;
	}
	public void setAppAppid(String appAppid) {
		this.appAppid = appAppid;
	}
	public String getAppDownloadUrl() {
		return appDownloadUrl;
	}
	public void setAppDownloadUrl(String appDownloadUrl) {
		this.appDownloadUrl = appDownloadUrl;
	}
	public String getAppAppidPath() {
		return appAppidPath;
	}
	public void setAppAppidPath(String appAppidPath) {
		this.appAppidPath = appAppidPath;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getWebAppid() {
		return webAppid;
	}
	public void setWebAppid(String webAppid) {
		this.webAppid = webAppid;
	}
	public String getWebUrlPath() {
		return webUrlPath;
	}
	public void setWebUrlPath(String webUrlPath) {
		this.webUrlPath = webUrlPath;
	}
	public String getMerchantProperty() {
		return merchantProperty;
	}
	public String getLegalCertAttribute1Path() {
		return legalCertAttribute1Path;
	}
	public void setLegalCertAttribute1Path(String legalCertAttribute1Path) {
		this.legalCertAttribute1Path = legalCertAttribute1Path;
	}
	public String getLegalCertAttribute2Path() {
		return legalCertAttribute2Path;
	}
	public void setLegalCertAttribute2Path(String legalCertAttribute2Path) {
		this.legalCertAttribute2Path = legalCertAttribute2Path;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getDoorPhotoPath() {
		return doorPhotoPath;
	}
	public void setDoorPhotoPath(String doorPhotoPath) {
		this.doorPhotoPath = doorPhotoPath;
	}
	public String getShopInteriorPath() {
		return shopInteriorPath;
	}
	public void setShopInteriorPath(String shopInteriorPath) {
		this.shopInteriorPath = shopInteriorPath;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getIdentityEffDate() {
		return identityEffDate;
	}
	public void setIdentityEffDate(String identityEffDate) {
		this.identityEffDate = identityEffDate;
	}
	public String getIdentityValDate() {
		return identityValDate;
	}
	public void setIdentityValDate(String identityValDate) {
		this.identityValDate = identityValDate;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCprRegAddr() {
		return cprRegAddr;
	}
	public void setCprRegAddr(String cprRegAddr) {
		this.cprRegAddr = cprRegAddr;
	}
	
	public String getMerchantProvince() {
		return merchantProvince;
	}
	public void setMerchantProvince(String merchantProvince) {
		this.merchantProvince = merchantProvince;
	}
	public String getMerchantCity() {
		return merchantCity;
	}
	public void setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
	}
	public String getMerchantArea() {
		return merchantArea;
	}
	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public String getRepresentativeCertNo() {
		return representativeCertNo;
	}
	public void setRepresentativeCertNo(String representativeCertNo) {
		this.representativeCertNo = representativeCertNo;
	}
	public String getInterBankName() {
		return interBankName;
	}
	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
	}
	public String getAccountNm() {
		return accountNm;
	}
	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	
	
	
	
}