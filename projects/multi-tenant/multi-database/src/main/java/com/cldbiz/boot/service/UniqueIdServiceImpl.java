package com.cldbiz.boot.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cldbiz.boot.dao.UniqueIdDao;
import com.cldbiz.boot.domain.UniqueId;
import com.cldbiz.boot.dto.UniqueIdDto;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UniqueIdServiceImpl implements UniqueIdService {
	@Resource
	private UniqueIdDao uniqueDao;
	
	@Override
	public UniqueIdDto getUniqueIdBlock(String idName, Long blkSz) {
		UniqueId uniqueId = uniqueDao.findUniqueId(idName);
		UniqueIdDto uniqueIdDto = new UniqueIdDto(uniqueId, blkSz);
		
		// update domain/database
		uniqueId.setNextId(uniqueIdDto.getLastId() + 1);
		
		return uniqueIdDto;
	}

}
