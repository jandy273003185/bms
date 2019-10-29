package com.sevenpay.bms.merchant.merchantReported.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.sevenpay.bms.merchant.merchantReported.bean.KFTArea;
import com.sevenpay.bms.merchant.merchantReported.bean.KFTMccBean;
import com.sevenpay.bms.merchant.reported.bean.Industry;

@MapperScan
public interface KftIncomeMapper {

	public List<KFTArea> selKftProvinceList();

	public List<KFTMccBean> selKftIndustryList();

	public List<KFTMccBean> selKftIndustryTwoList(KFTMccBean kFTMccBean);

	public List<KFTMccBean> selKftIndustryThreeList(KFTMccBean kFTMccBean);

	public List<KFTArea> selKftCityList(KFTArea kftArea);
	
	public List<KFTArea> selKftAreaList(KFTArea kftArea);

	
}
