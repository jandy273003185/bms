package com.qifenqian.bms.merchant.reported.mapper;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface TdMerchantReportMapper {

    int updateTdMerchantReport(TdMerchantReportInfo tdInfo);

    int insertTdMerchantReport(TdMerchantReportInfo info);

}
