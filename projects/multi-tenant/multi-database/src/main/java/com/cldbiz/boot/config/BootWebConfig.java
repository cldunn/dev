package com.cldbiz.boot.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.cldbiz.boot.spring.BootMessageSource;

@Configuration
public class BootWebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(BootWebConfig.class);
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("redirect:/index.html");
	    // registry.addViewController("/error").setViewName("redirect:/error.html");
	    // registry.addViewController("/error.html").setViewName("redirect:/myError.html");
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );

	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
		configurer.defaultContentType(MediaType.TEXT_HTML);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		String[] beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, Object.class);
		for (String beanName : beanNames) {
			log.debug("Created bean named: " + beanName);
		}
	}

	
	@Bean
	// @Profile("dev")
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/api/**").allowedOrigins("http://localhost:4200");
	        }
	    };
	}
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
 
        // Define all possible view resolvers
        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
 
        // TODO: create and add generic excel, pdf  view resolvers 
        // viewResolvers.add(htmlViewResolver());
        viewResolvers.add(new JsonViewResolver());
         
        resolver.setViewResolvers(viewResolvers);
        
        return resolver;
	}
	
	
	@Bean
    public InternalResourceViewResolver defaultViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        
        return resolver;
    }
	
	
	public ViewResolver htmlViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
	
	public class JsonViewResolver implements ViewResolver{
	    public View resolveViewName(String viewName, Locale locale) throws Exception {
	    	MappingJackson2JsonView view = new MappingJackson2JsonView();
	        view.setPrettyPrint(true);       
	        return view;
	    }
	}
	
	@Bean
	public BootMessageSource messageSource() {
		BootMessageSource afwMessageSource = new BootMessageSource();
		afwMessageSource.setBasename("classpath:BootMessages");
		afwMessageSource.setDefaultEncoding("UTF-8");
		
		return afwMessageSource;
	}
	
}
