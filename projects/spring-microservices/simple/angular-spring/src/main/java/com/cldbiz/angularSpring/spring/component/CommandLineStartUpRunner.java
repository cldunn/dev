package com.cldbiz.angularSpring.spring.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cldbiz.angularSpring.dto.AppCodeDto;
import com.cldbiz.angularSpring.service.AppCodeService;
import com.cldbiz.angularSpring.service.cache.AppCodeCacheService;

@Component
public class CommandLineStartUpRunner implements CommandLineRunner {

	@Autowired
	AppCodeService appCodeService;

	@Autowired
	AppCodeCacheService appCodeCacheService;
	
	@Override
	public void run(String... args) throws Exception {
		List<AppCodeDto> appCodeDtos = appCodeService.findAll();
		
		appCodeCacheService.deleteAll();
		appCodeCacheService.saveAll(appCodeDtos);
		
	}

}
