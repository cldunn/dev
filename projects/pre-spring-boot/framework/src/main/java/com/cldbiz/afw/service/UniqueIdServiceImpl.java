package com.cldbiz.afw.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cldbiz.afw.dao.UniqueIdDao;
import com.cldbiz.afw.domain.UniqueId;
import com.cldbiz.afw.dto.UniqueIdDto;
import com.cldbiz.afw.util.UniqueIdUtil;

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
