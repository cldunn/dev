package com.cldbiz.boot.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cldbiz.boot.config.BootWebConfig;
import com.cldbiz.boot.config.TstWebConfig;
import com.cldbiz.boot.filter.InitializeFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration(classes = BootWebConfig.class),
	@ContextConfiguration(classes = TstWebConfig.class)
	 
})
@Transactional
@Rollback(true)
public abstract class WebTest extends BaseTest {
	@Autowired
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	@Before
	public void webSetup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.addFilter(new InitializeFilter(),  "/*")
				.build();
	}
}
