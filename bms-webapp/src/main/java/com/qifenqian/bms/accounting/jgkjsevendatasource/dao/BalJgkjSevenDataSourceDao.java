package com.qifenqian.bms.accounting.jgkjsevendatasource.dao;

import java.util.List;

import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.jgkjsevendatasource.bean.BalJgkjSevenDataSource;
import com.qifenqian.bms.accounting.jgkjsevendatasource.mapper.BalJgkjSevenDataSourceMapper;

@Repository
public class BalJgkjSevenDataSourceDao {
	@Autowired
	private BalJgkjSevenDataSourceMapper mapper;
	@Page
	public List<BalJgkjSevenDataSource> selectSevenDataSourceList(BalJgkjSevenDataSource sevenDataSource){
		return mapper.selectSevenDataSourceList(sevenDataSource);
	}
}
