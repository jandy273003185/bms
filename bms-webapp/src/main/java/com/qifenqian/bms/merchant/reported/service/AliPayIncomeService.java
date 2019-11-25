package com.qifenqian.bms.merchant.reported.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.AliPayCoBean;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.jellyfish.bean.agentMerSign.ContactInfo;
import com.qifenqian.jellyfish.bean.agentMerSign.alipay.AlipayOpenAgentConfirmReq;
import com.qifenqian.jellyfish.bean.agentMerSign.alipay.AlipayOpenAgentConfirmRes;
import com.qifenqian.jellyfish.bean.agentMerSign.alipay.AlipayOpenAgentCreateReq;
import com.qifenqian.jellyfish.bean.agentMerSign.alipay.AlipayOpenAgentCreateRes;
import com.qifenqian.jellyfish.bean.agentMerSign.alipay.AlipayOpenAgentFacetofaceSignReq;
import com.qifenqian.jellyfish.bean.agentMerSign.alipay.AlipayOpenAgentFacetofaceSignRes;
import com.qifenqian.jellyfish.merRegistApi.IAlipayAgentMerRegistService;


@Service
public class AliPayIncomeService {
	
	private static final Logger logger = LoggerFactory.getLogger(AliPayIncomeService.class);
	
	@Reference 
	private IAlipayAgentMerRegistService iAgentMerRegistService;
	
	@Autowired
	private FmIncomeService fmIncomeService;
	
	public Object aliPayReported(AliPayCoBean aliPayCoBean) throws Exception {
		//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
		logger.info("支付宝商户报备start.");
		TdMerchantDetailInfo info = new TdMerchantDetailInfo();
		info.setId(GenSN.getSN());
		//报备批次号
		String patchNo = GenSN.getSN();
		info.setPatchNo(patchNo);
		info.setMerchantCode(aliPayCoBean.getMerchantCode());
		info.setChannelNo(aliPayCoBean.getChannelNo());
		info.setReportStatus("E");

		//info.setBankCode("5551458");
		//info.setBranchBankName("华夏银行");
		//info.setMobileNo("15666666666");
		logger.debug("插入td_merchant_report表数据：{}", JSONObject.toJSONString(info));
		fmIncomeService.insertTdMerchantReport(info);
		info.setReportStatus("99");
		logger.debug("插入td_merchant_detail_info表数据：{}", JSONObject.toJSONString(info));
		fmIncomeService.inserTdMerchantDetailInfo(info);
		
		//调用支付宝渠道
		
		//开启报备事务
		AlipayOpenAgentCreateReq aliReqBean = new AlipayOpenAgentCreateReq();
		aliReqBean.setAccount(aliPayCoBean.getAccount());
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setContactEmail(aliPayCoBean.getContactEmail());
		contactInfo.setContactMobile(aliPayCoBean.getContactMobile());
		contactInfo.setContactName(aliPayCoBean.getContactName());
		aliReqBean.setContactInfo(contactInfo);
		logger.debug("开启支付宝报备事务：{}", JSONObject.toJSONString(aliReqBean));
		AlipayOpenAgentCreateRes res = alipayOpenAgentCreate(aliReqBean);
		logger.debug("支付宝报备事务返回值：{}", JSONObject.toJSONString(res));
		//返回批次号和状态
		info.setZfbBatchNo(res.getBatchNo());
		info.setZfbBatchStatus(res.getBatchStatus());
		
		//待签约当面付产品
		AlipayOpenAgentFacetofaceSignReq aliSignReqBean = new AlipayOpenAgentFacetofaceSignReq();
		
		//批次号
		aliSignReqBean.setBatchNo(res.getBatchNo());
		//经营类目
		aliSignReqBean.setMccCode(aliPayCoBean.getMccCode());
		//特殊资质
		if (StringUtils.isNotBlank(aliPayCoBean.getQualificationPath())) {
			aliSignReqBean.setSpecialLicensePic(aliPayCoBean.getQualificationPath());
		}
		
		//营业执照号码
		aliSignReqBean.setBusinessLicenseNo(aliPayCoBean.getBusinessLicense());
		//营业执照
		aliSignReqBean.setBusinessLicensePic(aliPayCoBean.getBusinessPhotoPath());
		//营业执照授权函
		if (StringUtils.isNotBlank(aliPayCoBean.getBusinessAuthPhotoPath())) {
			aliSignReqBean.setBusinessLicenseAuthPic(aliPayCoBean.getBusinessAuthPhotoPath());
		}
		
		//营业执照是否长期有效
		aliSignReqBean.setLongTerm("2099-12-31".equals(aliPayCoBean.getBusinessTerm()));
		//营业期限
		if (StringUtils.isNotBlank(aliPayCoBean.getBusinessTerm()) && !aliSignReqBean.getLongTerm()) {
			aliSignReqBean.setDateLimitation(aliPayCoBean.getBusinessTerm());
		}
		if (StringUtils.isNotBlank(aliPayCoBean.getShopInteriorPath())) {
			//店铺内景照
			aliSignReqBean.setShopScenePic(aliPayCoBean.getShopInteriorPath());
		}
		
		//店铺门头照
		aliSignReqBean.setShopSignBoardPic(aliPayCoBean.getDoorPhotoPath());
		logger.debug("支付宝商户签约：{}", JSONObject.toJSONString(aliSignReqBean));
		AlipayOpenAgentFacetofaceSignRes signRes = alipayOpenAgentFacetofaceSign(aliSignReqBean);
		logger.debug("支付宝商户签约返回值：{}", JSONObject.toJSONString(signRes));
		
		//提交事务
		AlipayOpenAgentConfirmReq aliConfirmReqBean = new AlipayOpenAgentConfirmReq();
		aliConfirmReqBean.setBatchNo(res.getBatchNo());
		logger.debug("支付宝商户签约提交：{}", JSONObject.toJSONString(aliConfirmReqBean));
		AlipayOpenAgentConfirmRes confirmRes = alipayOpenAgentConfirm(aliConfirmReqBean);
		logger.debug("支付宝商户签约提交返回值：{}", JSONObject.toJSONString(confirmRes));
		if (!"10000".equals(confirmRes.getCode())) {
			throw new Exception();
		}
		
		//更新
		info.setReportStatus("O");
		info.setZfbUserId(confirmRes.getUserId());
		info.setZfbAuthAppId(confirmRes.getAuthAppId());
		info.setZfbAppAuthToken(confirmRes.getAppAuthToken());
		info.setZfbAppRefreshToken(confirmRes.getAppRefreshToken());
		info.setZfbExpiresIn(confirmRes.getExpiresIn());
		info.setZfbReExpiresIn(confirmRes.getReExpiresIn());
		logger.debug("更新td_merchant_report和td_merchant_detail_info表数据：{}", JSONObject.toJSONString(info));
		fmIncomeService.UpdateMerReportAndMerDetailInfo(info, "00");
		logger.info("支付宝商户报备end.");
		return confirmRes;
	}
	
	/**
	 * 开启支付宝报备事务
	 * @param aliReqBean
	 * @return
	 */
	public AlipayOpenAgentCreateRes alipayOpenAgentCreate(AlipayOpenAgentCreateReq aliCreateReqBean) {
		AlipayOpenAgentCreateRes res = iAgentMerRegistService.alipayOpenAgentCreate(aliCreateReqBean);
		return res;
	}
	
	/**
	 * 支付宝签约当面付
	 * @param aliReqBean
	 * @return
	 */
	public AlipayOpenAgentFacetofaceSignRes alipayOpenAgentFacetofaceSign(AlipayOpenAgentFacetofaceSignReq aliSignReqBean) {
		AlipayOpenAgentFacetofaceSignRes res = iAgentMerRegistService.alipayOpenAgentFacetofaceSign(aliSignReqBean);
		return res;
	}
	
	/**
	 * 支付宝提交代商户签约
	 * @param aliReqBean
	 * @return
	 */
	public AlipayOpenAgentConfirmRes alipayOpenAgentConfirm(AlipayOpenAgentConfirmReq aliConfirmReqBean) {
		AlipayOpenAgentConfirmRes res = iAgentMerRegistService.alipayOpenAgentConfirm(aliConfirmReqBean);
		return res;
	}
}
