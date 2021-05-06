package com.cldbiz.boot.service;

import com.cldbiz.boot.dto.UniqueIdDto;

public interface UniqueIdService {
	public UniqueIdDto getUniqueIdBlock(String idName, Long blkSz);
}
