package com.cldbiz.boot.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.cldbiz.boot.domain.UserInfo;

public class UserAuthenticationDto extends BaseDto {
	private static final long serialVersionUID = 8812466091226698206L;

	private Integer loginAttempts;
	
	private Date lastLoginDate;
	
	@Size(max=40)
	private String password;

	private Date passwordModifiedDate;
	
	private Date passwordExpireDate;
		
	@Size(max=20)
	private String status;

	private Boolean isLocked;
	
	public UserAuthenticationDto() {
	
	}
	
	public UserAuthenticationDto(UserInfo userInfo) {
		UserAuthenticationDto.asUserAuthenticationDto(this, userInfo);
	}

	public Integer getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Integer loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPasswordModifiedDate() {
		return passwordModifiedDate;
	}

	public void setPasswordModifiedDate(Date passwordModifiedDate) {
		this.passwordModifiedDate = passwordModifiedDate;
	}

	public Date getPasswordExpireDate() {
		return passwordExpireDate;
	}

	public void setPasswordExpireDate(Date passwordExpireDate) {
		this.passwordExpireDate = passwordExpireDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public static UserAuthenticationDto asUserAuthenticationDto(UserAuthenticationDto userAuthenticationDto, UserInfo userInfo) {
		if (userAuthenticationDto == null) {
			userAuthenticationDto = new UserAuthenticationDto();
		}

		if (userInfo == null) {
			userAuthenticationDto = null;
		}
		else {
			userAuthenticationDto.setId(userInfo.getId());
			userAuthenticationDto.setLoginAttempts(userInfo.getLoginAttempts());
			userAuthenticationDto.setLastLoginDate(userInfo.getLastLoginDate());
			userAuthenticationDto.setPassword(userInfo.getPassword());
			userAuthenticationDto.setPasswordModifiedDate(userInfo.getPasswordModifiedDate());
			userAuthenticationDto.setPasswordExpireDate(userInfo.getPasswordExpireDate());	
			userAuthenticationDto.setStatus(userInfo.getStatus());
			userAuthenticationDto.setIsLocked(userInfo.getIsLocked());
		}
		
		return userAuthenticationDto;
	}

}
