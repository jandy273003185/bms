package com.qifenqian.bms.merchant.serviceparenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.merchant.serviceparenter.dao.ServiceParenterDao;

@Service
public class ServiceParenterService {
	
	
	@Autowired
	private ServiceParenterDao serviceDao;
	
	/**代理商列表查询 **/
	public List<MerchantVo> selectServices(MerchantVo merchantVo){
		return serviceDao.serviceList(merchantVo);
	}

	public List<MerchantVo> myServicesList(MerchantVo merchantVo) {
		// TODO Auto-generated method stub
		return serviceDao.myServicesList(merchantVo);
	}
}
