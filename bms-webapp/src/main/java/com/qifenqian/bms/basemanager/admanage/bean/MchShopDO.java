package com.qifenqian.bms.basemanager.admanage.bean;

import java.io.Serializable;

public class MchShopDO implements Serializable {

    private static final long serialVersionUID = -8722898207548897505L;
    private String mchId;
    private String shopId;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
