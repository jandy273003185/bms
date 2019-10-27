package com.qifenqian.bms.basemanager.rechargeRevoke.dao;

import java.util.List;

import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.rechargeRevoke.bean.RechargeRevoke;
import com.qifenqian.bms.basemanager.rechargeRevoke.mapper.RechargeRevokeMapper;

@Repository
public class RechargeRevokeDao {
	
	@Autowired
	private RechargeRevokeMapper rechargeRevokeMapper;
	
	@Page
	public List<RechargeRevoke> selectTransRevokeList(RechargeRevoke queryBean) {
		return rechargeRevokeMapper.selectRechargeRevokeList(queryBean);
	};
	
	
}
