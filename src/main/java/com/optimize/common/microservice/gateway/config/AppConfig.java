package com.optimize.common.microservice.gateway.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource(value = {"classpath:application.yml", "${user.home}/.optimize/gateway-service/conf/application.yml"}, ignoreResourceNotFound = true)
public class AppConfig {
}
