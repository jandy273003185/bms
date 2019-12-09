package com.qifenqian.bms.merchant.reported.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.reported.bean.City;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantBankInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppAreaInfo;

@MapperScan
public interface WeChatAppMapper {

	List<WeChatAppAreaInfo> getProvinceName();
	
	WeChatAppAreaInfo selectWxAreaInfo(String areaName);
	
	TdMerchantBankInfo getMerchantBankInfo(String custId);
	
	City getTbSpCity(String cityId);
	
	public TdMerchantDetailInfo selTdMerchantDetailInfo(TdMerchantDetailInfo tdInfo);
	
	public void updateTdMerchantReport(TdMerchantDetailInfo tdInfo);
	
	public void updateTdMerchantDetailInfo(TdMerchantDetailInfo tdInfo);

}
