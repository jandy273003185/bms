package com.qifenqian.bms.basemanager.market.dao;

import java.util.List;

import org.gyzb.platform.web.admin.user.bean.User;
import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.market.bean.Manager;
import com.qifenqian.bms.basemanager.market.bean.MarketRole;
import com.qifenqian.bms.basemanager.market.mapper.ManagerMapper;
import com.qifenqian.bms.basemanager.market.mapper.MarketAuthoMapper;

@Repository
public class ManagerDao {
	
	
	@Autowired
	private ManagerMapper managerMapper;
	
	@Autowired
	private MarketAuthoMapper marketAuthoMapper;

	@Page
	public List<Manager> selectManagerList(Manager manager) {
		return managerMapper.selectManagerList(manager);
	}
	@Page
	public List<Manager> selectNewManagerList(Manager manager) {
		return managerMapper.selectNewManagerList(manager);
	}

	/**
	 * 根据查询条件获取用户详情列表
	 */
	public List<User> listInfo(){
		return managerMapper.selectManageUserInfo();
	}
	
	public Manager getManager(String userCode) {
		return managerMapper.getManager(userCode);
	}
	
}
