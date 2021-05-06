package com.cldbiz.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cldbiz.boot.spring.BootMessageSource;

@Configuration
public class TstWebConfig {
	
	
	@Bean(name="testBootMessageSource")
	public BootMessageSource messageSource() {
		BootMessageSource parentMessageSource = new BootMessageSource();
		System.out.println(">>" + parentMessageSource.getParentMessageSource());
		parentMessageSource.setBasename("classpath:com/cldbiz/boot/properties/ParentMsgSrc");
		parentMessageSource.setDefaultEncoding("UTF-8");
		
		BootMessageSource applicationMessageSource = new BootMessageSource();
		applicationMessageSource.setParentMessageSource(parentMessageSource);
		applicationMessageSource.setBasename("classpath:com/cldbiz/boot/properties/ApplicationMsgSrc");
		applicationMessageSource.setDefaultEncoding("UTF-8");

		/*
		applicationMessageSource.setBasenames(
				"classpath:com/cldbiz/boot/properties/ApplicationMsgSrc",
				"classpath:com/cldbiz/boot/properties/ParentMsgSrc");
		*/
		
		applicationMessageSource.getParentMessageSource();
		return applicationMessageSource;
	}
	
}
