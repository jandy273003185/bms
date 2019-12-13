package com.qifenqian.bms.basemanager.admanage.bean;

import java.io.Serializable;

public class AdDO implements Serializable {
    private static final long serialVersionUID = 7129780120556553228L;

    private String adId;
    private int sortNo;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
}
