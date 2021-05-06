package com.cldbiz.afw.config;

import com.cldbiz.afw.dto.UserInfoDto;

public class AfwContext {
	private String dsKey;
	private UserInfoDto userInfoDto;
	

	public String getDsKey() {
		return dsKey;
	}

	public void setDsKey(String dsKey) {
		this.dsKey = dsKey;
	}

	public UserInfoDto getUserInfoDto() {
		return userInfoDto;
	}

	public void setUserInfoDto(UserInfoDto userInfoDto) {
		this.userInfoDto = userInfoDto;
	}

}
