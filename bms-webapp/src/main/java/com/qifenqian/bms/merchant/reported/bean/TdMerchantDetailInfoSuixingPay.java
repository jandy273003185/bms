package com.qifenqian.bms.merchant.reported.bean;

import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;

import java.io.Serializable;

/**
 * @description:
 * @author: LiBin
 * @String: 2019/12/25 0025 17:04
 */
public class TdMerchantDetailInfoSuixingPay extends TdMerchantReportDetail implements Serializable {
    /**
     * 报备批次号
     */
    private String patchNo;

    /**
     * 商户号
     */
    private String merchantCode;

    /**
     * 商户名称
     */
    private String custName;

    /**
     * 联系人手机号
     */
    private String mobileNo;

    /**
     * 资质类型
     */
    private String suiXingMerchantType;

    /**
     * 商户类型
     */
    private String mecTypeFlag;

    /**
     * 营业执照注册名称
     */
    private String cprRegNmCn;

    /**
     * 营业执照注册号
     */
    private String registCode;

    /**
     * 注册省
     */
    private String merchantProvince;

    /**
     * 注册市
     */
    private String merchantCity;

    /**
     * 注册地区
     */
    private String merchantArea;

    /**
     * 注册详细地址
     */
    private String cprRegAddr;

    /**
     * 行业信息mcc
     */
    private String industryCode;

    /**
     * 法人姓名
     */
    private String representativeName;

    /**
     * 法人证件类型
     */
    private String representativeCertType;

    /**
     * 法人证件号
     */
    private String representativeCertNo;

    /**
     * 结算账户名
     */
    private String actNm;

    /**
     * 结算账户类型
     */
    private String actType;

    /**
     * 结算证件号
     */
    private String certifyNo;

    /**
     * 结算账号
     */
    private String bankCardNo;

    /**
     * 开户行
     */
    private String suiXinBank;

    /**
     * 开户行所在省
     */
    private String bankProvince;

    /**
     * 开户行所在市
     */
    private String bankCity;

    /**
     * 开户支行名称
     */
    private String interbankName;

    /**
     * 费率
     */
    private String rate;

    /**
     * 上传文件返回标识码
     */
    private String taskCode;

    /**
     * 联行号
     */
    private String lbnkNo;

    /**
     * 所属总店商户编号
     */
    private String parentMno;

    /**
     * 分店是否独立结算
     */
    private String independentModel;

    /**
     * 回调地址
     */
    private String merUrl;

    /**
     * 上传文件路径
     */
    private String filePath;

    /**
     * 渠道返回商户号
     */
    private String outMerchantCode;

    /**
     * 签约二维码
     */
    private String signQrcode;

    /**
     * 微信申请单编号
     */
    private String wxApplyNo;

    /**
     * 微信实名认证状态
     */
    private String idenStatus;

    /**
     * 渠道微信子商户号
     */
    private String wxChildNo;

    /**
     * 渠道支付宝子商户号
     */
    private String zfbChildNo;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 标识状态 1 待审核通过后更新
     */
    private String flagStatus;

    /**
     * 返回信息
     */
    private String resultMsg;

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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSuiXingMerchantType() {
        return suiXingMerchantType;
    }

    public void setSuiXingMerchantType(String suiXingMerchantType) {
        this.suiXingMerchantType = suiXingMerchantType;
    }

    public String getMecTypeFlag() {
        return mecTypeFlag;
    }

    public void setMecTypeFlag(String mecTypeFlag) {
        this.mecTypeFlag = mecTypeFlag;
    }

    public String getCprRegNmCn() {
        return cprRegNmCn;
    }

    public void setCprRegNmCn(String cprRegNmCn) {
        this.cprRegNmCn = cprRegNmCn;
    }

    public String getRegistCode() {
        return registCode;
    }

    public void setRegistCode(String registCode) {
        this.registCode = registCode;
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

    public String getCprRegAddr() {
        return cprRegAddr;
    }

    public void setCprRegAddr(String cprRegAddr) {
        this.cprRegAddr = cprRegAddr;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getRepresentativeCertType() {
        return representativeCertType;
    }

    public void setRepresentativeCertType(String representativeCertType) {
        this.representativeCertType = representativeCertType;
    }

    public String getRepresentativeCertNo() {
        return representativeCertNo;
    }

    public void setRepresentativeCertNo(String representativeCertNo) {
        this.representativeCertNo = representativeCertNo;
    }

    public String getActNm() {
        return actNm;
    }

    public void setActNm(String actNm) {
        this.actNm = actNm;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getCertifyNo() {
        return certifyNo;
    }

    public void setCertifyNo(String certifyNo) {
        this.certifyNo = certifyNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getSuiXinBank() {
        return suiXinBank;
    }

    public void setSuiXinBank(String suiXinBank) {
        this.suiXinBank = suiXinBank;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getInterbankName() {
        return interbankName;
    }

    public void setInterbankName(String interbankName) {
        this.interbankName = interbankName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getLbnkNo() {
        return lbnkNo;
    }

    public void setLbnkNo(String lbnkNo) {
        this.lbnkNo = lbnkNo;
    }

    public String getParentMno() {
        return parentMno;
    }

    public void setParentMno(String parentMno) {
        this.parentMno = parentMno;
    }

    public String getIndependentModel() {
        return independentModel;
    }

    public void setIndependentModel(String independentModel) {
        this.independentModel = independentModel;
    }

    public String getMerUrl() {
        return merUrl;
    }

    public void setMerUrl(String merUrl) {
        this.merUrl = merUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOutMerchantCode() {
        return outMerchantCode;
    }

    public void setOutMerchantCode(String outMerchantCode) {
        this.outMerchantCode = outMerchantCode;
    }

    public String getSignQrcode() {
        return signQrcode;
    }

    public void setSignQrcode(String signQrcode) {
        this.signQrcode = signQrcode;
    }

    public String getWxApplyNo() {
        return wxApplyNo;
    }

    public void setWxApplyNo(String wxApplyNo) {
        this.wxApplyNo = wxApplyNo;
    }

    public String getIdenStatus() {
        return idenStatus;
    }

    public void setIdenStatus(String idenStatus) {
        this.idenStatus = idenStatus;
    }

    public String getWxChildNo() {
        return wxChildNo;
    }

    public void setWxChildNo(String wxChildNo) {
        this.wxChildNo = wxChildNo;
    }

    public String getZfbChildNo() {
        return zfbChildNo;
    }

    public void setZfbChildNo(String zfbChildNo) {
        this.zfbChildNo = zfbChildNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(String flagStatus) {
        this.flagStatus = flagStatus;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TdMerchantDetailInfoSuixingPay{");
        sb.append("patchNo='").append(patchNo).append('\'');
        sb.append(", merchantCode='").append(merchantCode).append('\'');
        sb.append(", custName='").append(custName).append('\'');
        sb.append(", mobileNo='").append(mobileNo).append('\'');
        sb.append(", suiXingMerchantType='").append(suiXingMerchantType).append('\'');
        sb.append(", mecTypeFlag='").append(mecTypeFlag).append('\'');
        sb.append(", cprRegNmCn='").append(cprRegNmCn).append('\'');
        sb.append(", registCode='").append(registCode).append('\'');
        sb.append(", merchantProvince='").append(merchantProvince).append('\'');
        sb.append(", merchantCity='").append(merchantCity).append('\'');
        sb.append(", merchantArea='").append(merchantArea).append('\'');
        sb.append(", cprRegAddr='").append(cprRegAddr).append('\'');
        sb.append(", industryCode='").append(industryCode).append('\'');
        sb.append(", representativeName='").append(representativeName).append('\'');
        sb.append(", representativeCertType='").append(representativeCertType).append('\'');
        sb.append(", representativeCertNo='").append(representativeCertNo).append('\'');
        sb.append(", actNm='").append(actNm).append('\'');
        sb.append(", actType='").append(actType).append('\'');
        sb.append(", certifyNo='").append(certifyNo).append('\'');
        sb.append(", bankCardNo='").append(bankCardNo).append('\'');
        sb.append(", suiXinBank='").append(suiXinBank).append('\'');
        sb.append(", bankProvince='").append(bankProvince).append('\'');
        sb.append(", bankCity='").append(bankCity).append('\'');
        sb.append(", interbankName='").append(interbankName).append('\'');
        sb.append(", rate='").append(rate).append('\'');
        sb.append(", taskCode='").append(taskCode).append('\'');
        sb.append(", lbnkNo='").append(lbnkNo).append('\'');
        sb.append(", parentMno='").append(parentMno).append('\'');
        sb.append(", independentModel='").append(independentModel).append('\'');
        sb.append(", merUrl='").append(merUrl).append('\'');
        sb.append(", filePath='").append(filePath).append('\'');
        sb.append(", outMerchantCode='").append(outMerchantCode).append('\'');
        sb.append(", signQrcode='").append(signQrcode).append('\'');
        sb.append(", wxApplyNo='").append(wxApplyNo).append('\'');
        sb.append(", idenStatus='").append(idenStatus).append('\'');
        sb.append(", wxChildNo='").append(wxChildNo).append('\'');
        sb.append(", zfbChildNo='").append(zfbChildNo).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", modifyTime='").append(modifyTime).append('\'');
        sb.append(", flagStatus='").append(flagStatus).append('\'');
        sb.append(", resultMsg='").append(resultMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
