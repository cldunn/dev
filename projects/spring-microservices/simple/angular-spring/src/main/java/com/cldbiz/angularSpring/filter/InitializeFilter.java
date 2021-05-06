package com.cldbiz.angularSpring.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cldbiz.angularSpring.common.AfwConstants;
import com.cldbiz.angularSpring.common.AfwExecContext;
import com.cldbiz.angularSpring.dto.UserProfileDto;
import com.cldbiz.angularSpring.util.SessionUtil;

// @Component
public class InitializeFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(InitializeFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("InitializeFilter init()");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		setResponseHeaders(request, response);
		setAfwExecutionContext(request, response);
		
		try {
			chain.doFilter(request, response);
		}
		finally {
			AfwExecContext.remove();
		}
	}

	@Override
	public void destroy() {
		log.info("InitializeFilter destroy()");
	}

	private void setResponseHeaders(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
	}

	private void setAfwExecutionContext(HttpServletRequest request, HttpServletResponse response) {
		UserProfileDto userProfileDto = SessionUtil.getUserProfile(request);
		if (userProfileDto != null) {
			AfwExecContext.setUserProfileDto(userProfileDto);
			AfwExecContext.setDsKey(userProfileDto.getDsKey());
			AfwExecContext.setLocale(userProfileDto.getLocale());
		}
		else {
			AfwExecContext.setDsKey(AfwConstants.TENANT_IDENTIFIER_DEFAULT);
			AfwExecContext.setLocale(request.getLocale());
		}
	}
}
