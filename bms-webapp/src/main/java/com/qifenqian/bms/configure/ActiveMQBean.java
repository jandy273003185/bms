package com.qifenqian.bms.configure;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQBean {
	
	@Value("${MQ_URL}")
	private String MQ_URL;
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(MQ_URL);
		factory.setUserName("admin");
		factory.setPassword("admin");
		return factory;
	}
	

}
