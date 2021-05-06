package com.cldbiz.angularSpring.dto;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.cldbiz.angularSpring.domain.AppCode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@RedisHash("AppCodeDto")
@EqualsAndHashCode(callSuper=true)
public @Data class AppCodeDto extends AbstractDto implements Serializable {
    
	private static final long serialVersionUID = 1L;
 
	@Indexed
	private String type;
	
	@Indexed
	private String code;
	
	private String valueText;
	
	private String description;
	
	private Boolean defaultFlag;
	
	private Boolean userDefined;
	
	private Boolean isActive;

    public AppCodeDto() {
    	super();
    }
    
    public AppCodeDto(AppCode appCode) {
    	super(appCode);
    	
    	this.setType(appCode.getType());
    	this.setCode(appCode.getCode());
    	this.setValueText(appCode.getValueText());
    	this.setDescription(appCode.getDescription());
    	this.setDefaultFlag(appCode.getDefaultFlag());
    	this.setUserDefined(appCode.getUserDefined());
    	this.setIsActive(appCode.getIsActive());
    }

}
