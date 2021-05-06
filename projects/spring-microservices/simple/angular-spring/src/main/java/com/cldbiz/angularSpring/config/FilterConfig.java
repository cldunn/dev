package com.cldbiz.angularSpring.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cldbiz.angularSpring.filter.CrossSiteScriptFilter;
import com.cldbiz.angularSpring.filter.InitializeFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean initializeFilterBean() {
		final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setName("InitializeFilter");
		filterRegistrationBean.setFilter(new InitializeFilter());
		
		filterRegistrationBean.addUrlPatterns(new String[] {
			"/rest/*"
		});
		
		return filterRegistrationBean;
	}
	
	
	@Bean
	public FilterRegistrationBean crossSiteScriptFilterBean() {
		final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.setName("CrossSiteScriptFilter");
		filterRegistrationBean.setFilter(new CrossSiteScriptFilter());
		
		filterRegistrationBean.addUrlPatterns(new String[] {
			"/rest/*"
		});
		
		return filterRegistrationBean;
	}
	
}
