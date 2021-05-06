package com.cldbiz.afw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.cldbiz.afw.spring.AfwMessageSource;

@Configuration
@EnableWebMvc
@ComponentScan(value = {
	"com.cldbiz.afw.controller.**"
})
public class AfwWebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(AfwWebConfig.class);
	
	@Bean
	public AfwMessageSource messageSource() {
		AfwMessageSource afwMessageSource = new AfwMessageSource();
		afwMessageSource.setBasename("classpath:AfwMessages");
		afwMessageSource.setDefaultEncoding("UTF-8");
		
		return afwMessageSource;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		String[] beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, Object.class);
		for (String beanName : beanNames) {
			log.debug("Created bean named: " + beanName);
		}
	}
}
