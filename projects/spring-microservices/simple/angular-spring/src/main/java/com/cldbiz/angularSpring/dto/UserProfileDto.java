package com.cldbiz.angularSpring.dto;


import java.util.Locale;

import com.cldbiz.angularSpring.domain.UserProfile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
public @Data class UserProfileDto extends AbstractDto {

    private static final long serialVersionUID = 1L;

	private String firstName;
    
    private String lastName;
    
    private Locale locale;
    
    private String email;
    
    private String dsKey;

    public UserProfileDto() {
    	super();
    }
    
    public UserProfileDto(UserProfile userProfile) {
    	super(userProfile);
    	
    	this.setFirstName(userProfile.getFirstName());
    	this.setLastName(userProfile.getLastName());
    	this.setEmail(userProfile.getEmail());
    	this.setDsKey(userProfile.getDsKey());
    }
}
