package com.sevenpay.bms.basemanager.market.service;

import java.util.List;

import org.gyzb.platform.web.admin.user.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenpay.bms.basemanager.market.bean.Manager;
import com.sevenpay.bms.basemanager.market.bean.MarketRole;
import com.sevenpay.bms.basemanager.market.dao.ManagerDao;
import com.sevenpay.bms.basemanager.market.mapper.ManagerMapper;


@Service
public class ManagerService {
	
	
	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private ManagerMapper managerMapper;
	
	public List<Manager> selectManagerList(Manager manager) {
		
		return managerDao.selectManagerList(manager);
	}

	public void updateManagerList(Manager manager) {
		
		managerMapper.updateManagerList(manager);
	}

	public List<Manager> selectNewManagerList(Manager manager) {
		
		return managerDao.selectNewManagerList(manager);
	}

	public List<User> listInfo(){
		return managerDao.listInfo();
	}
	
	public Manager getManager(String userCode) {
		return managerDao.getManager(userCode);
	}
}
