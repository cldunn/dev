package com.cldbiz.afw.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AfwApplicationContext {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	private static ApplicationContext appCtx;
	
	@PostConstruct
	public void initApplicationContext() {
		AfwApplicationContext.appCtx = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return appCtx;
	}
}
