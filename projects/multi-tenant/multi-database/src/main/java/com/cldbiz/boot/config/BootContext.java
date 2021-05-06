package com.cldbiz.boot.config;

import com.cldbiz.boot.dto.UserProfileDto;

//import com.cldbiz.boot.dto.UserInfoDto;

public class BootContext {
	private String dsKey;
	private UserProfileDto userProfileDto;
	

	public String getDsKey() {
		return dsKey;
	}

	public void setDsKey(String dsKey) {
		this.dsKey = dsKey;
	}
	
	public UserProfileDto getUserProfileDto() {
		return userProfileDto;
	}

	public void setUserProfileDto(UserProfileDto userProfileDto) {
		this.userProfileDto = userProfileDto;
	}
	
}
