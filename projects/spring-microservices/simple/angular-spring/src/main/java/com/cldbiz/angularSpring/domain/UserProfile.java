package com.cldbiz.angularSpring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cldbiz.angularSpring.dto.UserProfileDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "USER_PROFILE")
@EqualsAndHashCode(callSuper=true)
public @Data class UserProfile extends AbstractDomain {

    @Column
    private String firstName;
    
    @Column
    private String lastName;
    
    @Column
    private String email;
    
    @Column
    private String dsKey;
    
    public UserProfile() {
    	super();
    }
    
    public UserProfile(UserProfileDto userProfileDto) {
    	super(userProfileDto);
    	
    	this.setFirstName(userProfileDto.getFirstName());
    	this.setLastName(userProfileDto.getLastName());
    	this.setEmail(userProfileDto.getEmail());
    	this.setDsKey(userProfileDto.getDsKey());
    }
}

