package com.cldbiz.angularSpring.common;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;

import com.cldbiz.angularSpring.dto.UserProfileDto;
import com.cldbiz.angularSpring.spring.component.AfwEnvironment;

public class AfwContext {
	@Resource
	protected Environment env;

	private String dsKey;
	private Locale locale;
	private UserProfileDto userProfileDto;
	
	public AfwContext() {
		this.dsKey = AfwConstants.TENANT_IDENTIFIER_DEFAULT;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getDsKey() {
		return dsKey;
	}

	public void setDsKey(String dsKey) {
		this.dsKey = dsKey;
	}

	public UserProfileDto getUserProfileDto() {
		return userProfileDto;
	}

	public void setUserProfileDto(UserProfileDto userProfileDto) {
		this.userProfileDto = userProfileDto;
	}

}
