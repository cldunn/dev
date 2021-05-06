package com.cldbiz.afw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cldbiz.afw.config.AfwExecutionContext;
import com.cldbiz.afw.dto.UserInfoDto;
import com.cldbiz.afw.util.SessionUtil;

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
		setHttpOnlyAndSecure(request, response);
		setAfwExecutionContext(request, response);
		
		try {
			chain.doFilter(request, response);
		}
		finally {
			AfwExecutionContext.remove();
		}
	}

	@Override
	public void destroy() {
		log.info("InitializeFilter destroy()");
	}

	private void setResponseHeaders(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
	}

	private void setHttpOnlyAndSecure(HttpServletRequest request, HttpServletResponse response) {
		if (response.isCommitted()) return;
		
		try {
			ServletContext servletContext = request.getServletContext();
			servletContext.getSessionCookieConfig().setHttpOnly(true);
			if (request.isSecure()) {
				servletContext.getSessionCookieConfig().setSecure(true);
			}
			else {
				servletContext.getSessionCookieConfig().setSecure(false);
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
			
			throw new RuntimeException(ex);
		}
	}

	private void setAfwExecutionContext(HttpServletRequest request, HttpServletResponse response) {
		UserInfoDto userInfoDto = SessionUtil.getUserInfo(request);
		AfwExecutionContext.setUserInfoDto(userInfoDto);
	}
}
