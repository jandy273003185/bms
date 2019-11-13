package com.qifenqian.bms.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.common.bean.BranchBankInfo;
import com.qifenqian.bms.merchant.reported.bean.Province;

@MapperScan	
public interface CommonInfoMapper {

	public List<Province> selProvinceList();
	
	public List<BranchBankInfo> branchBankList(BranchBankInfo queryBean);
	
	public List<Bank> selectSuiXingBanks();

	public List<Province> selSuiXingProvince();

	public List<City> selSuiXingCity(@Param("provinceId")String provinceId);

	public List<BranchBankInfo> suiXingBranchBankList(BranchBankInfo queryBean);

	
	

	
}
