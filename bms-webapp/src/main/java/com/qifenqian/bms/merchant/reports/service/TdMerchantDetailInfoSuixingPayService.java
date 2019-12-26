package com.qifenqian.bms.merchant.reports.service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoSuixingPay;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:08
 */
@Service("tdMerchantReportDetailService" + ChannelTypeConstants.SUIXING_PAY)
public class TdMerchantDetailInfoSuixingPayService implements TdMerchantReportDetailService {
    @Override
    public TdMerchantReportDetail getDetailParams(String jsonString) {
        return JSONObject.parseObject(jsonString, TdMerchantDetailInfoSuixingPay.class);
    }

    @Override
    public int addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoSuixingPay tdMerchantDetailInfoSuixingPay = (TdMerchantDetailInfoSuixingPay) tdMerchantReportDetail;

        System.out.println(tdMerchantDetailInfoSuixingPay.toString());
        return 0;
    }
}
