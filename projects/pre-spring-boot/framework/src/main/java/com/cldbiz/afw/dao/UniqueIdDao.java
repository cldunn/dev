package com.cldbiz.afw.dao;

import com.cldbiz.afw.domain.UniqueId;

public interface UniqueIdDao {
	public UniqueId findUniqueId(String idName);
}
