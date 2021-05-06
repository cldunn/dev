package com.cldbiz.boot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="PRODUCT")
public class Product extends EntityDomain {
	private static final long serialVersionUID = 2364820354355754316L;

	@Size(max=8)
    @Column(name="CODE", length=8, nullable=false, unique=true, updatable=false)
	private String code;
	
	@Size(max=60)
    @Column(name="KEY_NAME", length=60, nullable=false, unique=true)
	private String keyName;

	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LANDING_PAGE", nullable=false)
	private String landingPage;
	
	@Column(name="ID_RANGE_START", nullable=false)
	private Long idRangeStart;
	
	@Column(name="ID_RANGE_END", nullable=false)
	private Long idRanageEnd;

	@Column(name="SORT_ORDER", nullable=false)
	private Long sortOrder;
	
	@Column(name="STATUS", nullable=false)
	private Boolean status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	public Long getIdRangeStart() {
		return idRangeStart;
	}

	public void setIdRangeStart(Long idRangeStart) {
		this.idRangeStart = idRangeStart;
	}

	public Long getIdRanageEnd() {
		return idRanageEnd;
	}

	public void setIdRanageEnd(Long idRanageEnd) {
		this.idRanageEnd = idRanageEnd;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
