package com.qifenqian.bms.merchant.serviceparenter.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;

@MapperScan
public interface ServiceParenterMapper {

	List<MerchantVo> serviceList(MerchantVo merchantVo);

	List<MerchantVo> myServicesList(MerchantVo merchantVo);

}
