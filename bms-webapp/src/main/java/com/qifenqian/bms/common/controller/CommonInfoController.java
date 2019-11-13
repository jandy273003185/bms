package com.qifenqian.bms.common.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.service.BankService;
import com.qifenqian.bms.basemanager.branchbank.bean.BranchBank;
import com.qifenqian.bms.basemanager.branchbank.service.BranchBankService;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.bean.ProvinceBean;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.common.bean.BranchBankInfo;
import com.qifenqian.bms.merchant.reported.bean.Province;


@Controller
@RequestMapping("/common/info")
public class CommonInfoController {

	@Autowired
	CityService cityService;
	
	@Autowired
	BankService bankService;
	
	@Autowired
	BranchBankService branchBankService;
	
	@Autowired
	CommonInfoService commonInfoService;
	
	/**
	 * 根据渠道查询所有银行信息
	 * @param bank
	 * @return
	 */
	@RequestMapping(value = "/getBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<Bank> bankInfo(Bank bank,String channelCode) {
		
		List<Bank>  bankList = commonInfoService.selectBanks(bank,channelCode);
		
		return bankList;
		
	}
	
	/**
	 * 根据渠道查询所有省份
	 * @return
	 */
	@RequestMapping(value = "/getProvinceInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<Province> selectAllProvince(String channelCode) {
		
		List<Province> provinceList = commonInfoService.selectAllProvince(channelCode);
		
		return provinceList;
		
	}
	
	/**
	 * 根据渠道和省份查询城市
	 * @param provinceBean
	 * @return
	 */
	@RequestMapping(value = "/getCityInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<City> getCityInfo(ProvinceBean provinceBean,String channelCode) {
		
		List<City> cityList = commonInfoService.getCityByProvinceId(provinceBean.getProvinceId(),channelCode);
		
		return cityList;
		
	}
	
	/**
	 * 根据渠道和城市查询区县
	 * @param cityBean
	 * @return
	 */
	@RequestMapping(value = "/getAreaInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<City> getAreaInfo(City cityBean,String channelCode) {
		
		String cityId = Integer.toString(cityBean.getCityId());
		List<City> areaList = commonInfoService.getAreaByCityId(cityId,channelCode);
		
		return areaList;
		
	}
	
	/**
	 * 根据银行和城市查询渠道的分行
	 * @param bank
	 * @return
	 */
	@RequestMapping(value = "/bankCnapsInfo", method = RequestMethod.POST)
	@ResponseBody
	public String bankCnapsInfo(BranchBankInfo bank,String channelCode) {
		JSONObject object = new JSONObject();
		List<BranchBankInfo>  branchBankList = commonInfoService.branchBankList(bank,channelCode);
		
		if(null!=branchBankList &&branchBankList.size()>0){
			object.put("result", "SUCCESS");
			object.put("branchBankList", branchBankList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "银行编号为空");
		}
		return object.toString();
		
	}
	
}
