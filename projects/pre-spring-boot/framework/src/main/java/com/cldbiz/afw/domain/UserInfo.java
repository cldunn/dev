package com.cldbiz.afw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.cldbiz.afw.dto.UserInfoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER_INFO",
	   uniqueConstraints= {  @UniqueConstraint(name = "UK_USER_AUTHENTICATION_ID", columnNames={"USER_AUTHENTICATION_ID"}), 
 		  				     @UniqueConstraint(name = "UK_EMAIL_ADDRESS", columnNames={"EMAIL_ADDRESS"})}
)

public class UserInfo extends EntityDomain {
	private static final long serialVersionUID = -4000227300921411227L;

	@Size(max=40)
    @Column(name="FIRST_NAME", nullable=true)
	private String firstName;

	@Size(max=40)
    @Column(name="LAST_NAME", nullable=true)
	private String lastName;
	
	@Size(max=40)
    @Column(name="ADDRESS_LINE1", nullable=true)
	private String addressLine1;
	
	@Size(max=40)
    @Column(name="ADDRESS_LINE2", nullable=true)
	private String addressLine2;
	
	@Size(max=40)
    @Column(name="CITY", nullable=true)
	private String city;

	@Size(max=2)
    @Column(name="STATE", nullable=true)
	private String state;
	
	@Size(max=40)
    @Column(name="PROVINCE", nullable=true)
	private String province;
	
	@Size(max=10)
    @Column(name="ZIP", nullable=true)
	private String zip;

	@Size(max=40)
    @Column(name="COUNTRY", nullable=true)
	private String country;

	@Size(max=10)
    @Column(name="PRIMARY_PHONE", nullable=true)
	private String primaryPhone;

	@Size(max=10)
    @Column(name="SECONDARY_PHONE", nullable=true)
	private String secondaryPhone;
	
	@Size(max=10)
    @Column(name="FAX", nullable=true)
	private String fax;

	@Size(max=80)
    @Column(name="EMAIL_ADDRESS", nullable=false)
	private String emailAddress;

	@Size(max=10)
    @Column(name="DATE_FORMAT", nullable=true)
	private String dateFormat;

	@Size(max=12)
    @Column(name="TIME_FORMAT", nullable=true)
	private String timeFormat;

	@Size(max=80)
    @Column(name="TIME_ZONE", nullable=true)
	private String timeZone;
	
	@Size(max=10)
    @Column(name="LOCALE_LANGUAGE", nullable=false)
	private String localeLanguage = "en";

	@Size(max=10)
    @Column(name="LOCALE_COUNTRY", nullable=true)
	private String localeCountry ="us";

	@Size(max=10)
    @Column(name="LOCALE_VARIANT", nullable=true)
	private String localeVariant;
	
	// @ForeignKey(name = "FK_USER_AUTHENTICATION_ID")
	@OneToOne(cascade={CascadeType.ALL}, optional=false, orphanRemoval=true)
	@JoinColumn(name="USER_AUTHENTICATION_ID", referencedColumnName="ID", nullable=false, foreignKey = @ForeignKey(name = "FK_USER_AUTHENTICATION_ID"))
	private UserAuthentication userAuthentication;

	@JsonIgnore
	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(name="USER_INFO_ROLE", 
			   joinColumns = @JoinColumn(name = "USER_INFO_ID", referencedColumnName = "ID"), 
			   inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	List<Role> roles = new ArrayList<Role>();
	
	public UserInfo() {
		
	}
	
	public UserInfo(UserInfoDto userInfoDto) {
		UserInfo.asUserInfo(this, userInfoDto);
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

	public String getLocaleLanguage() {
		return localeLanguage;
	}

	public void setLocaleLanguage(String localeLanguage) {
		this.localeLanguage = localeLanguage;
	}

	public String getLocaleCountry() {
		return localeCountry;
	}

	public void setLocaleCountry(String localeCountry) {
		this.localeCountry = localeCountry;
	}

	public String getLocaleVariant() {
		return localeVariant;
	}

	public void setLocaleVariant(String localeVariant) {
		this.localeVariant = localeVariant;
	}

	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}

	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public static UserInfo asUserInfo(UserInfo userInfo, UserInfoDto userInfoDto) {
		if (userInfo == null) {
			userInfo = new UserInfo();
		}

		if (userInfoDto == null) {
			userInfo = null;
		}
		else {
			userInfo.setId(userInfoDto.getId());
			userInfo.setFirstName(userInfoDto.getFirstName());
			userInfo.setLastName(userInfoDto.getLastName());
			userInfo.setAddressLine1(userInfoDto.getAddressLine1());
			userInfo.setAddressLine2(userInfoDto.getAddressLine2());
			userInfo.setCity(userInfoDto.getCity());
			userInfo.setState(userInfoDto.getState());
			userInfo.setProvince(userInfoDto.getProvince());
			userInfo.setZip(userInfoDto.getZip());
			userInfo.setCountry(userInfoDto.getCountry());
			userInfo.setPrimaryPhone(userInfoDto.getPrimaryPhone());
			userInfo.setSecondaryPhone(userInfoDto.getSecondaryPhone());
			userInfo.setFax(userInfoDto.getFax());
			userInfo.setEmailAddress(userInfoDto.getEmailAddress());
			userInfo.setDateFormat(userInfoDto.getDateFormat());
			userInfo.setTimeFormat(userInfoDto.getTimeFormat());
			userInfo.setTimeZone(userInfoDto.getTimeZone());
			if (userInfoDto.getLocale() != null) {
				userInfo.setLocaleLanguage(userInfoDto.getLocale().getLanguage());
				userInfo.setLocaleCountry(userInfoDto.getLocale().getCountry());
				userInfo.setLocaleVariant(userInfoDto.getLocale().getVariant());
			}
		}
		
		return userInfo;
	}
	
	/*
	public static List<UserInfo> asUserInfos(List<UserInfoDto> userInfoDtos) {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		for(UserInfoDto userInfoDto: userInfoDtos) {
			userInfos.add(new UserInfo(userInfoDto));
		}
		
		return userInfos;
	}
	
	public UserInfoDto updateUserInfoDto(UserInfoDto userInfoDto) {
		userInfoDto.setFirstName(this.getFirstName());
		userInfoDto.setLastName(this.getLastName());
		userInfoDto.setAddressLine1(this.getAddressLine1());
		userInfoDto.setAddressLine2(this.getAddressLine2());
		userInfoDto.setCity(this.getCity());
		userInfoDto.setState(this.getState());
		userInfoDto.setProvince(this.getProvince());
		userInfoDto.setZip(this.getZip());
		userInfoDto.setCountry(this.getCountry());
		userInfoDto.setPrimaryPhone(this.getPrimaryPhone());
		userInfoDto.setSecondaryPhone(this.getSecondaryPhone());
		userInfoDto.setFax(this.getFax());
		userInfoDto.setEmailAddress(this.getEmailAddress());
		userInfoDto.setDateFormat(this.getDateFormat());
		userInfoDto.setTimeFormat(this.getTimeFormat());
		userInfoDto.setTimeZone(this.getTimeZone());
		if (this.getLocaleCountry() != null) {
			if (this.getLocaleVariant() != null) {
				userInfoDto.setLocale(new Locale(this.getLocaleLanguage(), this.getLocaleCountry(), this.getLocaleVariant()));
			}
			else {
				userInfoDto.setLocale(new Locale(this.getLocaleLanguage(), this.getLocaleCountry()));
			}
		}
		else {
			userInfoDto.setLocale(new Locale(this.getLocaleLanguage()));
		}
		
		
		userInfoDto.setUserAuthentication(this.getUserAuthentication());
		
		return userInfoDto;
	}
	*/
}
