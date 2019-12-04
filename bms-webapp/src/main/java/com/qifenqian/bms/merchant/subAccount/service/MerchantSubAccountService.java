package com.qifenqian.bms.merchant.subAccount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.merchant.subAccount.bean.MerchantSubAccouontBean;
import com.qifenqian.bms.merchant.subAccount.dao.MerchantSubAccountDao;
import com.qifenqian.bms.merchant.subAccount.mapper.MerchantSubAccountMapper;


@Service
public class MerchantSubAccountService {

	@Autowired
	private MerchantSubAccountDao merchantSubAccountDao;
	
	@Autowired
	private MerchantSubAccountMapper merchantSubAccountMapper;
	
	/**
	 * 查询商户分账
	 * @param merchantSubAccouontBean
	 * @return
	 */
	public List<MerchantSubAccouontBean> selectSubAccountList(MerchantSubAccouontBean merchantSubAccouontBean) {
		
		return merchantSubAccountDao.selectSubAccountList(merchantSubAccouontBean);
	}

	/**
	 * 商户分账新增
	 * @return
	 */
	public int insterSubAccount(MerchantSubAccouontBean merchantSubAccouont) {
		int i = merchantSubAccountDao.insterSubAccount(merchantSubAccouont);
		return i;
	}

}
