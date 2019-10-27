package com.qifenqian.bms.basemanager.merchant.dao;

import java.util.List;

import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;
import com.qifenqian.bms.basemanager.merchant.mapper.StoreManageMapper;


@Repository
public class StoreManageDao {
	
	@Autowired
	private StoreManageMapper mapper;
	
	
	@Page
	public List<StoreManage> getStoreList(StoreManage storeManage){
		return mapper.getStoreList(storeManage);
	}
}
