package com.cldbiz.afw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cldbiz.afw.spring.AfwMessageSource;

@Configuration
public class TstWebConfig {
	
	
	@Bean(name="testAfwMessageSource")
	public AfwMessageSource messageSource() {
		AfwMessageSource parentMessageSource = new AfwMessageSource();
		System.out.println(">>" + parentMessageSource.getParentMessageSource());
		parentMessageSource.setBasename("classpath:com/cldbiz/afw/properties/ParentMsgSrc");
		parentMessageSource.setDefaultEncoding("UTF-8");
		
		AfwMessageSource applicationMessageSource = new AfwMessageSource();
		applicationMessageSource.setParentMessageSource(parentMessageSource);
		applicationMessageSource.setBasename("classpath:com/cldbiz/afw/properties/ApplicationMsgSrc");
		applicationMessageSource.setDefaultEncoding("UTF-8");

		/*
		applicationMessageSource.setBasenames(
				"classpath:com/cldbiz/afw/properties/ApplicationMsgSrc",
				"classpath:com/cldbiz/afw/properties/ParentMsgSrc");
		*/
		
		applicationMessageSource.getParentMessageSource();
		return applicationMessageSource;
	}
	
}
