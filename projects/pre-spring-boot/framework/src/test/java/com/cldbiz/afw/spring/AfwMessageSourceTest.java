package com.cldbiz.afw.spring;

import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;



import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cldbiz.afw.base.AfwWebTest;

public class AfwMessageSourceTest extends AfwWebTest {
	private static Timestamp now;
	
	@Autowired
	@Qualifier("testAfwMessageSource")
	private AfwMessageSource messageSource;
	
	@BeforeClass
	public static void setUpBeforeTests() {
		now = new Timestamp(new Date().getTime());
	}
	
	@Test
	public void testAfwMessageSource() {
		assertNotNull(messageSource);
		
		String msg = messageSource.getMessage("application.msg");
		assert("Application Message".equals(msg));
		
		msg = messageSource.getMessage("parent.msg");
		assert("Parent Message".equals(msg));
		
		msg = messageSource.getMessage("override.msg");
		assert("Application Overriden Message".equals(msg));
		
		msg = messageSource.getMessage("arg.msg", "World");
		assert("Hello World".equals(msg));
		
		Map<String, String> prefixedMsgs = messageSource.getAllKeyPrefixedMessages("pre");
		assert(prefixedMsgs.size() == 3);
	}
}
