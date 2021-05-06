package com.cldbiz.afw.util;

import javax.servlet.http.HttpServletRequest;

import com.cldbiz.afw.common.AfwConstants;
import com.cldbiz.afw.dto.UserInfoDto;

public abstract class SessionUtil {

	public static UserInfoDto getUserInfo(HttpServletRequest request) {
		return (UserInfoDto) request.getSession().getAttribute(AfwConstants.USER_INFO);
	}
	
	public static void setUserInfo(HttpServletRequest request, UserInfoDto userInfo) {
		request.getSession().setAttribute(AfwConstants.USER_INFO, userInfo);
	}

	public static String getCsrfToken(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(AfwConstants.CSRF_TOKEN);
	}
	
	public static void setCsrfToken(HttpServletRequest request, String csrfToken) {
		request.getSession().setAttribute(AfwConstants.CSRF_TOKEN, csrfToken);
	}
}
