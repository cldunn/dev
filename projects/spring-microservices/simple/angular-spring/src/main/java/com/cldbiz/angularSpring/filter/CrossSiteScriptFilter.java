package com.cldbiz.angularSpring.filter;

import java.io.IOException;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.cldbiz.angularSpring.common.JsonFailure;
import com.cldbiz.angularSpring.common.JsonMessage;
import com.cldbiz.angularSpring.spring.bean.AfwMessageSource;
import com.cldbiz.angularSpring.util.RequestWrapper;
import com.cldbiz.angularSpring.util.SecurityUtil;

@Component
public class CrossSiteScriptFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(CrossSiteScriptFilter.class);
	
	@Autowired
	private static AfwMessageSource messagSource;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("CrossSiteScriptFilter init()");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
			
		if ("multipart".equalsIgnoreCase(request.getContentType()) && "post".equalsIgnoreCase(request.getMethod())) {
			StandardMultipartHttpServletRequest multiPartRequest = new StandardMultipartHttpServletRequest(request);
			
			chain.doFilter(multiPartRequest, response);
		}
		else {
			RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) req);
			
			String body = requestWrapper.getBody();
			String sanitizedBody = SecurityUtil.sanitize(body);
			if (sanitizedBody != null && !sanitizedBody.equals(body)) {
				throw new JsonFailure(new JsonMessage(messagSource.getMessage("security.antisamy.errors")));
			}

			chain.doFilter(requestWrapper, response);
		}
		
		return;
	}

	@Override
	public void destroy() {
		log.info("CrossSiteScriptFilter destroy()");
	}
}
