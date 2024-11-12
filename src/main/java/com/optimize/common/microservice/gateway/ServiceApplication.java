package com.optimize.common.microservice.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class ServiceApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext app = new SpringApplicationBuilder(
                ServiceApplication.class)
                .build().run(args);
        Environment env = app.getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("""
            
            ----------------------------------------------------------
            Application  '{}' is running!
            Version:      {}
            Access URLs:
            Local:        {}://localhost:{}
            Profile(s):   {}
            (c) Optimize Inc. All rights reserved.
            ----------------------------------------------------------
            """,
                env.getProperty("spring.application.name"),
                env.getProperty("spring.application.version"),
                protocol,
                env.getProperty("server.port"),
                env.getActiveProfiles());
	}

    @Bean
    DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(
            ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp
    ) {
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }

}
