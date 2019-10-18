package com.sevenpay.bms.merchant.reported;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayBankInfo;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayRequestInfo;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayUploadFileInfo;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.enums.suixinpay.SuixinBankType;
import com.seven.micropay.channel.service.IMerChantIntoService;
import com.seven.micropay.commons.util.DateUtil;
import com.sevenpay.bms.basemanager.custInfo.bean.TdCustInfo;
import com.sevenpay.bms.basemanager.merchant.service.MerchantService;
import com.sevenpay.bms.basemanager.utils.GenSN;
import com.sevenpay.bms.common.util.PropertiesUtil;
import com.sevenpay.bms.merchant.reported.bean.Bank;
import com.sevenpay.bms.merchant.reported.bean.ChannlInfo;
import com.sevenpay.bms.merchant.reported.bean.City;
import com.sevenpay.bms.merchant.reported.bean.CrInComeBean;
import com.sevenpay.bms.merchant.reported.bean.Industry;
import com.sevenpay.bms.merchant.reported.bean.MerchantCity;
import com.sevenpay.bms.merchant.reported.bean.Province;
import com.sevenpay.bms.merchant.reported.bean.SuiXingBean;
import com.sevenpay.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.sevenpay.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.sevenpay.bms.merchant.reported.mapper.FmIncomeMapper;
import com.sevenpay.bms.merchant.reported.service.CrIncomeService;
import com.sevenpay.bms.merchant.reported.service.FmIncomeService;

@Controller
@RequestMapping(MerchantReportedPath.BASE)
public class SuiXingPayMerchantReportsController {

   private Logger logger = LoggerFactory.getLogger(SuiXingPayMerchantReportsController.class);
	
   @Autowired
   private FmIncomeService fmIncomeService;
   
   @Autowired
   private CrIncomeService crIncomeService;
   
   @Autowired
   private MerchantService merchantService;
   
   @Autowired
   private FmIncomeMapperDao fmIncomeMapperDao;
   
   @Autowired
   private IMerChantIntoService iMerChantIntoService;

   @Autowired
   private FmIncomeMapper fmIncomeMapper;
	
	
   /**
    * 随行付商户报备入口
    */
	@RequestMapping(MerchantReportedPath.SUIXINGPAYMERCHANTREPORTS)
	public ModelAndView  viewMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String merchantCode,String status){
		ModelAndView mv = new ModelAndView();
		String channlCode = "SUIXING_PAY";
		if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询随行付银行地区信息***/
		List<Province> proviceList = fmIncomeService.getSuiXingProvinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询随行付商户行业信息***/
		List<Industry> industryList = fmIncomeService.getSuiXingIndustryList();
		/***查询随行付商户注册地区信息***/
		String areaType ="2";
		List<MerchantCity> merchantProvinceList = fmIncomeService.getSuiXingMerchantCityList(areaType);
		merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
		}
		if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
			String remark =  reportedList.get(0).getRemark();
			mv.addObject("remark", remark);
		}
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		if(null!=proviceList && proviceList.size()>0){
			mv.addObject("provinceList", proviceList);
		}
		if(null!=bankList && bankList.size()>0){
			mv.addObject("bankList", bankList);
		}
		if(null!=merchantProvinceList && merchantProvinceList.size()>0){
			mv.addObject("merchantProvinceList", merchantProvinceList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		if(null!=custInfo){
			mv.addObject("custInfo", custInfo);
		}
		mv.addObject("status",status);
		return mv;
	}	
	
	/**
	 * 查询银行所在地区
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.SELSUIXINGCITY)
	@ResponseBody
	public String selCity(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String province = request.getParameter("province");
		if(!StringUtils.isBlank(province)){
			List<City> cityList = fmIncomeService.getSuiXingCityList(province);
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
	 * 查询商户所在地区
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.SELSUIXINGAREA)
	@ResponseBody
	public String selArea(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String superiorAreaCode = request.getParameter("superiorAreaCode");
		if(!StringUtils.isBlank(superiorAreaCode)){
			List<MerchantCity> areaList = fmIncomeService.getSuiXingAreaList(superiorAreaCode);
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
	 * 随行付上传文件
	 */
	@RequestMapping(MerchantReportedPath.SELSUIXINGFILEUPLOAD)
	@ResponseBody
	public String  fileUpload(HttpServletRequest request,HttpServletResponse response,String merchantCode,String status) {
		JSONObject object = new JSONObject();
		SuiXingBean cr = new SuiXingBean();
		cr.setMerchantCode(merchantCode);
		/*TdCustInfo custInfo = new TdCustInfo();
		
		if(null != cr.getMerchantCode()){
			custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());
		}*/
		try {
			//文件更名压缩并上传服务器
			logger.info("文件更名压缩并上传服务器" +  "------------------------------");
			Map<String, String> fileResult = fmIncomeService.download(request, response, cr);
			if("SUCCESS".equals(fileResult.get("result"))){
				
				//文件上传至随行付
				Properties p = PropertiesUtil.getProperties();
//				File zipFile = new File(cr.getMerchantCode() +".zip") ;	// 定义压缩文件名称
				
//				String path =p.getProperty("SX_FILE_SAVE_PATH") + File.separator  + File.separator  +zipFile;
//				String filePath = cr.getMerchantCode() + DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS);
				//本地path
//				String path = "D:"+  File.separator +  p.getProperty("SX_FILE_SAVE_PATH") + File.separator + fileResult.get("filePath") + File.separator + cr.getMerchantCode() +".zip";
				//服务器地址
				String path = p.getProperty("SX_FILE_SAVE_PATH") + File.separator + cr.getMerchantCode() +".zip";
				logger.info("+++++++++++" + path + "------------------------------");
				
				SxPayUploadFileInfo uploadFileInfo = new SxPayUploadFileInfo();
				if("" != cr.getTaskCode() && null != cr.getTaskCode()){
					uploadFileInfo.setTaskCode(cr.getTaskCode());
				}
				uploadFileInfo.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
				uploadFileInfo.setFilePath(path);
				Map<String, Object> req = new HashMap<>();
				req.put("merList", uploadFileInfo);
				req.put("channelType", ChannelMerRegist.SUIXING_PAY);
				
				logger.info("文件上传至随行付" + "------------------------------");
				ChannelResult result = iMerChantIntoService.fileUpload(req);
				if("00".equals(result.getChannelCode())){
					String taskCode = (String) result.getData().get("taskCode");
					if("reported".equals(status)){
						CrInComeBean cc =new CrInComeBean();
						cc.setMerchantCode(cr.getMerchantCode());
						cc.setChannelNo("SUIXING_PAY");
						TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cc);
						TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
						tdInfo.setRemark(taskCode);
						tdInfo.setMerchantCode(merchantCode);
						tdInfo.setPatchNo(td.getPatchNo());
						fmIncomeMapper.updateTdMerchantDetailInfo(tdInfo);	
					}
					object.put("result", "SUCCESS");
					object.put("message", taskCode);
				}else {
					object.put("result", "FAIL");
					object.put("message", result.getReMsg());
					return object.toJSONString();
				}
			}else{
				object.put("result", "FAIL");
				object.put("message", fileResult.get("message"));
				return object.toJSONString();
			}
		} catch (Exception e) {
			logger.error("随行付上传文件失败",e);
			object.put("result", "FAILURE");
			object.put("message", e);
			return object.toJSONString();
		}
		return object.toJSONString();
	}
	
	
	/**
	 * 随行付提交报备
	 */
	
	@RequestMapping(MerchantReportedPath.SUXINGPAYSUBMITREPORT)
	@ResponseBody
	@Transactional
	public String list(HttpServletRequest request,HttpServletResponse response,SuiXingBean cr){
		
		JSONObject object = new JSONObject();
		JSONObject bestResult = new JSONObject();
		request.setAttribute("merchantCode", cr.getMerchantCode().trim());
		
		try {
			//查询商户报备表
			CrInComeBean cc =new CrInComeBean();
			cc.setMerchantCode(cr.getMerchantCode());
			cc.setChannelNo(cr.getChannelNo());
			TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cc);
			//该商户已报备
			if(td!=null){
				//该商户已报备成功
				if("Y".equals(td.getReportStatus())||"O".equals(td.getReportStatus())){
				object.put("result", "FAILURE");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}else{//商户报备失败
					TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
					tdInfo.setMerchantCode(td.getMerchantCode());
					tdInfo.setChannelNo(td.getChannelNo());
					tdInfo.setReportStatus("E");
					tdInfo.setRemark(cr.getTaskCode());
					fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo,"99");
				}
			}else{
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
				info.setBankCode(SuixinBankType.valueOf(cr.getSuiXinBank()).getCode());
				info.setBranchBankName(cr.getInterBankName());
				info.setMobileNo(cr.getMobileNo());
				info.setRemark(cr.getTaskCode());
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
			}
			
			//随行付查询银行信息
			SxPayBankInfo bankInfo = new SxPayBankInfo();
			bankInfo.setBnkCd(SuixinBankType.valueOf(cr.getSuiXinBank()).getCode());
			bankInfo.setLbnkProv(cr.getBankProvince());
			bankInfo.setLbnkCity(cr.getBankCity());
			bankInfo.setLbnkNm(cr.getInterBankName());
			
			SxPayRequestInfo requestInfo = new SxPayRequestInfo();
			requestInfo.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
			requestInfo.setReqData(bankInfo);
			requestInfo.setTimestamp(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
			
			Map<String, Object> req = new HashMap<>();
			req.put("merList", requestInfo);
			req.put("channelType", ChannelMerRegist.SUIXING_PAY);
			// 获取联行号
			ChannelResult result = iMerChantIntoService.queryBankInfo(req);
			
			if("00".equals(result.getChannelCode())){
				List list = (List) result.getData().get("bankList");
				if(list.size() != 0){
					JSONObject a = (JSONObject) list.get(0);
					String lbankNo = (String) a.get("lbnkNo");
					cr.setLbnkNo(lbankNo);
					//商户随行付进件
					logger.info("商户随行付开始进件："+ "--------------------");
					bestResult = fmIncomeService.suiXingReported(cr);
					if("SUCCESS".equals(bestResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
					}else{
						object.put("result", "FAILURE");
						if(bestResult.get("message") == "" && bestResult.get("message") == null){
							object.put("message", "随行付进件明确失败");
							return object.toString();
						}else {
							object.put("message", bestResult.get("message"));
							return object.toString();
						}
					}
				}else{
					object.put("result", "FAILURE");
					object.put("message","支行信息错误");
					return object.toString();
				}
			}else{
				object.put("result", "FAILURE");
				if("" == result.getReMsg() && null == result.getReMsg()){
					object.put("message", "查询银行信息失败");
				}else{
					object.put("message", result.getReMsg());
				}
				return object.toString();
			}
			
		} catch (Exception e) {
			logger.error("随行付进件失败",e);
			object.put("result", "FAILURE");
			object.put("message", e);
			return object.toString();
		}
		return object.toString();
	}
}
