package com.qifenqian.bms.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 主要用于适配以前老的配置
 * @author xun.sun
 *
 */

@ImportResource("classpath*:spring/ApplicationContext.xml")
@Configuration
public class WebConfigure {

}
