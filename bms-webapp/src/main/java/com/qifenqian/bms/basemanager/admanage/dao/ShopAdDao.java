package com.qifenqian.bms.basemanager.admanage.dao;

import com.qifenqian.bms.basemanager.admanage.bean.AdAllCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.MchShopDO;
import com.qifenqian.bms.basemanager.admanage.bean.ShopAd;
import com.qifenqian.bms.basemanager.admanage.mapper.ShopAdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopAdDao {

    @Autowired
    private ShopAdMapper shopAdMapper;

    public int batchSaveShopAd(List<ShopAd> list) {
        return this.shopAdMapper.batchSaveShopAd(list);
    }

    public int batchDeleteShopAd(List<MchShopDO> mchShopDOList) {
        return this.shopAdMapper.batchDeleteShopAd(mchShopDOList);
    }

    public List<AdCustInfoVO> selectCustInfoOnDistributionAd(String customName) {
        return this.shopAdMapper.selectCustInfoOnDistributionAd(customName);
    }

    public List<AdAllCustInfoVO> findAdAllCustomList(String customName, String shopName) {
        return this.shopAdMapper.selectAllCustInfoOnDistributionAd(customName,shopName);
    }
}
