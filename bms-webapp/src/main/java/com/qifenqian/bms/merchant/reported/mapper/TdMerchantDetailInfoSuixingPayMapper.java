package com.qifenqian.bms.merchant.reported.mapper;


import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoSuixingPay;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface TdMerchantDetailInfoSuixingPayMapper {
    int insert(TdMerchantDetailInfoSuixingPay tdMerchantDetailInfoSuixingPay);

    int updateDynamic(TdMerchantDetailInfoSuixingPay tdMerchantDetailInfoSuixingPay);

    TdMerchantDetailInfoSuixingPay selectByPatchNo(String patchNo);
}
