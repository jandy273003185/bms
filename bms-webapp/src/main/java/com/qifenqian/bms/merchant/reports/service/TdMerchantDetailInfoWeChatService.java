package com.qifenqian.bms.merchant.reports.service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.common.bean.ResultData;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoWeChat;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:08
 */
@Service("tdMerchantReportDetailService" + ChannelTypeConstants.WX)
public class TdMerchantDetailInfoWeChatService implements TdMerchantReportDetailService {
    @Override
    public TdMerchantReportDetail getDetailParams(String jsonString) {
        return JSONObject.parseObject(jsonString, TdMerchantDetailInfoWeChat.class);
    }

    @Override
    public ResultData addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoWeChat tdMerchantDetailInfoWeChat = (TdMerchantDetailInfoWeChat) tdMerchantReportDetail;
        System.out.println(tdMerchantDetailInfoWeChat.toString());
        return ResultData.success();
    }
}
