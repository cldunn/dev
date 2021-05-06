package com.cldbiz.afw.filter;

import static org.junit.Assert.fail;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.cldbiz.afw.base.AfwWebTest;

public class InitializeFilterTest extends AfwWebTest {

	@Test
	public void testInitializeFilter() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setServletPath("/index.html");
		
		final InitializeFilter initializeFilter = new InitializeFilter();
		MockFilterChain filterChain = new MockFilterChain() {
			@Override
			public void doFilter(ServletRequest req, ServletResponse resp) {
				try {
					initializeFilter.doFilter(req, resp, new MockFilterChain());
				}
				catch (Exception ex) {
					fail();
				}
			}
		};
		
		initializeFilter.init(new MockFilterConfig());

		
		initializeFilter.doFilter(request, response, filterChain);
		
		initializeFilter.destroy();
		
		assert(response.getContentLength() == 0);
	}
}
