package com.qifenqian.bms.basemanager.branchbank.dao;

import java.util.List;

import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.branchbank.bean.BranchBank;
import com.qifenqian.bms.basemanager.branchbank.mapper.BranchBankMapper;

@Repository
public class BranchBankDao {

	@Autowired
	private BranchBankMapper branchBankMapper;

	@Page
	public List<BranchBank> branchBankList(BranchBank queryBean) {
		return branchBankMapper.branchBankList(queryBean);
	}

}
