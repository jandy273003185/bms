package com.sevenpay.bms.merchant.merchantReported.dao;


import java.util.List;

import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sevenpay.bms.basemanager.custInfo.bean.TdCustInfo;
import com.sevenpay.bms.merchant.merchantReported.bean.KFTArea;
import com.sevenpay.bms.merchant.merchantReported.bean.KFTMccBean;
import com.sevenpay.bms.merchant.merchantReported.mapper.KftIncomeMapper;
import com.sevenpay.bms.merchant.reported.bean.Area;
import com.sevenpay.bms.merchant.reported.bean.Bank;
import com.sevenpay.bms.merchant.reported.bean.BankBranch;
import com.sevenpay.bms.merchant.reported.bean.City;
import com.sevenpay.bms.merchant.reported.bean.CrInComeBean;
import com.sevenpay.bms.merchant.reported.bean.FmWxCategory;
import com.sevenpay.bms.merchant.reported.bean.Industry;
import com.sevenpay.bms.merchant.reported.bean.MerchantCity;
import com.sevenpay.bms.merchant.reported.bean.Province;
import com.sevenpay.bms.merchant.reported.bean.SumPayArea;
import com.sevenpay.bms.merchant.reported.bean.SumpayMcc;
import com.sevenpay.bms.merchant.reported.bean.TbFmTradeInfo;
import com.sevenpay.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.sevenpay.bms.merchant.reported.bean.YQBArea;
import com.sevenpay.bms.merchant.reported.bean.YQBIndustry;
import com.sevenpay.bms.merchant.reported.mapper.FmIncomeMapper;


@Repository
public class KftIncomeMapperDao {
	
  @Autowired
  private KftIncomeMapper kftIncomeMapper;

public List<KFTArea> selKftProvinceList() {
	// TODO Auto-generated method stub
	return kftIncomeMapper.selKftProvinceList();
}

public List<KFTMccBean> selKftIndustryList() {
	// TODO Auto-generated method stub
	return kftIncomeMapper.selKftIndustryList();
}

public List<KFTMccBean> selKftIndustryTwoList(KFTMccBean kFTMccBean) {
	// TODO Auto-generated method stub
	return kftIncomeMapper.selKftIndustryTwoList(kFTMccBean);
}

public List<KFTMccBean> selKftIndustryThreeList(KFTMccBean kFTMccBean) {
	// TODO Auto-generated method stub
	return kftIncomeMapper.selKftIndustryThreeList(kFTMccBean);
}

public List<KFTArea> selKftCityList(KFTArea kftArea) {
	// TODO Auto-generated method stub
	return kftIncomeMapper.selKftCityList(kftArea);
}

public List<KFTArea> selKftAreaList(KFTArea kftArea) {
	// TODO Auto-generated method stub
	return kftIncomeMapper.selKftAreaList(kftArea);
}

  

}
