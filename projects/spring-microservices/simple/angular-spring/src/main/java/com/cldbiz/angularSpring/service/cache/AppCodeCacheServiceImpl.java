package com.cldbiz.angularSpring.service.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cldbiz.angularSpring.dao.cache.AppCodeCacheRepository;
import com.cldbiz.angularSpring.dto.AppCodeDto;

@Service
public class AppCodeCacheServiceImpl implements AppCodeCacheService {

	@Autowired
	AppCodeCacheRepository appCodeCacheRepo;
	
	@Override
	public AppCodeDto findById(Long id) {
		return appCodeCacheRepo.findById(id).get();
	}

	public List<AppCodeDto> findByType(String type) {
		return appCodeCacheRepo.findByType(type);
	}

	public AppCodeDto findByTypeAndCode(String type, String code) {
		return appCodeCacheRepo.findByTypeAndCode(type, code);
	}

	public void saveAll(List<AppCodeDto> appCodeDtos) {
		appCodeCacheRepo.saveAll(appCodeDtos);
	}
	
	public void deleteAll() {
		appCodeCacheRepo.deleteAll();
	}
}
