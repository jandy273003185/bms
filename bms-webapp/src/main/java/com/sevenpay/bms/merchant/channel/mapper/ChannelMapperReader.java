package com.sevenpay.bms.merchant.channel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sevenpay.bms.common.annotation.MapperScanPayment;
import com.sevenpay.bms.merchant.channel.bean.ChannelBean;
import com.sevenpay.bms.merchant.channel.bean.ChannelDetailBean;

@MapperScanPayment
public interface ChannelMapperReader {

	List<ChannelBean> selectChannels(ChannelBean queryBean);

	List<ChannelDetailBean> selectChannelDetails(ChannelDetailBean queryBean);

	ChannelBean selectChannel(@Param("custId")String custId, @Param("channelCode")String channelCode, @Param("merNo")String merNo);

	List<ChannelDetailBean> selectChannelDetail(@Param("custId")String custId, @Param("merNo")String merNo);
}
