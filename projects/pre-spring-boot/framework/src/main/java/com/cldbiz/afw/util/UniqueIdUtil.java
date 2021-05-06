package com.cldbiz.afw.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cldbiz.afw.config.AfwExecutionContext;
import com.cldbiz.afw.dto.UniqueIdDto;
import com.cldbiz.afw.service.UniqueIdService;

@Component
public class UniqueIdUtil {
	private static Map dsUniqueIdMap = Collections.synchronizedMap(new HashMap<String, Map<String, UniqueIdDto>>());
	private static final Long DEFAULT_BLOCK_SIZE = 100L;
	private static Object lock = new Object();

	@Autowired
	private UniqueIdService uniqueIdService;

	@SuppressWarnings("unchecked")
	public Long getNextId(String idName) {
		synchronized (lock) {
			Map<String, UniqueIdDto> idMap = (Map<String, UniqueIdDto>) dsUniqueIdMap.get(AfwExecutionContext.getDsKey());
			if (idMap == null) {
				idMap = new HashMap<String, UniqueIdDto>();
				dsUniqueIdMap.put(AfwExecutionContext.getDsKey(), idMap);
			}
			
			UniqueIdDto uniqueIdDto = idMap.get(idName);
			if (uniqueIdDto == null) {
				uniqueIdDto = uniqueIdService.getUniqueIdBlock(idName, DEFAULT_BLOCK_SIZE);
				idMap.put(idName, uniqueIdDto);
			}
			else {
				if (uniqueIdDto.getNextId() > uniqueIdDto.getLastId()) {
					uniqueIdDto = uniqueIdService.getUniqueIdBlock(idName, DEFAULT_BLOCK_SIZE);
					idMap.put(idName, uniqueIdDto);
				}
			}
			
			Long nextId = uniqueIdDto.getNextId();
			uniqueIdDto.setNextId(nextId + 1);
			return nextId;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void resetBlock(String idName, Long blkSz) {
		synchronized (lock) {
			Map<String, UniqueIdDto> idMap = (Map<String, UniqueIdDto>) dsUniqueIdMap.get(AfwExecutionContext.getDsKey());
			if (idMap == null) {
				idMap = new HashMap<String, UniqueIdDto>();
				dsUniqueIdMap.put(AfwExecutionContext.getDsKey(), idMap);
			}

			UniqueIdDto uniqueIdDto = uniqueIdService.getUniqueIdBlock(idName, blkSz);
			idMap.put(idName, uniqueIdDto);
		}
	}
}
