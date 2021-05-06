package com.cldbiz.boot.dao;

import com.cldbiz.boot.domain.UniqueId;

public interface UniqueIdDao {
	public UniqueId findUniqueId(String idName);
}
