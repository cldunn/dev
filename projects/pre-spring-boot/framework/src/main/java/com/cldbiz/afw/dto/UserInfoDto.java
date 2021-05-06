package com.cldbiz.afw.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.constraints.Size;

import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.domain.UserInfo;
import com.cldbiz.afw.security.AfwPrincipal;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfoDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 8357842956112614341L;
	
	@Size(max=40)
	private String firstName;
	
	@Size(max=40)
	private String lastName;
	
	@Size(max=40)
	private String addressLine1;
	
	@Size(max=40)
	private String addressLine2;
	
	@Size(max=40)
	private String city;
	
	@Size(max=2)
	private String state;
	
	@Size(max=40)
	private String province;
	
	@Size(max=10)
	private String zip;
	
	@Size(max=40)
	private String country;
	
	@Size(max=10)
	private String primaryPhone;
	
	@Size(max=10)
	private String secondaryPhone;
	
	@Size(max=10)
	private String fax;
	
	@Size(max=80)
	private String emailAddress;
	
	@Size(max=10)
	private String dateFormat;
	
	@Size(max=12)
	private String timeFormat;
	
	@Size(max=80)
	private String timeZone;
	
	@Size(max=10)
	private Locale locale = Locale.getDefault();;
	
	private UserAuthenticationDto userAuthenticationDto;

	private List<RoleDto> roleDtos = new ArrayList<RoleDto>();
	
	@JsonIgnore
	private RoleDto roleCriteria;
	
	@JsonIgnore
	private AfwPrincipal principal;

	public UserInfoDto() {
		
	}

	public UserInfoDto(UserInfo userInfo) {
		UserInfoDto.asUserInfoDto(this, userInfo);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Locale getLocale() {
		return this.locale;
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public UserAuthenticationDto getUserAuthenticationDto() {
		return userAuthenticationDto;
	}

	public void setUserAuthenticationDto(UserAuthenticationDto userAuthenticationDto) {
		this.userAuthenticationDto = userAuthenticationDto;
	}

	public List<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public void setRoleDtos(List<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
	}

	public RoleDto getRoleCriteria() {
		return roleCriteria;
	}

	public void setRoleCriteria(RoleDto roleCriteria) {
		this.roleCriteria = roleCriteria;
	}

	public AfwPrincipal getPrincipal() {
		return principal;
	}

	public void setPrincipal(AfwPrincipal principal) {
		this.principal = principal;
	}

	public String getName() {
		return this.lastName + ", " + this.firstName;
	}

	public static UserInfoDto asUserInfoDto(UserInfoDto userInfoDto , UserInfo userInfo) {
		if (userInfoDto == null) {
			userInfoDto = new UserInfoDto();
		}

		if (userInfo == null) {
			userInfoDto = null;
		}
		else {
			userInfoDto.setId(userInfo.getId());
			userInfoDto.setFirstName(userInfo.getFirstName());
			userInfoDto.setLastName(userInfo.getLastName());
			userInfoDto.setAddressLine1(userInfo.getAddressLine1());
			userInfoDto.setAddressLine2(userInfo.getAddressLine2());
			userInfoDto.setCity(userInfo.getCity());
			userInfoDto.setState(userInfo.getState());
			userInfoDto.setProvince(userInfo.getProvince());
			userInfoDto.setZip(userInfo.getZip());
			userInfoDto.setCountry(userInfo.getCountry());
			userInfoDto.setPrimaryPhone(userInfo.getPrimaryPhone());
			userInfoDto.setSecondaryPhone(userInfo.getSecondaryPhone());
			userInfoDto.setFax(userInfo.getFax());
			userInfoDto.setEmailAddress(userInfo.getEmailAddress());
			userInfoDto.setDateFormat(userInfo.getDateFormat());
			userInfoDto.setTimeFormat(userInfo.getTimeFormat());
			userInfoDto.setTimeZone(userInfo.getTimeZone());
			if (userInfo.getLocaleCountry() != null) {
				if (userInfo.getLocaleVariant() != null) {
					userInfoDto.locale = new Locale(userInfo.getLocaleLanguage(), userInfo.getLocaleCountry(), userInfo.getLocaleVariant());
				}
				else {
					userInfoDto.locale = new Locale(userInfo.getLocaleLanguage(), userInfo.getLocaleCountry());
				}
			}
			else {
				userInfoDto.locale = new Locale(userInfo.getLocaleLanguage());
			}
		}
		
		return userInfoDto;
	}
	
	/*
	public static List<UserInfoDto> asUserInfoDtos(List<UserInfo> userInfos) {
		List<UserInfoDto> userInfoDtos = new ArrayList<UserInfoDto>();
		for(UserInfo userInfo: userInfos) {
			UserInfoDto userInfoDto = new UserInfoDto(userInfo);
			userInfoDto.setRoles(userInfo.getRoles());
			userInfoDtos.add(userInfoDto);
		}
		
		return userInfoDtos;
	}

	public UserInfo updateUserInfo(UserInfo userInfo) {
		userInfo.setFirstName(this.getFirstName());
		userInfo.setLastName(this.getLastName());
		userInfo.setAddressLine1(this.getAddressLine1());
		userInfo.setAddressLine2(this.getAddressLine2());
		userInfo.setCity(this.getCity());
		userInfo.setState(this.getState());
		userInfo.setProvince(this.getProvince());
		userInfo.setZip(this.getZip());
		userInfo.setCountry(this.getCountry());
		userInfo.setPrimaryPhone(this.getPrimaryPhone());
		userInfo.setSecondaryPhone(this.getSecondaryPhone());
		userInfo.setFax(this.getFax());
		userInfo.setEmailAddress(this.getEmailAddress());
		userInfo.setDateFormat(this.getDateFormat());
		userInfo.setTimeFormat(this.getTimeFormat());
		userInfo.setTimeZone(this.getTimeZone());
		if (this.getLocale() != null) {
			userInfo.setLocaleLanguage(this.getLocale().getLanguage());
			userInfo.setLocaleCountry(this.getLocale().getCountry());
			userInfo.setLocaleVariant(this.getLocale().getVariant());
		}
		
		userInfo.setUserAuthentication(this.getUserAuthentication());
		
		return userInfo;
	}
	*/
}
