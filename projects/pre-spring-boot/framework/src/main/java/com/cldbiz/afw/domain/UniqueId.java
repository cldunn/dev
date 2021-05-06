package com.cldbiz.afw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cldbiz.afw.dto.UniqueIdDto;

@Entity
@Table(name="UNIQUE_ID")
public class UniqueId {
	@Id 
    @Column(name="ID_NAME", nullable=false)
	private String idName;
	
	@Column(name="NEXT_ID", nullable=false)
	private Long nextId;

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
	
	public static UniqueId asUniqueId(UniqueIdDto uniqueIdDto) {
		if (uniqueIdDto == null) return null;
		
		UniqueId uniqueId = new UniqueId();
		
		uniqueId.setIdName(uniqueIdDto.getIdName());
		uniqueId.setNextId(uniqueId.getNextId());

		return uniqueId;
	}
}
