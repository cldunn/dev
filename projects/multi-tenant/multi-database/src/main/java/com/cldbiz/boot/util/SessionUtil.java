package com.cldbiz.boot.util;

import javax.servlet.http.HttpServletRequest;

import com.cldbiz.boot.common.BootConstants;
import com.cldbiz.boot.dto.UserProfileDto;

public abstract class SessionUtil {

	public static UserProfileDto getUserProfileDto(HttpServletRequest request) {
		return (UserProfileDto) request.getSession().getAttribute(BootConstants.USER_PROFILE);
	}
	
	public static void setUserProfileDto(HttpServletRequest request, UserProfileDto userProfileDto) {
		request.getSession().setAttribute(BootConstants.USER_PROFILE, userProfileDto);
	}

	public static String getCsrfToken(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(BootConstants.CSRF_TOKEN);
	}
	
	public static void setCsrfToken(HttpServletRequest request, String csrfToken) {
		request.getSession().setAttribute(BootConstants.CSRF_TOKEN, csrfToken);
	}
}
