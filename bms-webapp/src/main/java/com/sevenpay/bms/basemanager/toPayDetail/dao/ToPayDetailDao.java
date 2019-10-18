package com.sevenpay.bms.basemanager.toPayDetail.dao;

import java.util.List;

import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sevenpay.bms.basemanager.toPayDetail.bean.ToPaySingleDetail;
import com.sevenpay.bms.basemanager.toPayDetail.mapper.ToPayDetailMapper;

@Repository
public class ToPayDetailDao {

	@Autowired
	private ToPayDetailMapper mapper;

	@Page
	public List<ToPaySingleDetail> listDetail(ToPaySingleDetail bean) {
		return mapper.listDetail(bean);
	}

	public List<ToPaySingleDetail> exportDetailList(ToPaySingleDetail toPaySingleDetail) {
		
		return mapper.exportDetailList(toPaySingleDetail);
	}
	
}
