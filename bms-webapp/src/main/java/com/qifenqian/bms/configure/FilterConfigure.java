package com.qifenqian.bms.configure;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qifenqian.bms.configure.log.LogFilter;
import com.qifenqian.bms.configure.security.PrivilegeFilter;

@Configuration
public class FilterConfigure {
  /**
   * 权限过滤
   *
   * @return
   */
  @Bean
  public FilterRegistrationBean<PrivilegeFilter> privilegeFilter() {
    PrivilegeFilter privilege = new PrivilegeFilter();
    FilterRegistrationBean<PrivilegeFilter> filter =
        new FilterRegistrationBean<PrivilegeFilter>(privilege);
    filter.setOrder(Integer.MIN_VALUE);
    filter.addUrlPatterns("/*");

    return filter;
  }

  @Bean
  public FilterRegistrationBean<LogFilter> logFilter() {
    LogFilter logFilter = new LogFilter();
    FilterRegistrationBean<LogFilter> filter = new FilterRegistrationBean<LogFilter>(logFilter);
    filter.setOrder(Integer.MIN_VALUE);
    filter.addUrlPatterns("/*");
    return filter;
  }
}
