package com.cldbiz.afw.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.cldbiz.afw.domain.UserAuthentication;

public class UserAuthenticationDto extends BaseDto {
	private static final long serialVersionUID = -7544907062108640471L;

	@Size(max=30)
    private String login;
	
	private Integer loginAttempts;
	
	private Date lastLoginDate;
	
	@Size(max=40)
	private String password;

	private Date passwordModifiedDate;
	
	private Date passwordExpireDate;
		
	@Size(max=20)
	private String status;

	private Boolean isLocked;
	
	private UserInfoDto userInfoDto;
	
	public UserAuthenticationDto() {
	
	}
	
	public UserAuthenticationDto(UserAuthentication userAuthentication) {
		UserAuthenticationDto.asUserAuthenticationDto(this, userAuthentication);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public UserInfoDto getUserInfoDto() {
		return userInfoDto;
	}

	public void setUserInfoDto(UserInfoDto userInfoDto) {
		this.userInfoDto = userInfoDto;
	}
	
	public static UserAuthenticationDto asUserAuthenticationDto(UserAuthenticationDto userAuthenticationDto, UserAuthentication userAuthentication) {
		if (userAuthenticationDto == null) {
			userAuthenticationDto = new UserAuthenticationDto();
		}

		if (userAuthentication == null) {
			userAuthenticationDto = null;
		}
		else {
			userAuthenticationDto.setId(userAuthentication.getId());
			userAuthenticationDto.setLogin(userAuthentication.getLogin());
			userAuthenticationDto.setLoginAttempts(userAuthentication.getLoginAttempts());
			userAuthenticationDto.setLastLoginDate(userAuthentication.getLastLoginDate());
			userAuthenticationDto.setPassword(userAuthentication.getPassword());
			userAuthenticationDto.setPasswordModifiedDate(userAuthentication.getPasswordModifiedDate());
			userAuthenticationDto.setPasswordExpireDate(userAuthentication.getPasswordExpireDate());	
			userAuthenticationDto.setStatus(userAuthentication.getStatus());
			userAuthenticationDto.setIsLocked(userAuthentication.getIsLocked());
		}
		
		return userAuthenticationDto;
	}
	
	/*
	public UserAuthentication updateUserAuthentication(UserAuthentication userAuthentication) {
		userAuthentication.setLogin(this.getLogin());
		userAuthentication.setLoginAttempts(this.getLoginAttempts());
		userAuthentication.setLastLoginDate(this.getLastLoginDate());
		userAuthentication.setPassword(this.getPassword());
		userAuthentication.setPasswordModifiedDate(this.getPasswordModifiedDate());
		userAuthentication.setPasswordExpireDate(this.getPasswordExpireDate());	
		userAuthentication.setStatus(this.getStatus());
		userAuthentication.setIsLocked(this.getIsLocked());

		userAuthentication.setUserInfo(this.getUserInfo());
		
		return userAuthentication;
	}
	
	public static List<UserAuthenticationDto> asUserAuthenticationDtos(List<UserAuthentication> userAuthentications) {
		List<UserAuthenticationDto> userAuthenticationDtos = new ArrayList<UserAuthenticationDto>();
		for(UserAuthentication userAuthentication: userAuthentications) {
			userAuthenticationDtos.add(new UserAuthenticationDto(userAuthentication));
		}
		
		return userAuthenticationDtos;
	}
	*/
}
