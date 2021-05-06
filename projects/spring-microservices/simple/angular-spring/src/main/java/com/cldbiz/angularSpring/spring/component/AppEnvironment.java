package com.cldbiz.angularSpring.spring.component;

import org.springframework.stereotype.Component;

@Component
public class AppEnvironment extends AfwEnvironment {
	public String getAutoconfigureExclude() {
		return env.getProperty("spring.autoconfigure.exclude");
	}
}
