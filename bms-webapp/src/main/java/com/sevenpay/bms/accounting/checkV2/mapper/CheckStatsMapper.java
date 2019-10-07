package com.sevenpay.bms.accounting.checkV2.mapper;

import java.util.List;

import com.sevenpay.bms.accounting.checkV2.bean.CheckStats;
import com.sevenpay.bms.common.annotation.MapperScanPayment;
@MapperScanPayment
public interface CheckStatsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckStats record);

    int insertSelective(CheckStats record);

    CheckStats selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckStats record);

    int updateByPrimaryKey(CheckStats record);
    
    List<CheckStats> findCheckStats(CheckStats checkStats);
}