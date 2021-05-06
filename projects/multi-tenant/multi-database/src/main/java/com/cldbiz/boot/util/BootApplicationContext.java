package com.cldbiz.boot.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BootApplicationContext {
	@Autowired
	private ApplicationContext applicationContext;
	
	private static ApplicationContext appCtx;
	
	@PostConstruct
	public void initApplicationContext() {
		BootApplicationContext.appCtx = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return appCtx;
	}

}
