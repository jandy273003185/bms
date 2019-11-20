package com.qifenqian.bms.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.qifenqian.bms.common.util.PropertiesUtil;

@Configuration
public class DubboConfig {
	@Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("bms-webapp");
        applicationConfig.setQosEnable(false);
        return applicationConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        // 设置
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(3000);
        consumerConfig.setCheck(false);
        return consumerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        Properties p = PropertiesUtil.getProperties();
        String address = p.getProperty("DUBBO_CONFIG_IP");
        registryConfig.setAddress(address);
        registryConfig.setClient("curator");
        return registryConfig;
    }
}
