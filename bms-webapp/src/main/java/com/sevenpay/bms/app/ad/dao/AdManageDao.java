package com.sevenpay.bms.app.ad.dao;

import java.util.List;

import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sevenpay.bms.app.ad.bean.AdManageBean;
import com.sevenpay.bms.app.ad.mapper.AdManageMapper;
/**
 * APP登录页广告管理Dao
 * @author hongjiagui
 *
 */
@Repository
public class AdManageDao {

	@Autowired
	private AdManageMapper adManageMapper;
	
	/**
	 * 根据查询条件获取APP登录页广告列表
	 */
	@Page
	public List<AdManageBean> listAdManageInfo(AdManageBean queryBean){
		return adManageMapper.listAdManageInfo(queryBean);
	}
	/**
	 * 保存APP登录页广告信息
	 */
	public void saveAdManageInfo(AdManageBean adManageInfo) {
		adManageMapper.saveAdManageInfo(adManageInfo);
	}
	/**
	 * 修改APP登录页广告信息
	 */
	public void updateAdManageInfo(AdManageBean adManageInfo) {
		adManageMapper.updateAdManageInfo(adManageInfo);
	}
	/**
	 * 删除APP登录页广告信息
	 */
	public void deleteAdManageInfoByAdId(String adId) {
		adManageMapper.deleteAdManageInfoByAdId(adId);
	}
}
