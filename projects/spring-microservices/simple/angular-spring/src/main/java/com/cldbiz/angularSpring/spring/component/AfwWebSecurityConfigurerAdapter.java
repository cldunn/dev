package com.cldbiz.angularSpring.spring.component;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class AfwWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	  httpSecurity
        .httpBasic()
      .and()
        .authorizeRequests()
          .antMatchers("/", "/index.html", "/favicon.ico", "/*.js", "/*.js.map", "/rest/ui/**").permitAll()
          .anyRequest().authenticated()
      .and().csrf()
          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
}

