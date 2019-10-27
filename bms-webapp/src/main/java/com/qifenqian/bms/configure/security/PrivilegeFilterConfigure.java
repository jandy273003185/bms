package com.qifenqian.bms.configure.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *权限过滤的bean
 * @author Kevin
 *
 */

@Configuration
public class PrivilegeFilterConfigure {

  @Bean
  public FilterRegistrationBean<PrivilegeFilter> privilegeFilter() {
    PrivilegeFilter privilege = new PrivilegeFilter();
    FilterRegistrationBean<PrivilegeFilter> filter =
        new FilterRegistrationBean<PrivilegeFilter>(privilege);
    filter.setOrder(Integer.MIN_VALUE);
    
    return filter;
  }
}
