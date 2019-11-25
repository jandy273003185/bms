package com.qifenqian.bms.merchant.serviceparenter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ServiceParenterDao {
	
	@Autowired
	private ServiceParenterMapper serviceParenterMapper;
	/**
	 * 查询代理商列表
	 * @param merchantVo
	 * @return
	 */
	@Page
	public List<MerchantVo> serviceList(MerchantVo merchantVo) {
		return serviceParenterMapper.serviceList(merchantVo);
	}
	/**
	 * 查询自己权限下代理商列表
	 * @param merchantVo
	 * @return
	 */
	@Page
	public List<MerchantVo> myServicesList(MerchantVo merchantVo) {
		return serviceParenterMapper.myServicesList(merchantVo);
	}

}
