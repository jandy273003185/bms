package com.qifenqian.bms.merchant.reports.service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.common.bean.ResultData;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAllinPay;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:08
 */
@Service("tdMerchantReportDetailService" + ChannelTypeConstants.ALLIN_PAY)
public class TdMerchantDetailInfoAllinPayService implements TdMerchantReportDetailService {

    @Override
    public TdMerchantReportDetail getDetailParams(String jsonString) {
        return JSONObject.parseObject(jsonString, TdMerchantDetailInfoAllinPay.class);
    }

    @Override
    public ResultData addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoAllinPay tdMerchantDetailInfoAllinPay = (TdMerchantDetailInfoAllinPay) tdMerchantReportDetail;

        System.out.println(tdMerchantDetailInfoAllinPay.toString());
        return ResultData.success();
    }
}
