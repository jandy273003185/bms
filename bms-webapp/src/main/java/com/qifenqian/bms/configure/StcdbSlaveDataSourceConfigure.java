package com.qifenqian.bms.configure;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.qifenqian.bms.common.annotation.MapperScanSub;

/**
 * 数据源配置， 暂时不使用
 *
 * @author xun.sun
 */
@Configuration
@MapperScan(
    basePackages = "com.qifenqian.bms",
    sqlSessionFactoryRef = "stcdbSqlSessionFactory", annotationClass = MapperScanSub.class)
public class StcdbSlaveDataSourceConfigure {

  @ConfigurationProperties("spring.datasource.stcdbslave")
  @Bean(name = "stcdbslaveDataSource")
  public DruidDataSource stcdbDataSource() {
	  DruidDataSource ds  =  new DruidDataSource();
	  return ds;
  }

  @Bean(name = "stcdbslaveTransactionManager")
  public DataSourceTransactionManager combinedmasterTransactionManager(@Qualifier("stcdbslaveDataSource")DataSource ds) {
    return new DataSourceTransactionManager(ds);
  }

  @Bean(name = "stcdbslaveSqlSessionFactory")
  public SqlSessionFactory masterSqlSessionFactory(
      @Qualifier("stcdbslaveDataSource") DataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/MyBatisMapConfig.xml"));
    /*sessionFactoryBean.setMapperLocations(
    new PathMatchingResourcePatternResolver().getResources(MAPPER));*/
    return sessionFactoryBean.getObject();
  }

  /*  @Bean("combinedmasterMaperScan")
  public MapperScannerConfigurer combinedmasterMapperScan() {
    MapperScannerConfigurer bean = new MapperScannerConfigurer();
    bean.setSqlSessionFactoryBeanName("combinedmasterSqlSessionFactory");
    bean.setBasePackage("com.qifenqian.bms");
    bean.setAnnotationClass(com.qifenqian.bms.common.annotation.MapperScanCombinedmaster.class);
    return bean;
  }*/
}
