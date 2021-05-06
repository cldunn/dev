package com.cldbiz.afw.base;

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

import com.cldbiz.afw.config.AfwWebConfig;
import com.cldbiz.afw.config.TstWebConfig;
import com.cldbiz.afw.filter.InitializeFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration(classes = AfwWebConfig.class),
	@ContextConfiguration(classes = TstWebConfig.class)
	 
})
@Transactional
@Rollback(true)
public abstract class AfwWebTest extends AfwBaseTest {
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
