package com.cldbiz.angularSpring.dao.cache;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cldbiz.angularSpring.dto.AppCodeDto;

@Repository
public interface AppCodeCacheRepository extends CrudRepository<AppCodeDto, Long> {
	List<AppCodeDto>findByType(String type);
	AppCodeDto findByTypeAndCode(String type, String code);
}
