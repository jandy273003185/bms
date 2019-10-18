package com.sevenpay.bms.basemanager.market.mapper;

import java.util.List;

import org.gyzb.platform.web.admin.user.bean.User;
import org.mybatis.spring.annotation.MapperScan;

import com.sevenpay.bms.basemanager.market.bean.Manager;
import com.sevenpay.bms.basemanager.market.bean.MarketRole;



@MapperScan
public interface ManagerMapper {

	List<Manager> selectManagerList(Manager manager);

	void updateManagerList(Manager manager);

	List<Manager> selectNewManagerList(Manager manager);

	List<MarketRole> selectManangerJurisdiction(MarketRole role);

	void insertNewManagerList(Manager manager);
	
	Manager getManager(String userCode);

	List<User> selectManageUserInfo();

}
