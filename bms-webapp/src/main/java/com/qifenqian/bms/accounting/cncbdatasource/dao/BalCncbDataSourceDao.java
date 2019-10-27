package com.qifenqian.bms.accounting.cncbdatasource.dao;

import java.util.List;

import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.cncbdatasource.bean.BalCncbDataSource;
import com.qifenqian.bms.accounting.cncbdatasource.mapper.BalCncbDataSourceMapper;
@Repository
public class BalCncbDataSourceDao {
	
	@Autowired
	private BalCncbDataSourceMapper mapper;
	
	@Page
	public List<BalCncbDataSource> selectCncbDataSourceList(BalCncbDataSource cncbDataSource){
		return mapper.selectCncbDataSourceList(cncbDataSource);
	}
}
