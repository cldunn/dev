package com.cldbiz.angularSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.cldbiz.angularSpring.spring.bean.AfwMessageSource;
import com.cldbiz.angularSpring.spring.component.AppEnvironment;

public abstract class BaseController {
	@Autowired
	protected AfwMessageSource messagSource;
	
	@Autowired
	protected AppEnvironment appEnv;
}
