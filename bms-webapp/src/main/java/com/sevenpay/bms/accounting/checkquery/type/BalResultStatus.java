package com.sevenpay.bms.accounting.checkquery.type;

import org.gyzb.platform.common.annotation.Description;

/**
 * @project sevenpay-bms-web
 * @fileName BalResultStatus.java
 * @author huiquan.ma
 * @date 2015年10月16日
 * @memo
 */
public enum BalResultStatus {

  @Description("有效")
  VALID("有效"), 
  @Description("无效")
  INVALID("无效");

  private String desc;

  private BalResultStatus(String desc) {
    this.desc = desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}


