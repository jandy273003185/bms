package com.qifenqian.bms.merchant.reported;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.common.controller.CommonInfoService;
import com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath;
import com.qifenqian.bms.merchant.reported.bean.AliPayCoBean;
import com.qifenqian.bms.merchant.reported.bean.CommonIndustry;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.AliPayIncomeService;

@Controller
@RequestMapping(MerchantEnterReportedPath.BASE)
public class AliPayMerchantReportsController {
	private static final Logger logger = LoggerFactory.getLogger(AliPayMerchantReportsController.class);
	
	@Autowired
	private AliPayIncomeService aliPayIncomeService;
	
	@Autowired
	private FmIncomeMapperDao fmIncomeMapperDao;
	
	@Autowired
	private CommonInfoService commonInfoService;
	
	@RequestMapping(MerchantEnterReportedPath.ALIPAY_MERCHANT_REPORTS)
	public ModelAndView  viewAliPayMerchantReported(String channlCode,String merchantCode,String status) {
		ModelAndView mv = new ModelAndView();
		
		/***查询行业信息***/
		List<CommonIndustry> industryList = commonInfoService.selectCommonIndustrys(channlCode, null, null);
		
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		
		mv.addObject("status",status);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/aliPayReports")
	public Map<String, Object> aliPayReports(AliPayCoBean aliPayCoBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Object object = aliPayIncomeService.aliPayReported(aliPayCoBean);
			result.put("data", object);
			result.put("message", "报备成功");
			result.put("code", "SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("支付宝报备失败：{}", e.getMessage());
			result.put("message", "报备失败");
			result.put("code", "FAIL");
		}
		return result;
	}
}
