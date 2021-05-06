package com.cldbiz.angularSpring.util;

import javax.servlet.http.HttpServletRequest;

import com.cldbiz.angularSpring.common.AfwConstants;
import com.cldbiz.angularSpring.dto.UserProfileDto;


public abstract class SessionUtil {

	public static UserProfileDto getUserProfile(HttpServletRequest request) {
		return (UserProfileDto) request.getSession().getAttribute(AfwConstants.USER_PROFILE);
	}
	
	public static void setUserProfile(HttpServletRequest request, UserProfileDto userProfile) {
		request.getSession().setAttribute(AfwConstants.USER_PROFILE, userProfile);
	}

	public static String getCsrfToken(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(AfwConstants.CSRF_TOKEN);
	}
	
	public static void setCsrfToken(HttpServletRequest request, String csrfToken) {
		request.getSession().setAttribute(AfwConstants.CSRF_TOKEN, csrfToken);
	}
}
