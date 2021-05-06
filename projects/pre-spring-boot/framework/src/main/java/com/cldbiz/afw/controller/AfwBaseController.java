package com.cldbiz.afw.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.cldbiz.afw.config.AfwExecutionContext;
import com.cldbiz.afw.dto.UserInfoDto;
import com.cldbiz.afw.security.AfwPrincipal;
import com.cldbiz.afw.spring.AfwMessageSource;

public abstract class AfwBaseController {
	@Autowired
	AfwMessageSource msgSrc;
	
	protected String getMessage(String key) {
		return msgSrc.getMessage(key);
	}

	protected String getMessage(String key, Object... args) {
		return msgSrc.getMessage(key, args);
	}
	
	protected Map<String, String> getAllKeyPrefixedMessages(String... prefixes) {
		return msgSrc.getAllKeyPrefixedMessages(prefixes);
	}
	
	protected Map<String, Set<AfwPrincipal.Privilege>> getAllKeyPrefixedPermissions(String... prefixes) {
		UserInfoDto userInfoDto = AfwExecutionContext.getUserInfoDto();
		return userInfoDto.getPrincipal().getAllKeyPrefixedPermissions(prefixes);
	}
}
