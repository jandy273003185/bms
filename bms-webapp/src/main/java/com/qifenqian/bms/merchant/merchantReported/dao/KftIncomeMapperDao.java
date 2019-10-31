package com.qifenqian.bms.merchant.merchantReported.dao;


import java.util.List;

import com.qifenqian.bms.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.merchantReported.bean.KFTArea;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTMccBean;
import com.qifenqian.bms.merchant.merchantReported.mapper.KftIncomeMapper;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.merchant.reported.bean.Area;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.BankBranch;
import com.qifenqian.bms.merchant.reported.bean.City;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.FmWxCategory;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.MerchantCity;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.SumPayArea;
import com.qifenqian.bms.merchant.reported.bean.SumpayMcc;
import com.qifenqian.bms.merchant.reported.bean.TbFmTradeInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.YQBArea;
import com.qifenqian.bms.merchant.reported.bean.YQBIndustry;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;


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

public TdMerchantDetailInfo selInfo(TdMerchantDetailInfo detailInfo) {
	// TODO Auto-generated method stub
	return kftIncomeMapper.selInfo(detailInfo);
}

  

}
