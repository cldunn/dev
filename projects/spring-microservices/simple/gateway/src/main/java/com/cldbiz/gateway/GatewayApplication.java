package com.cldbiz.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableWebSecurity
@EnableRedisHttpSession
public class GatewayApplication {

	// TODO: replace @EnableWebSecurity with @Configuration class for spring security by extending WebSecurityConfigurerAdapter
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}