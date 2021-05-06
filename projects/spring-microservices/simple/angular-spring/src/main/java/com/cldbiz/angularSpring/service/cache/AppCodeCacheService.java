package com.cldbiz.angularSpring.service.cache;

import java.util.List;

import com.cldbiz.angularSpring.dto.AppCodeDto;

public interface AppCodeCacheService {
	
	public AppCodeDto findById(Long id);

	public List<AppCodeDto> findByType(String type);
	
	public AppCodeDto findByTypeAndCode(String type, String code);
	
	public void saveAll(List<AppCodeDto> appCodeDtos);

	public void deleteAll();
}
