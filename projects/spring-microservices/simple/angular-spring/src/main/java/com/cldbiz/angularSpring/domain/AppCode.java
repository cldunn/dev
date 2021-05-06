package com.cldbiz.angularSpring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cldbiz.angularSpring.dto.AppCodeDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "APP_CODE")
@EqualsAndHashCode(callSuper=true)
public @Data class AppCode extends AbstractDomain {

	@Column
	private String type;
	
	@Column
	private String code;
	
	@Column
	private String valueText;
	
	@Column
	private String description;
	
	@Column
	private Boolean defaultFlag;
	
	@Column
	private Boolean userDefined;
	
	@Column
	private Boolean isActive;
	
	public AppCode() {
    	super();
    }
	
    public AppCode(AppCodeDto appCodeDto) {
    	super(appCodeDto);
    	
    	this.setType(appCodeDto.getType());
    	this.setCode(appCodeDto.getCode());
    	this.setValueText(appCodeDto.getValueText());
    	this.setDescription(appCodeDto.getDescription());
    	this.setDefaultFlag(appCodeDto.getDefaultFlag());
    	this.setUserDefined(appCodeDto.getUserDefined());
    	this.setIsActive(appCodeDto.getIsActive());
    }

}
