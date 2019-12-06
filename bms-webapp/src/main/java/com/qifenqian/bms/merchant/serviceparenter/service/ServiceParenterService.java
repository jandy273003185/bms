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
	
	/**新  查询代理商列表  //增加待审核状态 zhanggc**/
	public List<MerchantVo> selectNewServices(MerchantVo merchantVo){
		return serviceDao.serviceNewList(merchantVo);
	}
	
	/**
	 * @param fals 标识  yes 审核通过  ; no 服务商审核不通过
	 *  zhanggc 服务商审核状态
	 */
	public String updateState(String custId , String fals) {
		
		int i =0;//标识
		int j =0;//标识
		//yes 审核通过
		if ("yes".equals(fals))
		{
			 i = serviceDao.updateTdCustInfoState(custId, "00","01");//00 有效；01 待审核；02 注销；03 冻结；04 审核不通过;05 待完善',
			 j = serviceDao.updateTdCertificateAuthState(custId, "0" ,"1");//0 审核通过  1 待审核  2 审核不通过
		}
		//服务商审核不通过
		if ("no".equals(fals))
		{
			 i = serviceDao.updateTdCustInfoState(custId, "04","01");//00 有效；01 待审核；02 注销；03 冻结；04 审核不通过;05 待完善',
			 j = serviceDao.updateTdCertificateAuthState(custId, "2","1");//0 审核通过  1 待审核  2 审核不通过
		}
		
		if (i == 0 || j == 0) 
		{
			return "FAILE";
		}
		return "SUCCESS";
	}
	
}
