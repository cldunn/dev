package com.cldbiz.boot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.constraints.Size;

import com.cldbiz.boot.domain.UserInfo;
import com.cldbiz.boot.security.BootPrincipal;
import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Add Role relationship
public class UserProfileDto extends BaseDto implements Serializable {
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
	private Locale locale = Locale.getDefault();
		
	@Size(max=30)
    private String login;

	@JsonIgnore
	private BootPrincipal principal;

	@JsonIgnore
	private UserAuthenticationDto userAuthenticationDto;
	
	private List<RoleDto> roleDtos = new ArrayList<RoleDto>();
	
	public UserProfileDto() {
		
	}

	public UserProfileDto(UserInfo userInfo) {
		UserProfileDto.asUserProfileDto(this, userInfo);
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public BootPrincipal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BootPrincipal principal) {
		this.principal = principal;
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

	public static UserProfileDto asUserProfileDto(UserProfileDto userProfileDto , UserInfo userInfo) {
		if (userProfileDto == null) {
			userProfileDto = new UserProfileDto();
		}

		if (userInfo == null) {
			userProfileDto = null;
		}
		else {
			userProfileDto.setId(userInfo.getId());
			userProfileDto.setFirstName(userInfo.getFirstName());
			userProfileDto.setLastName(userInfo.getLastName());
			userProfileDto.setAddressLine1(userInfo.getAddressLine1());
			userProfileDto.setAddressLine2(userInfo.getAddressLine2());
			userProfileDto.setCity(userInfo.getCity());
			userProfileDto.setState(userInfo.getState());
			userProfileDto.setProvince(userInfo.getProvince());
			userProfileDto.setZip(userInfo.getZip());
			userProfileDto.setCountry(userInfo.getCountry());
			userProfileDto.setPrimaryPhone(userInfo.getPrimaryPhone());
			userProfileDto.setSecondaryPhone(userInfo.getSecondaryPhone());
			userProfileDto.setFax(userInfo.getFax());
			userProfileDto.setEmailAddress(userInfo.getEmailAddress());
			userProfileDto.setDateFormat(userInfo.getDateFormat());
			userProfileDto.setTimeFormat(userInfo.getTimeFormat());
			userProfileDto.setTimeZone(userInfo.getTimeZone());
			if (userInfo.getLocaleCountry() != null) {
				if (userInfo.getLocaleVariant() != null) {
					userProfileDto.locale = new Locale(userInfo.getLocaleLanguage(), userInfo.getLocaleCountry(), userInfo.getLocaleVariant());
				}
				else {
					userProfileDto.locale = new Locale(userInfo.getLocaleLanguage(), userInfo.getLocaleCountry());
				}
			}
			else {
				userProfileDto.locale = new Locale(userInfo.getLocaleLanguage());
			}
			userProfileDto.setLogin(userInfo.getLogin());
		}
		
		return userProfileDto;
	}

	public String getName() {
		return this.lastName + ", " + this.firstName;
	}
}
