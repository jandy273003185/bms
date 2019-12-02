package com.qifenqian.bms.merchant.reported.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppAreaInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppBean;
import com.qifenqian.bms.merchant.reported.dao.WeChatAppMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.WeChatAppMapper;
import com.qifenqian.jellyfish.api.merregist.WxpayAgentMerRegistService;
import com.qifenqian.jellyfish.bean.enums.BusinessStatus;
import com.qifenqian.jellyfish.bean.enums.GetwayStatus;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistUpgradeReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistUpgradeResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgrntMerRegistQueryReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgrntMerRegistQueryResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgrntMerRegistUpgradeQueryReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgrntMerRegistUpgradeQueryResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinReintroduceWithdrawalsReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinReintroduceWithdrawalsResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinWithdrawalStateQueryReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinWithdrawalStateQueryResp;
import com.seven.micropay.channel.domain.UpgradeApplicatioMerchanelReq;

@Service
public class WeChatAppService {
	
	private Logger logger = LoggerFactory.getLogger(WeChatAppService.class);
	
	@Autowired
	private WeChatAppMapperDao weChatAppMapperDao;
	
	@Autowired
	private WeChatAppMapper weChatAppMapper;
	
	@Autowired
	private FmIncomeService fmIncomeService;
	
	@Reference
	private WxpayAgentMerRegistService wxpayAgentMerRegistService;
	
	
	/**
	 * 微信报备
	 * @param cr
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> weChatAppReported(WeChatAppBean cr) throws Exception {
		logger.info("微信进件报备start..");
		Map<String, Object> result = new HashMap<String, Object>();
		//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
		TdMerchantDetailInfo info = new TdMerchantDetailInfo();
		info.setId(GenSN.getSN());
		String patchNo = GenSN.getSN();
		info.setPatchNo(patchNo);
		info.setMerchantCode(cr.getMerchantCode().trim());
		info.setChannelNo(cr.getChannelNo());
		info.setReportStatus("E");
		info.setProvCode(cr.getMerchantProvince());
		info.setCityCode(cr.getMerchantCity());
		info.setContryCode(cr.getMerchantArea());
		info.setBankCode(cr.getBank());
		//结算账户
		info.setAccountNumber(cr.getAccountNo());
		info.setBranchBankName(cr.getInterBankName());
		info.setMobileNo(cr.getMobileNo());
		logger.debug("插入td_merchant_report表数据：{}", JSONObject.toJSONString(info));
		fmIncomeService.insertTdMerchantReport(info);
		info.setReportStatus("99");
		logger.debug("插入td_merchant_detail_info表数据：{}", JSONObject.toJSONString(info));
		fmIncomeService.inserTdMerchantDetailInfo(info);
		
		
		//微信报备
		WeiXinAgentMerRegistReq req = new WeiXinAgentMerRegistReq();
		//业务申请编号
		req.setBusinessCode(patchNo);
		// 身份证人像面照片
		req.setIdCardCopy(cr.getLegalCertAttribute1Path());
		// 身份证国徽面
		req.setIdCardNational(cr.getLegalCertAttribute2Path());
		// 门店门口照片
		req.setStoreEntrancePic(cr.getDoorPhotoPath());
		// 店内环境照片
		req.setIndoorPic(cr.getShopInteriorPath());
		// 身份证姓名
		req.setIdCardName(cr.getRepresentativeName());
		// 身份证号码
		req.setIdCardNumber(cr.getRepresentativeCertNo());
		// 身份证有效期限
		String idCardValidTime = "[\"" + cr.getIdentityEffDate() + "\",\"" + ("2099-12-31".equals(cr.getIdentityValDate()) ? "长期" : cr.getIdentityValDate()) + "\"]";
		req.setIdCardValidTime(idCardValidTime);
		// 开户名称
		req.setAccountName(cr.getAccountNm());
		// 开户银行
		req.setAccountBank(cr.getBank());
		// 开户银行省市编码
		req.setBankAddressCode(cr.getBankCity());
		// 银行账号
		req.setAccountNumber(cr.getAccountNo());
		// 门店名称
		req.setStoreName(cr.getStoreName());
		// 门店省市编码
		req.setStoreAddressCode(cr.getMerchantArea());
		// 门店街道名称
		req.setStoreStreet(cr.getCprRegAddr());
		// 商户简称
		req.setMerchantShortname(cr.getCustName());
		// 客服电话
		req.setServicePhone(cr.getContactPhone());
		// 售卖商品/提供服务描述
		req.setProductDesc(cr.getIndustryCode());
		// 费率
		req.setRate(cr.getRate());
		// 超级管理员姓名
		req.setContact(cr.getRepresentativeName());
		// 手机号码
		req.setContactPhone(cr.getMobileNo());
		// 管理员邮箱
		req.setContactEmail(cr.getEmail());
			
		logger.info("-----------------微信进件请求报文：" + JSONObject.toJSONString(req));
		WeiXinAgentMerRegistResp wxregResp = wxpayAgentMerRegistService.microMerRegist(req);
		logger.info("-----------------微信进件响应报文：" + JSONObject.toJSONString(wxregResp));
	    String reportState = null;
	    if(BusinessStatus.SUCCESS.equals(wxregResp.getSubCode())) {
    		info.setReportStatus("O");
    		reportState = "00";
            info.setFileStatus("Y");
            info.setApplymentId(wxregResp.getApplymentId());
            result.put("data", wxregResp);
			result.put("message", "报备成功");
			result.put("result", "SUCCESS");
	    } else {
           logger.error("微信进件明确失败：{}", wxregResp.getSubMsg());
           info.setResultMsg(wxregResp.getSubMsg());
		   reportState = "99";
		   result.put("message", wxregResp.getSubMsg());
		   result.put("result", "FAIL");
	    }
	    logger.debug("更新td_merchant_report和td_merchant_detail_info表数据：{}", JSONObject.toJSONString(info));
	    UpdateMerReportAndMerDetailInfo(info, reportState);
	    logger.info("微信进件报备end..");
		return result; 
		
	}
	
	public void UpdateMerReportAndMerDetailInfo(TdMerchantDetailInfo tdInfo, String status) {

	   weChatAppMapper.updateTdMerchantReport(tdInfo);
	   tdInfo.setReportStatus(status);
	   weChatAppMapper.updateTdMerchantDetailInfo(tdInfo);
	}
	
	
	public WeiXinAgrntMerRegistQueryResp microMerRegistQuery(String applymentId) throws Exception {
		WeiXinAgrntMerRegistQueryReq registQueryReq = new WeiXinAgrntMerRegistQueryReq();
		registQueryReq.setApplymentId(applymentId);
		registQueryReq.setBusinessCode("123");
		logger.info("查询微信进件申请单入参：{}", registQueryReq);
		WeiXinAgrntMerRegistQueryResp microMerRegistQuery = wxpayAgentMerRegistService.microMerRegistQuery(registQueryReq);
		logger.info("查询微信进件申请单返回值：{}", microMerRegistQuery);
		return microMerRegistQuery;
	}

	/**
	 * 查询微信省份
	 * @return
	 */
	public List<WeChatAppAreaInfo> getProvinceName() {
		return weChatAppMapperDao.getProvinceName();
	}

	/**
	 * 微信升级报备
	 * @param cr
	 * @return
	 */
	public Map<String, Object> weChatAppUpgradeReported(WeChatAppBean cr) {
		
		logger.info("-----------------微信升级进件开始-----------------");
		
		Map<String, Object> result = new HashMap<String, Object>();
		WeiXinAgentMerRegistUpgradeReq req = new WeiXinAgentMerRegistUpgradeReq();
		TdMerchantDetailInfo info = new TdMerchantDetailInfo();
		try {
			//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
			info.setId(GenSN.getSN()); 
			String patchNo = GenSN.getSN();
			info.setPatchNo(patchNo); 
			info.setMerchantCode(cr.getMerchantCode().trim());
			info.setChannelNo(cr.getChannelNo()); 
			info.setReportStatus("E");
			info.setProvCode(cr.getMerchantProvince());
			info.setCityCode(cr.getMerchantCity());
			info.setContryCode(cr.getMerchantArea());
			info.setBranchBankName(cr.getInterBankName());
			info.setMobileNo(cr.getMobileNo());
			logger.debug("插入td_merchant_report表数据：{}", JSONObject.toJSONString(info));
			fmIncomeService.insertTdMerchantReport(info); 
			info.setReportStatus("99");
			info.setRemark("weChatUpgrade");//备注值为weChatUpgrade则是微信升级信息
			logger.debug("插入td_merchant_detail_info表数据：{}",
			JSONObject.toJSONString(info));
			fmIncomeService.inserTdMerchantDetailInfo(info);
			 
			String businessTermTime = "[\"" + cr.getBusinessEffectiveTerm() + "\",\"" + ("2099-12-31".equals(cr.getBusinessTerm()) ? "长期" : cr.getBusinessTerm()) + "\"]";
			String businessSceneList = "[" +cr.getBusinessScene()+ "]";
			req.setSubMchId(cr.getOutMerchantCode());       		         //小微商户的商户号
			req.setOrganizationType(cr.getMerchantProperty());               //主体类型
			req.setBusinessLicenseCopy(cr.getBusinessPhotoPath());		     //营业执照扫描件
			req.setBusinessLicenseNumber(cr.getBusinessLicense());           //营业执照注册号
			req.setMerchantName(cr.getCustName());                           //商户名称(营业执照上的商户名称)
			req.setCompanyAddress(cr.getCprRegAddr());                       //注册地址
			req.setLegalPerson(cr.getRepresentativeName());                  //经营者姓名/法定代表人(营业执照上的经营者/法人姓名)
			req.setBusinessTime(businessTermTime);                           //营业期限
			req.setBusinessLicenceType("1762");                              //营业执照类型
			req.setAccountName(cr.getAccountNm());                           //开户名称
			req.setAccountBank(cr.getBank());                                //开户银行
			req.setBankAddressCode(cr.getBankCity());                        //开户银行省市编码
			req.setBankName(cr.getInterBankName());                          //开户银行全称（含支行）
			req.setAccountNumber(cr.getAccountNo());                         //银行卡号
			req.setMerchantShortname(cr.getShortName());                     //商户简称
			req.setBusiness(cr.getRateId());                                 //费率结算规则ID
			req.setBusinessScene(businessSceneList);                         //经营场景
			req.setContactEmail(cr.getAttentionEmail());                     //联系邮箱
			req.setMpAppid(cr.getMpAppid());                                 //公众号APPID
			req.setMpAppScreenShots(cr.getMpAppScreenShotsPath());           //公众号页面截图
			req.setMiniprogramAppid(cr.getMiniprogramAppid());               //小程序APPID
			req.setMiniprogramScreenShots(cr.getMiniprogramAppidPath());     //小程序页面截图
			req.setAppAppid(cr.getAppAppid());                               //应用APPID
			req.setAppScreenShots(cr.getAppAppidPath());                     //APP截图
			req.setAppDownloadUrl(cr.getAppDownloadUrl());                   //APP下载衔接
			req.setWebUrl(cr.getWebUrl());                                   //PC网站域名
			req.setWebAuthoriationLetter(cr.getWebUrlPath());                //网站授权函
			req.setWebAppid(cr.getWebAppid());                               //PC网站对应的公众号APPID
			
			logger.info("-----------------微信升级进件请求报文：" + JSONObject.toJSONString(req));
			WeiXinAgentMerRegistUpgradeResp wxregUpgradeResp = wxpayAgentMerRegistService.microMerRegistUpgrade(req);
			logger.info("-----------------微信升级进件响应报文：" + JSONObject.toJSONString(wxregUpgradeResp));
			
			String reportState = null;
		    if(BusinessStatus.SUCCESS.equals(wxregUpgradeResp.getSubCode())) {
	    		info.setReportStatus("O");
	    		reportState = "00";
	            info.setFileStatus("Y");
	            result.put("data", wxregUpgradeResp);
				result.put("message", "报备成功");
				result.put("result", "SUCCESS");
		    } else {
	           logger.error("微信进件明确失败：{}", wxregUpgradeResp.getSubMsg());
	           info.setResultMsg(wxregUpgradeResp.getSubMsg());
			   reportState = "99";
			   result.put("message", wxregUpgradeResp.getSubMsg());
			   result.put("result", "FAIL");
		    }
		    logger.debug("更新td_merchant_report和td_merchant_detail_info表数据：{}", JSONObject.toJSONString(info));
		    UpdateMerReportAndMerDetailInfo(info, reportState);
		    logger.info("-----------------微信升级进件结束-----------------");
		} catch (Exception e) {
			result.put("message", e);
			result.put("result", "FAIL");
		}
		return result;
	}
	
	/**
	 * 升级查询
	 * @param applymentId
	 * @return
	 * @throws Exception
	 */
	public WeiXinAgrntMerRegistUpgradeQueryResp microMerRegistUpgradeQuery(String subMchId) throws Exception {
		WeiXinAgrntMerRegistUpgradeQueryReq registUpgradeQueryReq = new WeiXinAgrntMerRegistUpgradeQueryReq();
		registUpgradeQueryReq.setSubMchId(subMchId);
		logger.info("查询微信进件申请单入参：{}", registUpgradeQueryReq);
		WeiXinAgrntMerRegistUpgradeQueryResp microMerRegistQuery = wxpayAgentMerRegistService.microMerRegistUpgradeQuery(registUpgradeQueryReq);
		logger.info("查询微信进件申请单返回值：{}", microMerRegistQuery);
		return microMerRegistQuery;
	}
	/**
	 * 微信查询结算账户
	 * @param cr
	 * @return
	 */
	public JSONObject querySettlement(WeChatAppBean cr) {
		JSONObject object = new JSONObject();
		UpgradeApplicatioMerchanelReq req = new UpgradeApplicatioMerchanelReq();
		try {
			req.setMch_id("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return object;
	}
	
	/**
	 * 微信修改结算账户
	 * @param cr
	 * @return
	 */
	public JSONObject modifySettlement(WeChatAppBean cr) {
		JSONObject object = new JSONObject();
		UpgradeApplicatioMerchanelReq req = new UpgradeApplicatioMerchanelReq();
		try {
			//req.setMch_id(cr.getOutMerchantCode());
			//主体类型，根据主体类型判断是对公还是对私枚举类型
			req.setOrganization_type("ACCOUNT_TYPE_BUSINESS");
			req.setAccount_bank(cr.getBank());
			req.setBank_address_code(cr.getBankCity());
			req.setBank_name(cr.getInterBankName());
//			req.setBank_branch_id(cr.getBankCode());
			req.setAccount_number(cr.getAccountNo());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return object;
	}
	
	/**
	 * 查询提现状态
	 * @param weChatBean
	 * @return
	 */
	public Map<String, Object> merchantWithdrawalStateQuery(WeChatAppBean weChatBean) {
		Map<String, Object> weChatResult = new HashMap<String, Object>();
		WeiXinWithdrawalStateQueryReq req = new WeiXinWithdrawalStateQueryReq();
		req.setDate(weChatBean.getDate());
		req.setSubMchId(weChatBean.getOutMerchantCode());
		try {
			logger.info("查询微信提现状态：{}", req);
			WeiXinWithdrawalStateQueryResp weiXinWithdrawalStateQueryResp = wxpayAgentMerRegistService.merchantWithdrawalStateQuery(req);
			logger.info("查询微信提现状态：{}", weiXinWithdrawalStateQueryResp);
			if(GetwayStatus.SUCCESS.equals(weiXinWithdrawalStateQueryResp.getCode())){
				if (BusinessStatus.SUCCESS.equals(weiXinWithdrawalStateQueryResp.getSubCode())) {
					weChatResult.put("message",weiXinWithdrawalStateQueryResp);
					weChatResult.put("result", "SUCCESS");
				}else if(BusinessStatus.FAIL.equals(weiXinWithdrawalStateQueryResp.getSubCode())) {
					weChatResult.put("message",weiXinWithdrawalStateQueryResp.getSubMsg());
					weChatResult.put("result", "FAIL");
				}
			}else if(GetwayStatus.FAIL.equals(weiXinWithdrawalStateQueryResp.getCode())){
				weChatResult.put("message",weiXinWithdrawalStateQueryResp.getMsg());
				weChatResult.put("result", "FAIL");
			}
			
		} catch (Exception e) {
			weChatResult.put("message",e);
			weChatResult.put("result", "FAIL");
		}
		return weChatResult;
	}

	/**
	 * 重新发起提现
	 * @param weChatBean
	 * @return
	 */
	public Map<String, Object> reintroduceWithdrawals(WeChatAppBean weChatBean) {
		Map<String, Object> weChatResult = new HashMap<String, Object>();
		WeiXinReintroduceWithdrawalsReq req = new WeiXinReintroduceWithdrawalsReq();
		req.setDate(weChatBean.getDate());
		req.setSubMchId(weChatBean.getOutMerchantCode());
		try {
			logger.info("重新发起提现状态：{}", req);
			WeiXinReintroduceWithdrawalsResp resp = wxpayAgentMerRegistService.reintroduceWithdrawals(req);
			logger.info("重新发起提现状态：{}", resp);
			if(GetwayStatus.SUCCESS.equals(resp.getCode())){
				if (BusinessStatus.SUCCESS.equals(resp.getSubCode())) {
					weChatResult.put("message",resp);
					weChatResult.put("result", "SUCCESS");
				}else if(BusinessStatus.FAIL.equals(resp.getSubCode())) {
					weChatResult.put("message",resp.getSubMsg());
					weChatResult.put("result", "FAIL");
				}
			}else if(GetwayStatus.FAIL.equals(resp.getCode())){
				weChatResult.put("message",resp.getMsg());
				weChatResult.put("result", "FAIL");
			}
		} catch (Exception e) {
			weChatResult.put("message",e);
			weChatResult.put("result", "FAIL");
		}
		return weChatResult;
	}
	
}
