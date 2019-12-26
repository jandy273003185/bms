package com.qifenqian.bms.merchant.reports.service;

import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.common.bean.ResultData;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantReportDao;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:12
 */
@Service
public class TdMerchantReportService {

    @Autowired
    private TdMerchantReportDao tdMerchantReportDao;
    @Autowired
    private Map<String, TdMerchantReportDetailService> tdMerchantReportDetailServiceMap;

    /**
     * 获取调用的service
     *
     * @param channel
     * @return
     */
    public TdMerchantReportDetailService getActiveChannel(String channel) {
        channel = "tdMerchantReportDetailService" + channel;
        if (tdMerchantReportDetailServiceMap.containsKey(channel)) {
            return tdMerchantReportDetailServiceMap.get(channel);
        }
        return null;
    }

    /**
     * 处理界面参数
     *
     * @param channel
     * @param jsonString
     * @return
     */
    public TdMerchantReportDetail getDetailParams(String channel, String jsonString) {
        TdMerchantReportDetailService tdMerchantReportDetailService = getActiveChannel(channel);
        if (tdMerchantReportDetailService == null) {
            return null;
        }
        return tdMerchantReportDetailService.getDetailParams(jsonString);
    }

    public ResultData addMerchantReportDetailByChannel(String channel, TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantReportDetailService tdMerchantReportDetailService = getActiveChannel(channel);
        if (tdMerchantReportDetailService == null) {
            return ResultData.error("请确认渠道信息是否正确！");
        }
        return tdMerchantReportDetailService.addMerchantReportDetail(tdMerchantReportDetail);
    }


    public ResultData addReport(TdMerchantReportInfo merchantReport, String jsonReportDetailInfo) {
        Map<String, Object> map = new HashMap<>();
        /**
         * 查询数据的reportStatus判断当前数据是否已报备或者审核失败
         */
        String reportStatus = merchantReport.getReportStatus();

        /**
         * 如果已提交或者已审核失败返回提示
         */

        /**
         * 如果没有
         */
        merchantReport.setId(GenSN.getSN());
        merchantReport.setPatchNo(GenSN.getSN());
        merchantReport.setReportStatus("E");
        merchantReport.setDetailStatus("");
        merchantReport.setStatus("01");
        /**
         * this.dao.insert  调用dao存储
         */
        this.tdMerchantReportDao.insertTdMerchantReport(merchantReport);
        /**
         * 组织详情参数
         */
        String channel = merchantReport.getChannelNo();
        TdMerchantReportDetail tdMerchantReportDetail = getDetailParams(channel, jsonReportDetailInfo);
        tdMerchantReportDetail.setChannelNo(merchantReport.getChannelNo());
        tdMerchantReportDetail.setDetailStatus(merchantReport.getDetailStatus());
        tdMerchantReportDetail.setPatchNo(merchantReport.getPatchNo());

        /**
         * 调用渠道service存储
         */
        this.addMerchantReportDetailByChannel(channel, tdMerchantReportDetail);
        map.put("code", "SUCCESS");
        return ResultData.success();
    }
}
