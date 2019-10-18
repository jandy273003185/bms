package com.sevenpay.bms.sns.redpacket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenpay.bms.sns.redpacket.bean.RedEnvelopeInfo;
import com.sevenpay.bms.sns.redpacket.dao.RedPacketInfoDao;
import com.sevenpay.bms.sns.redpacketdetail.bean.RedPacketDetail;
import com.sevenpay.bms.sns.redpacketdetail.dao.RedPacketDetailDao;
import com.sevenpay.bms.sns.redpacketdetail.mapper.RedPacketDetailMapper;

@Service
public class RedPacketInfoService {

	@Autowired
	private RedPacketInfoDao redPacketInfoDao;
	
	@Autowired
	private RedPacketDetailDao redPacketDetailDao;
	@Autowired
	private RedPacketDetailMapper redPacketDetailMapper;
	/***
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<RedEnvelopeInfo> selectList(RedEnvelopeInfo queryBean) {
		return redPacketInfoDao.selectList(queryBean);
	}

	/**
	 * 
	 * @param 
	 * @return
	 */
	public List<RedPacketDetail> selectRedPacketDetailByRedEnvId(RedPacketDetail queryBean) {
		return redPacketDetailMapper.selectRedPacketDetailByRedEnvId(queryBean);
	}
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	public List<RedPacketDetail> selectRedPacketDetailList(RedPacketDetail queryBean) {
		return redPacketDetailDao.selectRedPacketDetailList(queryBean);
	}

}
