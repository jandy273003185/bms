package com.qifenqian.bms.configure;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置， 暂时不使用
 * @author xun.sun
 *
 */
@Component
public class DataSourceConfigure {
  /*@Bean(name = "dataSourceCombinedmaster", initMethod = "init", destroyMethod = "close")
  public DataSource dataSourceCombinedmaster() throws SQLException {
    DruidDataSource ds = new DruidDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl("jdbc:mysql://192.168.1.17:3306/combinedpayment?zeroDateTimeBehavior=convertToNull");
    ds.setUsername("combinedpayment");
    ds.setPassword("combinedpayment");
    ds.setInitialSize(10);
    ds.setMaxActive(50);
    ds.setMinIdle(3);
    ds.setMaxWait(1000);
    ds.setPoolPreparedStatements(true);
    ds.setMaxPoolPreparedStatementPerConnectionSize(33);
    ds.setValidationQuery("SELECT 1");
    ds.setTestWhileIdle(true);
    ds.setTestOnBorrow(false);
    ds.setTestOnReturn(false);
    ds.setTimeBetweenEvictionRunsMillis(60000);
    ds.setMinEvictableIdleTimeMillis(60000);
    ds.setRemoveAbandoned(true);
    ds.setRemoveAbandonedTimeout(18000);
    ds.setLogAbandoned(true);
    ds.setFilters("mergeStat,stat");
    ds.setUseUnfairLock(true);

    return ds;
  }*/
}
