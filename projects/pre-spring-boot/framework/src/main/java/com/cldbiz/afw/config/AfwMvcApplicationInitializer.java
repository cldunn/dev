package com.cldbiz.afw.config;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.cldbiz.afw.filter.InitializeFilter;

public class AfwMvcApplicationInitializer implements WebApplicationInitializer, ServletContainerInitializer {
	private static final Logger log = LoggerFactory.getLogger(AfwMvcApplicationInitializer.class);
	
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		log.debug("Inside onStartup");
		this.onStartup(ctx);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.debug("Inside onStartup");
		
		setAppContext(servletContext);
		setWebContext(servletContext);
		
		addServletFilters(servletContext);
		
	}
	protected void setAppContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AfwConfig.class);
		
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}

	protected void setWebContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(AfwWebConfig.class);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
		dispatcher.addMapping("/*");
		dispatcher.setLoadOnStartup(1);
	}
	
	protected void addServletFilters(ServletContext servletContext) {
		FilterRegistration.Dynamic initializeFilter = servletContext.addFilter("InitializeFilter",  new InitializeFilter());
		initializeFilter.addMappingForUrlPatterns(null, true, new String[] {"/rest/"});
	}
}
