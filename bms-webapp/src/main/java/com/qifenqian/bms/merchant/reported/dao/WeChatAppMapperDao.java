package com.qifenqian.bms.merchant.reported.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.reported.bean.WeChatAppAreaInfo;
import com.qifenqian.bms.merchant.reported.mapper.WeChatAppMapper;


@Repository
public class WeChatAppMapperDao {

	@Autowired
	private WeChatAppMapper weChatAppMapper;
	
	public List<WeChatAppAreaInfo> getProvinceName() {
		
		return weChatAppMapper.getProvinceName();
	}
	
	
}
