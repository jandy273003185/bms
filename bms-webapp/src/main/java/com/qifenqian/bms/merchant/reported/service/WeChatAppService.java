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
import com.qifenqian.jellyfish.bean.agentMerSign.weixin.WeiXinAgentMerRegistReq;
import com.qifenqian.jellyfish.bean.agentMerSign.weixin.WeiXinAgentMerRegistResp;
import com.qifenqian.jellyfish.bean.enums.BusinessStatus;
import com.qifenqian.jellyfish.merRegistApi.WxpayAgentMerRegistService;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.base.enums.ReStatus;
import com.seven.micropay.channel.domain.UpgradeApplicatioMerchanelReq;
import com.seven.micropay.channel.domain.WeChatApplicationReq;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.service.IMerChantIntoService;

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
		//info.setBankCode(cr.getBankCode());
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
	public JSONObject weChatAppUpgradeReported(WeChatAppBean cr) {
		JSONObject object = new JSONObject();
		UpgradeApplicatioMerchanelReq req = new UpgradeApplicatioMerchanelReq();
		try {
			//req.setSub_mch_id(cr.getOutMerchantCode());       //小微商户的商户号
			req.setOrganization_type("");                     //主体类型
			req.setBusiness_license_copy("");				  //营业执照扫描件
			req.setBusiness_license_number("");               //营业执照注册号
			req.setMerchant_name("");                         //商户名称(营业执照上的商户名称)
			req.setCompany_address("");                       //注册地址
			req.setLegal_person("");                          //经营者姓名/法定代表人(营业执照上的经营者/法人姓名)
			req.setBusiness_time("");                         //营业期限
			req.setBusiness_licence_type("");                 //营业执照类型
			req.setAccount_name("");                          //开户名称
			req.setAccount_bank("");                          //开户银行
			req.setBank_address_code("");                     //开户银行省市编码
			req.setBank_name("");                             //开户银行全称（含支行）
			req.setAccount_number("");                        //银行卡号
			req.setMerchant_shortname("");                    //商户简称
			req.setBusiness("");                              //费率结算规则ID
			req.setBusiness_scene("");                        //经营场景
			req.setContact_email("");                         //联系邮箱
			req.setMp_appid("");                              //公众号APPID
			req.setMp_app_screen_shots("");                   //公众号页面截图
			req.setMiniprogram_appid("");                     //小程序APPID
			req.setMiniprogram_screen_shots("");              //小程序页面截图
			req.setApp_appid("");                             //应用APPID
			req.setApp_screen_shots("");                      //APP截图
			req.setApp_download_url("");                      //APP下载衔接
			req.setWeb_url("");                               //PC网站域名
			req.setWeb_authoriation_letter("");               //网站授权函
			req.setWeb_appid("");                             //PC网站对应的公众号APPID
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return object;
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
	
}
