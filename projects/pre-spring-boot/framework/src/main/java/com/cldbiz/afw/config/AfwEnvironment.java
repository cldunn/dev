package com.cldbiz.afw.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class AfwEnvironment extends PropertySourcesPlaceholderConfigurer {
	private static Properties props;
	
	@Override
	protected Properties mergeProperties() throws IOException {
		props = super.mergeProperties();
		return props;
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}
