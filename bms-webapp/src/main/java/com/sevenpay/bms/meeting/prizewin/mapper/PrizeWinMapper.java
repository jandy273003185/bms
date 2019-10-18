package com.sevenpay.bms.meeting.prizewin.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.sevenpay.bms.meeting.prizewin.bean.PrizeWinBean;
import com.sevenpay.bms.meeting.prizewin.bean.PrizeWinExcel;

@MapperScan
public interface PrizeWinMapper {
	
	public void insertList(List<PrizeWinBean> prizeWinList);

	List<PrizeWinBean> selectPrizeWinList(PrizeWinBean queryBean);

	int updatePrizeWin(PrizeWinBean deleteBean);
	
	List<PrizeWinExcel> selectPrizeWinExcel(PrizeWinBean queryBean);
}
