package com.qifenqian.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Bootstrap /*extends SpringBootServletInitializer*/ {
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(Bootstrap.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Bootstrap.class, args);
  }
}
