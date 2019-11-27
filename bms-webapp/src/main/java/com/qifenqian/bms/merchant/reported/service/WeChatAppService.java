package com.qifenqian.bms.merchant.reported.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppAreaInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppBean;
import com.qifenqian.bms.merchant.reported.dao.WeChatAppMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.WeChatAppMapper;
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
	private IMerChantIntoService iMerChantIntoService;
	
	@Autowired
	private WeChatAppMapper weChatAppMapper;
	
	
	/**
	 * 微信报备
	 * @param cr
	 * @return
	 */
	public JSONObject weChatAppReported(WeChatAppBean cr) {
		JSONObject object = new JSONObject();
		WeChatApplicationReq req = new WeChatApplicationReq();
		try {
			String idCardValidTime = "[" +cr.getIdentityEffDate()+ "," + cr.getIdentityValDate()+"]";
			
			req.setBusiness_code(cr.getMerchantCode());              //业务申请编号
			req.setId_card_name(cr.getRepresentativeName());         //身份证名字
			req.setId_card_number(cr.getRepresentativeCertNo());     //身份证号
			req.setId_card_valid_time(idCardValidTime);              //身份证有效期
			req.setAccount_name(cr.getRepresentativeName());         //开户名称 
			req.setAccount_bank(cr.getBankName());                   //开户银行
			req.setBank_address_code(cr.getBankCity());              //开户银行省市编码
			req.setBank_name(cr.getInterBankName());                 //开户银行全称（含支行）
			req.setAccount_number(cr.getAccountNo());                //结算账号
			req.setStore_name(cr.getCustName());					 //门店名称
			req.setStore_address_code(cr.getArea());				 //门店省市编码
			req.setStore_street(cr.getCprRegAddr());                 //门店街道名称
			req.setMerchant_shortname(cr.getShortName());			 //商户简称 
			req.setService_phone(cr.getCustomerPhone());             //客服电话
			req.setProduct_desc(cr.getIndustryCode());               //售卖商品/提供服务描述
			req.setRate(cr.getRate());                               //费率
			req.setContact(cr.getRepresentativeName());				 //超级管理员姓名
			req.setContact_phone(cr.getPhone());					 //手机号
			req.setContact_email(cr.getEmail());					 //联系邮箱
			req.setIdCard(cr.getCertAttribute1Path());               //身份证正面照片
			req.setNational(cr.getCertAttribute2Path());             //身份证反面照片
			req.setEntrance(cr.getDoorPhotoPath());                  //门店门口照片
			req.setIndoor(cr.getShopInteriorPath());                 //店内环境照片
			
			
			/*
			 * //图片路径 PicPath picPath = new PicPath();
			 * req.setIdCard(picPath.getIdentityImagePath0()); //身份证正面照片
			 * req.setNational(picPath.getIdentityImagePath1()); //身份证反面照片
			 * req.setEntrance(picPath.getDoorPath()); //门店门口照片
			 * req.setIndoor(picPath.getShopInteriorPath()); //店内环境照片
			 */
			Map<String, Object> reqs = new HashMap<>();
			ChannelResult channelResult = new ChannelResult();
			logger.info("-----------------微信进件请求报文：" + JSONObject.toJSONString(req));
			reqs.put("req", req);
			reqs.put("channelType", ChannelMerRegist.WX);
			channelResult= iMerChantIntoService.merchatAdd(reqs);
			logger.info("-----------------微信进件响应报文：" + JSONObject.toJSONString(channelResult));
		    
		    if(channelResult != null && "SUCCESS".equals(channelResult.getStatus().toString())) {
		    	if(ReStatus.SUCCESS.equals(channelResult.getStatus()) 
		    		  && "00".equals(channelResult.getChannelCode())) {
		    		
		    		TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
		    		tdInfo.setMerchantCode(cr.getMerchantCode().trim());
		    		tdInfo.setChannelNo(cr.getChannelNo());
		    		tdInfo.setReportStatus("O");
		    		// 查询商户报备信息
		            TdMerchantDetailInfo tdInfo_ = weChatAppMapper.selTdMerchantDetailInfo(tdInfo);
		            tdInfo.setPatchNo(tdInfo_.getPatchNo());
		            String mchntStatus = "0";
		            tdInfo.setFileStatus("Y");
		            UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
		        } else {
		           logger.error("微信进件明确失败:" + channelResult.getData().get("errorMsg"));
		           object.put("message", channelResult.getData().get("errorMsg"));
		           object.put("result", "FAILURE");
		           return object;
		        }

	        }else{
	         logger.error("微信进件明确失败:" + channelResult.getReMsg());
	         object.put("result", "FAILURE");
	         object.put("message", channelResult.getReMsg());
	         return object;
	        }


	     } catch (Exception e) {
	       logger.error("FmIncomingServlet execute exception", e);
	       object.put("result", "FAILURE");
	       object.put("message", e);
	       return object;
	     }
	     object.put("result", "SUCCESS");
		return object; 
		
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
			req.setSub_mch_id(cr.getOutMerchantCode());       //小微商户的商户号
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
			req.setMch_id(cr.getOutMerchantCode());
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
			req.setMch_id(cr.getOutMerchantCode());
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
