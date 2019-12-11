package com.qifenqian.bms.basemanager.admanage.mapper;


import com.qifenqian.bms.basemanager.admanage.bean.AdManagement;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface AdManagementMapper {

    /**
     * 获取广告列表
     * @param queryBean
     * @return
     */
    List<AdManagement> selectAdManagementListBy(AdManagement queryBean);

    int saveAdManagement(AdManagement adManagement);

    int updateAdManagement(AdManagement adManagement);
}
