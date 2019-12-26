package com.qifenqian.bms.merchant.reported.dao;


import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoSuixingPay;
import com.qifenqian.bms.merchant.reported.mapper.TdMerchantDetailInfoSuixingPayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TdMerchantDetailInfoSuixingPayDao {
    @Autowired
    private TdMerchantDetailInfoSuixingPayMapper tdMerchantDetailInfoSuixingPayMapper;


    public int insert(TdMerchantDetailInfoSuixingPay tdMerchantDetailInfoSuixingPay) {
        return tdMerchantDetailInfoSuixingPayMapper.insert(tdMerchantDetailInfoSuixingPay);
    }

    public int updateDynamic(TdMerchantDetailInfoSuixingPay tdMerchantDetailInfoSuixingPay) {
        return tdMerchantDetailInfoSuixingPayMapper.updateDynamic(tdMerchantDetailInfoSuixingPay);
    }

    public TdMerchantDetailInfoSuixingPay selectByPatchNo(String patchNo) {
        return tdMerchantDetailInfoSuixingPayMapper.selectByPatchNo(patchNo);
    }
}
