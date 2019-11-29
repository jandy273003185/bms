package com.qifenqian.bms.merchant.reported;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppAreaInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppBean;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.qifenqian.bms.merchant.reported.service.WeChatAppService;

@Controller
@RequestMapping("/merchant")
public class WeChatAppReportsController {
	
	 private Logger logger = LoggerFactory.getLogger(WeChatAppReportsController.class);
	 
	 @Autowired
	 private FmIncomeService fmIncomeService;
   
	 @Autowired
	 private CrIncomeService crIncomeService;
	 
	 @Autowired
	 private FmIncomeMapperDao fmIncomeMapperDao;
	 
	 @Autowired
	 private WeChatAppService weChatAppService;
	 
	 
	/**
	 * 微信商户报备入口
	*/
	@RequestMapping("/merchantReported/weChatAppMerchantReport")
	public ModelAndView  viewMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String merchantCode,String status){
		
		ModelAndView mv = new ModelAndView();
		TdCustInfo custInfo = new TdCustInfo();
		String channlCode = "WX";
		
		if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}
		/***查询客户信息***/
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
		}
		/***查询省份***/
		List<WeChatAppAreaInfo> weChatAppAreaInfoList = weChatAppService.getProvinceName();
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		
		mv.addObject("custInfo",custInfo);
		mv.addObject("weChatAppAreaInfoList",weChatAppAreaInfoList);
		mv.addObject("channlInfoList",channlInfoList);
		mv.addObject("reportedList",reportedList);
		return mv;
	}
	
	
	/**
	 * 微信商户报备提交
	*/
	@RequestMapping("/reported/weChatAppMerchantReportSubmit")
	@ResponseBody
	public Map<String, Object> reportSubmit(WeChatAppBean cr){
		Map<String, Object> result = new HashMap<String, Object>();
		//判断银行账号是否在微信支持范围内
		List<String> accountList6 = Arrays.asList("623501","621468","620522","625191","622384","623078",
				"622150","622151","622181","622188","955100","621095","620062","621285","621798","621799",
				"621797","622199","621096","621622","623219","621674","623218","621599","623698","623699",
				"620529","622180","622182","622187","622189","621582","623676","623677","622812","622810",
				"623686","621098","622811","628310","625919","625368","625367","518905","622835","625603",
				"940034","625605","518905");
		//List<String> accountList8 = Arrays.asList("62215049","62215050","62215051","62218849","62218850","62218851");
		String accountNoPrefix6 =cr.getAccountNo().substring(0, 6);
		//String accountNoPrefix8 =cr.getAccountNo().substring(0, 8);
		if (accountList6.contains(accountNoPrefix6)) {
			result.put("result", "FAIL");
			result.put("message", "微信小微商户开户目前不支持此前缀的银行账号");
			return result;
		}
		try {
			//查询该商户是否已报备
			CrInComeBean comeBean = new CrInComeBean();
			comeBean.setMerchantCode(cr.getMerchantCode());
			comeBean.setChannelNo(cr.getChannelNo());
			comeBean.setReportStatus("00");
			TdMerchantDetailInfo merchantDetailInfo = fmIncomeMapperDao.selTdMerchantReport(comeBean);
			
			if(merchantDetailInfo != null){
				//该商户已报备
				if("Y".equals(merchantDetailInfo.getReportStatus()) || "O".equals(merchantDetailInfo.getReportStatus())){
					result.put("result", "FAIL");
					result.put("message", "商户已经报备，请勿重新提交");
					return result;
				}
			}
			//调用微信报备接口
			result = weChatAppService.weChatAppReported(cr);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("微信报备失败：{}", e.getMessage());
			result.put("result", "FAIL");
			result.put("message", "微信报备失败");
		}
		return result;
	}
	
	
	/**
	 * 微信商户升级报备入口
	*/
	@RequestMapping("/merchantReported/weChatAppUpgradeMerchantReport")
	public ModelAndView  viewUpgradeMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String merchantCode,String status){
		
		ModelAndView mv = new ModelAndView();
		TdCustInfo custInfo = new TdCustInfo();
		String channlCode = "WX";
		
		if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}
		/***查询客户信息***/
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
		}
		/***查询省份***/
		List<WeChatAppAreaInfo> weChatAppAreaInfoList = weChatAppService.getProvinceName();
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		
		mv.addObject("custInfo",custInfo);
		mv.addObject("weChatAppAreaInfoList",weChatAppAreaInfoList);
		mv.addObject("channlInfoList",channlInfoList);
		mv.addObject("reportedList",reportedList);
		return mv;
	}
	
	/**
	 * 微信商户升级报备提交
	*/
	@RequestMapping("/reported/weChatAppUpgradeMerchantReportSubmit")
	@ResponseBody
	public String reportUpgradeSubmit(HttpServletRequest request,HttpServletResponse response,WeChatAppBean cr){
		JSONObject object = new JSONObject();
		JSONObject weChatResult = new JSONObject();
		if("WX".equals(cr.getChannelNo().trim())){
			
			//查询该商户是否已报备
			CrInComeBean comeBean = new CrInComeBean();
			comeBean.setMerchantCode(cr.getMerchantCode());
			comeBean.setChannelNo(cr.getChannelNo());
			TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(comeBean);
			
			if(td!=null){
				//该商户已报备
				if("Y".equals(td.getReportStatus())||"O".equals(td.getReportStatus())){
				object.put("result", "FAILURE");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}else{//商户报备
					TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
					tdInfo.setMerchantCode(td.getMerchantCode());
					tdInfo.setChannelNo(td.getChannelNo());
					tdInfo.setReportStatus("E");
					fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo,"99");
					Bank bank = new Bank();
					bank.setBankId(cr.getBank());
					List<Bank> bankIdList = fmIncomeService.getYQBBankList(bank);
					if(bankIdList.size() != 0){
						//cr.setBankName(bankIdList.get(0).getBankName());
					}
					//调用微信报备接口
					logger.info("-----------------调用微信报备接口开始");
					weChatResult = weChatAppService.weChatAppUpgradeReported(cr);
					logger.info("-----------------调用微信报备接口开始"+weChatResult.get("result") +  "----------------------");
					if("SUCCESS".equals(weChatResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
					}else{
						object.put("result", "FAILURE");
						if(weChatResult.get("message") == "" && weChatResult.get("message") == null){
							object.put("message", "微信进件明确失败");
						}else {
							object.put("message", weChatResult.get("message"));
						}
					}
				}
			}else{//查询为空，商户未报备
				//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
				TdMerchantDetailInfo info = new TdMerchantDetailInfo();
				info.setId(GenSN.getSN());
				info.setPatchNo(GenSN.getSN());
				info.setMerchantCode(cr.getMerchantCode().trim());
				info.setChannelNo(cr.getChannelNo());
				info.setReportStatus("E");
				info.setProvCode(cr.getMerchantProvince());
				info.setCityCode(cr.getMerchantCity());
				info.setContryCode(cr.getMerchantArea());
				//info.setBankCode(cr.getBankCode());
				info.setBranchBankName(cr.getInterBankName());
				//info.setMobileNo(cr.getMobile());
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
				
				//调用微信报备接口
				logger.info("-----------------调用微信报备接口开始");
				weChatResult = weChatAppService.weChatAppUpgradeReported(cr);
				logger.info("-----------------调用微信报备接口返回" +weChatResult.get("result") +  "----------------------");
				
				if("SUCCESS".equals(weChatResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
				}else{
					object.put("result", "FAILURE");
					if(weChatResult.get("message") == "" && weChatResult.get("message") == null){
						object.put("message", "微信进件明确失败");
					}else {
						object.put("message", weChatResult.get("message"));
					}
				}
			}
		}
		return object.toString();
	}
	
	
	/**
	 * 微信商户查询结算账户
	*/
	@RequestMapping("/merchantReported/weChatAppQuerySettlement")
	public String  querySettlement(HttpServletRequest request,HttpServletResponse response,WeChatAppBean weChatBean){
		
		JSONObject object = new JSONObject();
		JSONObject weChatResult = new JSONObject();
		//调用微信报备查询结算账户
		logger.info("-----------------调用微信报备接口开始");
		weChatResult = weChatAppService.querySettlement(weChatBean);
		logger.info("-----------------调用微信报备接口返回" +weChatResult.get("result") +  "----------------------");
		
		
		
		return object.toString();
	}
	
	/**
	 * 微信商户修改结算账户
	*/
	@RequestMapping("/merchantReported/weChatAppModifySettlement")
	public String  modifySettlement(HttpServletRequest request,HttpServletResponse response,WeChatAppBean weChatBean){
		
		JSONObject object = new JSONObject();
		JSONObject weChatResult = new JSONObject();
		
		TdMerchantDetailInfo info = new TdMerchantDetailInfo();
		info.setMerchantCode(weChatBean.getMerchantCode().trim());
		info.setChannelNo(weChatBean.getChannelNo());
		info.setReportStatus("E");
		info.setProvCode(weChatBean.getMerchantProvince());
		info.setCityCode(weChatBean.getMerchantCity());
		info.setContryCode(weChatBean.getMerchantArea());
		//info.setBankCode(weChatBean.getBankCode());
		info.setBranchBankName(weChatBean.getInterBankName());
		//info.setMobileNo(weChatBean.getMobile());
		info.setReportStatus("99");
		fmIncomeService.UpdateMerReportAndMerDetailInfo(info,"99");
		
		//调用微信报备查询结算账户
		logger.info("-----------------调用微信报备接口开始");
		weChatResult = weChatAppService.modifySettlement(weChatBean);
		logger.info("-----------------调用微信报备接口返回" +weChatResult.get("result") +  "----------------------");
		
		
		
		return object.toString();
	}
}
