package com.cldbiz.afw.service;

import com.cldbiz.afw.dto.UniqueIdDto;

public interface UniqueIdService {
	public UniqueIdDto getUniqueIdBlock(String idName, Long blkSz);
}
