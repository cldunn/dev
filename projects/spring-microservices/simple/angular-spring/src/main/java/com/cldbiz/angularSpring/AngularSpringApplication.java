package com.cldbiz.angularSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableZuulProxy
@EnableJpaRepositories("com.cldbiz.angularSpring.dao")
@SpringBootApplication(scanBasePackages = {
	"com.cldbiz.angularSpring"
})
public class AngularSpringApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AngularSpringApplication.class, args);
	}

}
