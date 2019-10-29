package com.sevenpay.bms.merchant.reported;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.base.enums.ReStatus;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayRequestInfo;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.service.IMerChantIntoService;
import com.seven.micropay.commons.util.DateUtil;
import com.sevenpay.bms.basemanager.custInfo.bean.TdCustInfo;
import com.sevenpay.bms.basemanager.merchant.service.MerchantService;
import com.sevenpay.bms.basemanager.utils.GenSN;
import com.sevenpay.bms.merchant.reported.bean.Area;
import com.sevenpay.bms.merchant.reported.bean.Bank;
import com.sevenpay.bms.merchant.reported.bean.BankBranch;
import com.sevenpay.bms.merchant.reported.bean.ChannlInfo;
import com.sevenpay.bms.merchant.reported.bean.City;
import com.sevenpay.bms.merchant.reported.bean.CrInComeBean;
import com.sevenpay.bms.merchant.reported.bean.FmWxCategory;
import com.sevenpay.bms.merchant.reported.bean.Industry;
import com.sevenpay.bms.merchant.reported.bean.Province;
import com.sevenpay.bms.merchant.reported.bean.TbFmTradeInfo;
import com.sevenpay.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.sevenpay.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.sevenpay.bms.merchant.reported.mapper.FmIncomeMapper;
import com.sevenpay.bms.merchant.reported.service.CrIncomeService;
import com.sevenpay.bms.merchant.reported.service.FmIncomeService;

@Controller
@RequestMapping(MerchantReportedPath.BASE)
public class MerchantReportsController {

   private Logger logger = LoggerFactory.getLogger(MerchantReportsController.class);
	
   @Autowired
   private FmIncomeService fmIncomeService;
   
   @Autowired
   private CrIncomeService crIncomeService;
   
   @Autowired
   private MerchantService merchantService;
   
   @Autowired
   private FmIncomeMapperDao fmIncomeMapperDao;
   
   @Autowired
   private IMerChantIntoService iMerChantIntoServic;
   
   @Autowired
   private FmIncomeMapper fmIncomeMapper;

   /**
    * 商户报备入口
    */
	@RequestMapping(MerchantReportedPath.BMSMERCHANTREPORT)
	public ModelAndView  viewMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail){
		ModelAndView mv = new ModelAndView();
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询省份信息***/
	/*	List<Province> proviceList = fmIncomeService.getprovinceList();
		*//***查询省份信息***//*
		List<Bank> bankList = fmIncomeService.getBankList();
		*//***查询支付功能Id***//*
		List<TbFmTradeInfo> powerIdList = fmIncomeService.getPowerIdList();
		*//***查询翼支付商户行业信息***//*
		List<Industry> industryList = fmIncomeService.getIndustryList();*/
		
		if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
		}
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		/*if(null!=proviceList && proviceList.size()>0){
			mv.addObject("provinceList", proviceList);
		}
		if(null!=bankList && bankList.size()>0){
			mv.addObject("bankList", bankList);
		}
		if(null!=powerIdList && powerIdList.size()>0){
			mv.addObject("powerIdList", powerIdList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}*/
		
		return mv;
	}	
	
	  
	/**
	 * 查询市
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.BMSSELCITY)
	@ResponseBody
	public String selCity(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String province = request.getParameter("province");
		if(!StringUtils.isBlank(province)){
			List<City> cityList = fmIncomeService.getCityList(province);
			if(null!=cityList &&cityList.size()>0){
				object.put("result", "SUCCESS");
				object.put("cityList", cityList);
			}else{
				object.put("result", "FAIL");
				object.put("message", "省份编号为空");
			}
		}
		return object.toString();
	}
	
	/**
	 * 查询区
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.BMSSELAREA)
	@ResponseBody
	public String selArea(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String city = request.getParameter("city");
		if(!StringUtils.isBlank(city)){
			List<Area> areaList = fmIncomeService.getAreaList(city);
			if(null!=areaList &&areaList.size()>0){
				object.put("result", "SUCCESS");
				object.put("areaList", areaList);
			}else{
				object.put("result", "FAIL");
				object.put("message", "区域编号为空");
			}
		}
		return object.toString();
	}
	
	/**
	 * 查询开户支行号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.BMSSELINTERBANK)
	@ResponseBody
	public String selInterBank(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String interBank = request.getParameter("bank");
		if(!StringUtils.isBlank(interBank)){
			List<BankBranch	> interBankList = fmIncomeService.getInterBankList(interBank);
			if(null!=interBankList &&interBankList.size()>0){
				object.put("result", "SUCCESS");
				object.put("interBankList", interBankList);
			}else{
				object.put("result", "FAIL");
				object.put("message", "银行编号为空");
			}
		}
		return object.toString();
	}
	/**
	 * 查询行目类别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.BMSSELFIRSTTYPE)
	@ResponseBody
	public String selfirstType(HttpServletRequest request,HttpServletResponse response,FmWxCategory category){
		JSONObject object = new JSONObject();
		String type = request.getParameter("type");
		String flag = request.getParameter("flag");
		List<FmWxCategory> categoryList = null;
		if(flag.equals("first")){
			if("alipay".equals(type)){//支付宝
				categoryList = fmIncomeService.getAlipayList();
	  		}else if("wx".equals(type)){//微信
	  			categoryList = fmIncomeService.getWxList();
	  		}else{//京东
	  			categoryList = fmIncomeService.getJdList();
	  		}
		}else if(flag.equals("second")){
			if("wx".equals(type)){//微信
	  			categoryList = fmIncomeService.getWxSecondTypeList(category);
	  		}else{//京东
	  			categoryList = fmIncomeService.getJdThirdTypeList(category);
	  		}
		}else{
			categoryList = fmIncomeService.getWxThirdTypeList(category);
		}
		
		if(null!=categoryList && categoryList.size()>0){
			object.put("result", "SUCCESS");
			object.put("categoryList", categoryList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "行业类目查询失败");
		}
		
		return object.toString();
	}
	
	
	/**
	 * 提交报备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.SUBMITREPORT)
	@ResponseBody
	@Transactional
	public String list(HttpServletRequest request,HttpServletResponse response,CrInComeBean cr){
		JSONObject object = new JSONObject();
		JSONObject bestResult = new JSONObject();
		request.setAttribute("merchantCode", cr.getMerchantCode().trim());
		String result = "";
		if("FM_COMBINEDPAY".equals(cr.getChannelNo().trim())){//富民
			//查询该商户是否已报备
			TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cr);
			if(td!=null){
				if("Y".equals(td.getReportStatus())||"O".equals(td.getReportStatus())){
				object.put("result", "FAILURE");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}else{
					TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
					tdInfo.setMerchantCode(td.getMerchantCode());
					tdInfo.setChannelNo(td.getChannelNo());
					tdInfo.setReportStatus("E");
					fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo,"99");
					
					//调用富民报备接口
					result = fmIncomeService.fmReported(cr);
				}
				
			}else{
				//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
				TdMerchantDetailInfo info = new TdMerchantDetailInfo();
				info.setId(GenSN.getSN());
				info.setPatchNo(GenSN.getSN());
				info.setMerchantCode(cr.getMerchantCode().trim());
				info.setChannelNo(cr.getChannelNo());
				info.setReportStatus("E");
				info.setProvCode(cr.getProvince());
				info.setCityCode(cr.getCity());
				info.setContryCode(cr.getCountry());
				info.setBankCode(cr.getBankCode());
				info.setBranchBankCode(cr.getInterBank());
				info.setBranchBankName(cr.getInterBankName());
				info.setMobileNo(cr.getMobileNo());
				info.setCustomerPhone(cr.getCustomerPhone());
				info.setQq(cr.getQq());
				info.setAppId(cr.getAppId());
				info.setPowerId(cr.getPowerId());
				info.setCategory(cr.getThirdType());
				
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
				//调用富民报备接口
				result = fmIncomeService.fmReported(cr);
				object.put("result", "SUCCESS");
				object.put("message", "报备成功");
			}
			
			if("SUCCESS".equals(result)){
				object.put("result", "SUCCESS");
				object.put("message", "报备成功");
			}else{
				object.put("result", "FAILURE");
				object.put("message", "商户报备失败");
			}
		}else if("BEST_PAY".equals(cr.getChannelNo().trim())){//翼支付
			//查询该商户是否已报备
			TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cr);

			if(td!=null){
				//该商户已报备
				if("Y".equals(td.getReportStatus())||"O".equals(td.getReportStatus())){
				object.put("result", "FAILURE");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}else{//商户未报备
					TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
					tdInfo.setMerchantCode(td.getMerchantCode());
					tdInfo.setChannelNo(td.getChannelNo());
					tdInfo.setReportStatus("E");
					tdInfo.setBestMerchantType("01");
					fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo,"99");
					
					//获取图片路径
					TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());
					
					//获取门头照路径
					String doorImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
					String[] pathDoorImage = null;
					pathDoorImage = doorImagePath.split(";");
					if(pathDoorImage.length >1){
						cr.setStoreSignBoardPic(pathDoorImage[0]);
						cr.setStoreInteriorPic(pathDoorImage[1]);
					}else{
						cr.setStoreSignBoardPic(doorImagePath);
						//内景
						cr.setStoreInteriorPic(doorImagePath);
					}
					
					
					//获取身份证正反面
					String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
					String[] paths = null;
					paths = identityImagePath.split(";");
					//正面
					String identityImagePath0 = paths[0];
					cr.setIdentityCardFrontPic(identityImagePath0);
					//反面
					String identityImagePath1 = paths[1];
					cr.setIdentityCardReversePic(identityImagePath1);
					
					//获取营业执照
					String imagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
					cr.setLicensePic(imagePath);
					
					
					logger.info(imagePath);
					//调用翼支付报备接口
					bestResult = fmIncomeService.bestReported(cr);
					
					if("SUCCESS".equals(bestResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
					}else{
						object.put("result", "FAILURE");
						if(bestResult.get("message") == "" && bestResult.get("message") == null){
							object.put("message", "翼支付进件明确失败");
						}else {
							object.put("message", bestResult.get("message"));
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
				info.setProvCode(cr.getProvince());
				info.setCityCode(cr.getCity());
				info.setContryCode(cr.getCountry());
				info.setBankCode(cr.getBankCode());
				info.setBranchBankCode(cr.getInterBank());
				info.setBranchBankName(cr.getInterBankName());
				info.setMobileNo(cr.getMobileNo());
				info.setBestMerchantType("01");
				//info.setCustomerPhone(cr.getCustomerPhone());
				//info.setQq(cr.getQq());
				//info.setAppId(cr.getAppId());
				//info.setPowerId(cr.getPowerId());
				//info.setCategory(cr.getThirdType());
				
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
				//获取图片路径
				TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());
				String imagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
				logger.info(imagePath);
				//调用翼支付报备接口
				bestResult =fmIncomeService.bestReported(cr);
				
				if("SUCCESS".equals(bestResult.get("result"))){
					object.put("result", "SUCCESS");
					object.put("message", "报备成功");
				}else{
					object.put("result", "FAILURE");
					if(bestResult.get("message") == "" && bestResult.get("message") == null){
						object.put("message", "翼支付进件明确失败");
					}else {
						object.put("message", bestResult.get("message"));
					}
					
				}
			}
			
		
		}
		return object.toString();
	}
	
	
	
	/** 文件上传 */
	@RequestMapping(MerchantReportedPath.FILEUPLOAD)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response,String merchantCode) {
		JSONObject object = new JSONObject();
		try {
			TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			Map<String, String> result = merchantService.compressUpload(request, custInfo.getCustId());
			object.put("result", result.get("result"));
			object.put("message", result.get("message"));
			object.put("custId", custInfo.getCustId());
			request.getSession().setAttribute("custScanMap", result);
		} catch (Exception e) {
			logger.error("文件上传失败",e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	
	
	// 根据商户号查找进件失败的商户信息 
	@RequestMapping(MerchantReportedPath.SELECTMERCHANTREPORTINFO)
	@ResponseBody
	public String findByIdMerchantInfo(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail) {
		logger.info("查找商户信息");
		
		JSONObject jsonObject = new JSONObject();
		
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		TdMerchantDetailInfo merchantDetailInfo = fmIncomeMapperDao.selMerchantDetailInfo(detail);
		/***查询省份信息***/
		List<Province> proviceList = fmIncomeService.getprovinceList();
		/***查询省份信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询支付功能Id***/
		List<TbFmTradeInfo> powerIdList = fmIncomeService.getPowerIdList();
		/***查询翼支付商户行业信息***/
		List<Industry> industryList = fmIncomeService.getIndustryList();
		
		if(null!=merchantDetailInfo ){
			jsonObject.put("merchantDetailInfo", merchantDetailInfo);
		}
		if(null!=reportedList && reportedList.size()>0){
			jsonObject.put("reportedList", reportedList);
		}
		if(null!=channlInfoList && channlInfoList.size()>0){
			jsonObject.put("infoList", channlInfoList);
		}
		if(null!=proviceList && proviceList.size()>0){
			jsonObject.put("provinceList", proviceList);
		}
		if(null!=bankList && bankList.size()>0){
			jsonObject.put("bankList", bankList);
		}
		if(null!=powerIdList && powerIdList.size()>0){
			jsonObject.put("powerIdList", powerIdList);
		}
		if(null!=industryList && industryList.size()>0){
			jsonObject.put("industryList", industryList);
		}
		
		return jsonObject.toJSONString();
	}
	
	
	

	
	// 根据商户号查询商户进件状态
	@RequestMapping(MerchantReportedPath.SELECTMERCHANTREPORTSTATUS)
	@ResponseBody
	public String getMerchantStatus(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail) {
		JSONObject object = new JSONObject();
		Map<String, Object> req = new HashMap<String, Object>();
		
		String merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
		}
		
		if("BEST_PAY".equals(detail.getChannelNo())){
			// 翼支付企业进件查询
			if("02".equals(detail.getBestMerchantType())){
				req.put("traceLogId", "QT"+DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
				req.put("loginNo", detail.getLoginNo());
				req.put("channelType", ChannelMerRegist.BEST_PAY);
				req.put("isCompany", true);
			}else{
				req.put("merchantNo", detail.getMerchantCode());
				req.put("requestSeqId", System.currentTimeMillis() + "BE");
				req.put("channelType", ChannelMerRegist.BEST_PAY);
			}
			
		}else if("SUIXING_PAY".equals(detail.getChannelNo())){
			
			List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
			String taskCode = reportedList.get(0).getRemark();
			SxPayRequestInfo requestInfo = new SxPayRequestInfo();
			requestInfo.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
			requestInfo.setReqData(taskCode);
			requestInfo.setTimestamp(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
			req.put("merList", requestInfo);
			req.put("channelType", ChannelMerRegist.SUIXING_PAY);
			
		}else if("SUM_PAY".equals(detail.getChannelNo())){
			
			if(null != detail.getOutMerchantCode()){
				req.put("mchntId", detail.getOutMerchantCode());
				req.put("channelType", ChannelMerRegist.SUM_PAY);
			}else {
				object.put("result", "FAIL");
				object.put("message", "外部商户号为空");
				return  object.toString();
			}
			
		}else if("YQB".equals(detail.getChannelNo())){
			
			req.put("paltformMerNo", detail.getMerchantCode());
			req.put("channelType", ChannelMerRegist.YQB);
			
		}else if("KFT_PAY".equals(detail.getChannelNo())){
			
			req.put("merchantId",detail.getMerchantCode());
			req.put("merchantProperty",custInfo.getCustType());
			if("1".equals(custInfo.getCustType())){
				req.put("certNo",custInfo.getCertifyNo());
			}else{
				req.put("merchantId",custInfo.getBusinessLicense());
			}
			req.put("channelType", ChannelMerRegist.KFT_PAY);
		}
			
		ChannelResult channelResult = iMerChantIntoServic.merQuery(req);
		
		Map<String, Object> rtnResultMap = channelResult.getData();
		
		if((ReStatus.SUCCESS).equals(channelResult.getStatus())){
			String channelMerNo = "";
			if("BEST_PAY".equals(detail.getChannelNo())){
				
				if("02".equals(detail.getBestMerchantType())){
					//翼支付企业进件成功后仍需签约
					object.put("result", "SUCCESS");
					object.put("message", detail.getBestMerchantType());
				}else{
					channelMerNo =  rtnResultMap.get("bestpayMctNo") == null?"":(String)rtnResultMap.get("bestpayMctNo");
					detail.setBestpayMctNo(channelMerNo);
					object.put("result", "SUCCESS");
					object.put("message", channelResult.getData().get("ReMsg"));
				}
				
			}else if("SUIXING_PAY".equals(detail.getChannelNo())){
				
				channelMerNo = rtnResultMap.get("mno")==null?"":(String)rtnResultMap.get("mno");
				object.put("result", "SUCCESS");
				object.put("message", "商户审核成功");
				
			}else if("SUM_PAY".equals(detail.getChannelNo())){
				
				channelMerNo = detail.getOutMerchantCode();
				object.put("result", "SUCCESS");
				object.put("message", "商户审核成功");
				
			}else if("YQB".equals(detail.getChannelNo())){
				
				channelMerNo =  rtnResultMap.get("merchantId") == null?"":(String)rtnResultMap.get("merchantId");
				object.put("result", "SUCCESS");
				object.put("message", "商户审核成功");
			}
			
			//报备表中状态改变
			detail.setReportStatus("O");
			//报备成功商户报备信息表中状态改变
			detail.setFileStatus("Y");
			detail.setOutMerchantCode(channelMerNo);
			fmIncomeService.UpdateMerReportAndMerDetailInfo(detail, "1");
			
		}else if((ReStatus.FAIL).equals(channelResult.getStatus())){
			//查询报备失败改变表中状态
			//有外部商户号未成功则调更新接口   无外部商户号未成功则重新进件
			String channelMerNo = "";
			if("BEST_PAY".equals(detail.getChannelNo())){
				if("02".equals(detail.getBestMerchantType())){
					//翼支付企业进件成功后仍需签约
				}else{
					channelMerNo =  rtnResultMap == null?"":(String)rtnResultMap.get("bestpayMctNo");
					detail.setBestpayMctNo(channelMerNo);
				}
				
			}else if("SUIXING_PAY".equals(detail.getChannelNo())){
				channelMerNo = rtnResultMap == null?"":(String)rtnResultMap.get("mno");
			}else if("SUM_PAY".equals(detail.getChannelNo())){
				channelMerNo = detail.getOutMerchantCode();
			}else if("YQB".equals(detail.getChannelNo())){
				channelMerNo =  rtnResultMap.get("merchantId") == null?"":(String)rtnResultMap.get("merchantId");
			}
			detail.setReportStatus("E");
			detail.setOutMerchantCode(channelMerNo);
			fmIncomeService.UpdateMerReportAndMerDetailInfo(detail,"2");
			object.put("result", "FAIL");
			object.put("message", StringUtils.isBlank(channelResult.getReMsg())?"审核失败":channelResult.getReMsg());
		}else{
			object.put("result", "SUCCESS");
			//通讯失败或者系统异常
			if("04".equals(channelResult.getReCode())){
				object.put("message", channelResult.getReMsg());
			}else {
				object.put("message", "商户审核中");
			}
		}
		
		return  object.toString();
		
	}
}
