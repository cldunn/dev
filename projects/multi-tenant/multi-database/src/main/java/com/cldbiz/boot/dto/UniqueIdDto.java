package com.cldbiz.boot.dto;

import javax.validation.constraints.Size;

import com.cldbiz.boot.domain.UniqueId;

public class UniqueIdDto {
	@Size(max=50)
	private String idName;

	private Long nextId;
	
	private Long lastId;
	
	public UniqueIdDto() {}

	public UniqueIdDto(UniqueId uniqueId, Long blkSz) {
		this.setIdName(uniqueId.getIdName());
		this.setNextId(uniqueId.getNextId());
		this.setLastId(uniqueId.getNextId() + blkSz - 1);
	}

	public String getIdName() {
		return idName;
	}
	
	public void setIdName(String idName) {
		this.idName = idName;
	}
	
	public Long getNextId() {
		return nextId;
	}
	
	public void setNextId(Long nextId) {
		this.nextId = nextId;
	}

	public Long getLastId() {
		return lastId;
	}

	public void setLastId(Long lastId) {
		this.lastId = lastId;
	}
}
