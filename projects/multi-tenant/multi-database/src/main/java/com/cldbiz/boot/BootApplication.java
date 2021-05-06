package com.cldbiz.boot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import com.cldbiz.boot.hibernate.BootCurrentTenantIdentifier;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BootApplication {
	private static final Logger log = LoggerFactory.getLogger(BootApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        
        BootCurrentTenantIdentifier bootCurrentTenantIdentifier = new BootCurrentTenantIdentifier();
        String tenantIdentifier = bootCurrentTenantIdentifier.resolveCurrentTenantIdentifier();
        log.debug("ENVIRONMENT VARIABLE: " + tenantIdentifier);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

        	log.info("Let's inspect the beans provided by Spring Boot:");
        	
        	// DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
            // dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
            
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                // System.out.println(beanName);
            }

        };
    }
}
