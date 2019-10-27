package com.qifenqian.bms.configure;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiObjectFactoryBean;

//@Configuration
public class TomcatConfigs {

  @Bean
  public TomcatServletWebServerFactory tomcatFactory() {

    return new TomcatServletWebServerFactory() {
      @Override
      protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
        tomcat.enableNaming();
        return super.getTomcatWebServer(tomcat);
      }

      @Override
      protected void postProcessContext(Context context) {

        context.getNamingResources().addResource(coreResource());
        context.getNamingResources().addResource(operResource());
        context.getNamingResources().addResource(slaveResource());
        context.getNamingResources().addResource(sabsMasterResource());
        context.getNamingResources().addResource(combinedmasterResource());
        context.getNamingResources().addResource(combinedslaveResource());
      }
    };
  }

  @Bean
  public DataSource coreJndiDataSource() throws IllegalArgumentException, NamingException {
    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
    bean.setJndiName("java:/comp/env/jdbc/core");
    bean.setProxyInterface(DataSource.class);
    bean.setLookupOnStartup(false);
    bean.afterPropertiesSet();
    return (DataSource) bean.getObject();
  }

  @Bean
  public DataSource slaveJndiDataSource() throws IllegalArgumentException, NamingException {
    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
    bean.setJndiName("java:/comp/env/jdbc/slave");
    bean.setProxyInterface(DataSource.class);
    bean.setLookupOnStartup(false);
    bean.afterPropertiesSet();
    return (DataSource) bean.getObject();
  }

  @Bean
  public DataSource sabsMasterJndiDataSource() throws IllegalArgumentException, NamingException {
    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
    bean.setJndiName("java:/comp/env/jdbc/sabsMaster");
    bean.setProxyInterface(DataSource.class);
    bean.setLookupOnStartup(false);
    bean.afterPropertiesSet();
    return (DataSource) bean.getObject();
  }

  @Bean
  public DataSource combinedmasterJndiDataSource()
      throws IllegalArgumentException, NamingException {
    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
    bean.setJndiName("java:/comp/env/jdbc/combinedmaster");
    bean.setProxyInterface(DataSource.class);
    bean.setLookupOnStartup(false);
    bean.afterPropertiesSet();
    return (DataSource) bean.getObject();
  }

  @Bean
  public DataSource combinedslaveJndiDataSource() throws IllegalArgumentException, NamingException {
    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
    bean.setJndiName("java:/comp/env/jdbc/combinedslave");
    bean.setProxyInterface(DataSource.class);
    bean.setLookupOnStartup(false);
    bean.afterPropertiesSet();
    return (DataSource) bean.getObject();
  }

  private ContextResource coreResource() {
    ContextResource resource = new ContextResource();
    resource.setType(DataSource.class.getName());
    resource.setName("jdbc/core");
    resource.setProperty("factory", "com.alibaba.druid.pool.DruidDataSourceFactory");
    resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
    resource.setProperty(
        "url", "jdbc:mysql://192.168.1.17:3306/stcs?zeroDateTimeBehavior=convertToNull");
    resource.setProperty("username", "stcs_user");
    resource.setProperty("password", "123456");
    return resource;
  }

  private ContextResource operResource() {
    ContextResource resource = new ContextResource();
    resource.setType(DataSource.class.getName());
    resource.setName("jdbc/oper");
    resource.setProperty("factory", "com.alibaba.druid.pool.DruidDataSourceFactory");
    resource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
    resource.setProperty(
        "url", "jdbc:mysql://192.168.1.17:3306/stcdb?zeroDateTimeBehavior=convertToNull");
    resource.setProperty("username", "stc_web_user");
    resource.setProperty("password", "123456");
    return resource;
  }

  private ContextResource slaveResource() {
    ContextResource resource = new ContextResource();
    resource.setType(DataSource.class.getName());
    resource.setName("jdbc/slave");
    resource.setProperty("factory", "com.alibaba.druid.pool.DruidDataSourceFactory");
    resource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
    resource.setProperty(
        "url", "jdbc:mysql://192.168.1.18:3306/stcs?zeroDateTimeBehavior=convertToNull");
    resource.setProperty("username", "stcs_user");
    resource.setProperty("password", "123456");
    return resource;
  }

  private ContextResource sabsMasterResource() {
    ContextResource resource = new ContextResource();
    resource.setType(DataSource.class.getName());
    resource.setName("jdbc/sabsMaster");
    resource.setProperty("factory", "com.alibaba.druid.pool.DruidDataSourceFactory");
    resource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
    resource.setProperty(
        "url", "jdbc:mysql://192.168.1.18:3306/sabs?zeroDateTimeBehavior=convertToNull");
    resource.setProperty("username", "sabs_user");
    resource.setProperty("password", "123456");
    return resource;
  }

  private ContextResource combinedmasterResource() {
    ContextResource resource = new ContextResource();
    resource.setType(DataSource.class.getName());
    resource.setName("jdbc/combinedmaster");
    resource.setProperty("factory", "com.alibaba.druid.pool.DruidDataSourceFactory");
    resource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
    resource.setProperty(
        "url", "jdbc:mysql://192.168.1.17:3306/combinedpayment?zeroDateTimeBehavior=convertToNull");
    resource.setProperty("username", "combinedpayment");
    resource.setProperty("password", "combinedpayment");
    return resource;
  }

  private ContextResource combinedslaveResource() {
    ContextResource resource = new ContextResource();
    resource.setType(DataSource.class.getName());
    resource.setName("jdbc/combinedmaster");
    resource.setProperty("factory", "com.alibaba.druid.pool.DruidDataSourceFactory");
    resource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
    resource.setProperty(
        "url", "jdbc:mysql://192.168.1.18:3306/combinedpayment?zeroDateTimeBehavior=convertToNull");
    resource.setProperty("username", "combinedpayment");
    resource.setProperty("password", "combinedpayment");
    return resource;
  }
}
