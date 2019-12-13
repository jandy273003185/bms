package com.qifenqian.bms.basemanager.admanage.service;


import com.qifenqian.bms.basemanager.admanage.bean.*;
import com.qifenqian.bms.basemanager.admanage.dao.ShopAdDao;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopAdService {

    @Autowired
    private ShopAdDao shopAdDao;

    public String distributionShopAd(ShopAdDO shopAdDO) {
        /**
         * 取出广告ID
         */
        List<AdDO> adIds = shopAdDO.getAdDOList();
        /**
         * 取出商户门店信息
         */
        List<MchShopDO> mchShopDOList = shopAdDO.getMchShopDOList();
        if (CollectionUtils.isEmpty(mchShopDOList)) {
            mchShopDOList = this.findMchShopDOList(mchShopDOList);
        }
        if (CollectionUtils.isEmpty(mchShopDOList)) {
            return "FALSE";
        }
        ShopAd shopAd = null;
        List<ShopAd> list = new ArrayList<>();
        for (MchShopDO mchShopDO : mchShopDOList) {
            for (AdDO adDO : adIds) {
                shopAd = new ShopAd();
                //设置ID为UUID生成的32位随机数
                shopAd.setShopAdId(GenSN.getSN());
                //设置当前时间为创建时间
                shopAd.setCreateTime(new Date());
                //将当前登录用户的ID设置放入创建者的字段中
                shopAd.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
                //设置广告
                shopAd.setAdId(adDO.getAdId());
                shopAd.setSortNo(adDO.getSortNo());
                //设置商户
                shopAd.setMchId(mchShopDO.getMchId());
                //设置门店
                shopAd.setShopId(mchShopDO.getShopId());
                list.add(shopAd);
            }
        }
        if (CollectionUtils.isEmpty(list)) {
            return "FALSE";
        }
        try {
            shopAdDao.batchDeleteShopAd(mchShopDOList);
            shopAdDao.batchSaveShopAd(list);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FALSE";
        }
    }

    /**
     * @Author LiBin
     * @Description 如果没有商户 则全选商户门店
     * @Param
     * @Return
     * @Date 2019/12/12 0012 22:08
     */
    private List<MchShopDO> findMchShopDOList(List<MchShopDO> mchShopDOList) {
        if (null == mchShopDOList) {
            mchShopDOList = new ArrayList<>();
        }
        List<AdAllCustInfoVO> adAllCustInfoVOList = findAdAllCustomList(null, null);
        if (CollectionUtils.isEmpty(adAllCustInfoVOList)) {
            return mchShopDOList;
        }
        MchShopDO mchShopDO = null;
        for (AdAllCustInfoVO adAllCustInfoVO : adAllCustInfoVOList) {
            mchShopDO = new MchShopDO();
            mchShopDO.setMchId(adAllCustInfoVO.getCustId());
            mchShopDO.setShopId(adAllCustInfoVO.getShopId());
            mchShopDOList.add(mchShopDO);
        }
        return mchShopDOList;
    }

    public List<AdCustInfoVO> findAdCustomList(String customName) {
        return this.shopAdDao.selectCustInfoOnDistributionAd(customName);
    }

    public List<AdAllCustInfoVO> findAdAllCustomList(String customName, String shopName) {
        return this.shopAdDao.findAdAllCustomList(customName, shopName);
    }
}
