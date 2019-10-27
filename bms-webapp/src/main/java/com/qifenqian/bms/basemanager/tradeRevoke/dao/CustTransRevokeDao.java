package com.qifenqian.bms.basemanager.tradeRevoke.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qifenqian.bms.basemanager.tradeRevoke.bean.CustTransRevoke;
import com.qifenqian.bms.basemanager.tradeRevoke.mapper.CustTransRevokeMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class CustTransRevokeDao {

	@Autowired
	private CustTransRevokeMapper custTransRevokeMapper;
	
	@Page
	public List<CustTransRevoke> selectTransRevokeList(CustTransRevoke queryBean) {
		return custTransRevokeMapper.selectTransRevokeList(queryBean);
	};

	@Transactional
	public void insert(CustTransRevoke insertBean) {
		custTransRevokeMapper.insert(insertBean);
	}

	@Transactional
	public void updateByAudit(CustTransRevoke updateBean) {
		custTransRevokeMapper.updateByAudit(updateBean);
	}
	
	@Transactional
	public void updateByRevoke(CustTransRevoke updateBean) {
		custTransRevokeMapper.updateByRevoke(updateBean);
	}

}
