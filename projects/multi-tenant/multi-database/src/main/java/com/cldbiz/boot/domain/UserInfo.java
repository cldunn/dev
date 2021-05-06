package com.cldbiz.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.cldbiz.boot.domain.Role;
import com.cldbiz.boot.common.BootConstants;
import com.cldbiz.boot.dto.UserAuthenticationDto;
import com.cldbiz.boot.dto.UserProfileDto;

@Entity
@Table(name="USER_INFO",
	   uniqueConstraints= {  @UniqueConstraint(name = "UK_EMAIL_ADDRESS", columnNames={"EMAIL_ADDRESS"}),
 		  				     @UniqueConstraint(name = "UK_LOGIN", columnNames={"LOGIN"})}
)

public class UserInfo extends EntityDomain {
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
	private String localeCountry ="US";

	@Size(max=10)
    @Column(name="LOCALE_VARIANT", nullable=true)
	private String localeVariant;
	
	@Size(max=30)
    @Column(name="LOGIN", nullable=false)
    private String login;
	
	@Column(name="LOGIN_ATTEMPTS")
	private Integer loginAttempts;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_LOGIN_DATE")
	private Date lastLoginDate;
	
	@Size(max=40)
    @Column(name="PASSWORD", nullable=false)
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="PASSWORD_MODIFIED_DATE")
	private Date passwordModifiedDate;
	
	@Column(name="PASSWORD_EXPIRE_DATE")
	@Temporal(TemporalType.DATE)
	private Date passwordExpireDate;
		
	@Size(max=20)
    @Column(name="STATUS", nullable=false)
	private String status = BootConstants.USER_STATUS_INACTIVE;

	@Column(name="IS_LOCKED")
	private Boolean isLocked;
	
	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(name="USER_INFO_ROLE", 
			   joinColumns = @JoinColumn(name = "USER_INFO_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_INFO_ID")), 
			   inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ROLE_ID")))
	List<Role> roles = new ArrayList<Role>();
	
	public UserInfo() {
		
	}
	
	public UserInfo(UserProfileDto userProfileDto) {
		UserInfo.asUserInfo(this, userProfileDto);
		UserInfo.asUserInfo(this, userProfileDto.getUserAuthenticationDto());
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
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public static UserInfo asUserInfo(UserInfo userInfo, UserProfileDto userProfileDto) {
		if (userInfo == null) {
			userInfo = new UserInfo();
		}

		if (userProfileDto == null) {
			userInfo = null;
		}
		else {
			userInfo.setId(userProfileDto.getId());
			userInfo.setFirstName(userProfileDto.getFirstName());
			userInfo.setLastName(userProfileDto.getLastName());
			userInfo.setAddressLine1(userProfileDto.getAddressLine1());
			userInfo.setAddressLine2(userProfileDto.getAddressLine2());
			userInfo.setCity(userProfileDto.getCity());
			userInfo.setState(userProfileDto.getState());
			userInfo.setProvince(userProfileDto.getProvince());
			userInfo.setZip(userProfileDto.getZip());
			userInfo.setCountry(userProfileDto.getCountry());
			userInfo.setPrimaryPhone(userProfileDto.getPrimaryPhone());
			userInfo.setSecondaryPhone(userProfileDto.getSecondaryPhone());
			userInfo.setFax(userProfileDto.getFax());
			userInfo.setEmailAddress(userProfileDto.getEmailAddress());
			userInfo.setDateFormat(userProfileDto.getDateFormat());
			userInfo.setTimeFormat(userProfileDto.getTimeFormat());
			userInfo.setTimeZone(userProfileDto.getTimeZone());
			if (userProfileDto.getLocale() != null) {
				userInfo.setLocaleLanguage(userProfileDto.getLocale().getLanguage());
				userInfo.setLocaleCountry(userProfileDto.getLocale().getCountry());
				userInfo.setLocaleVariant(userProfileDto.getLocale().getVariant());
			}
			userInfo.setLogin(userProfileDto.getLogin());
		}
		
		return userInfo;
	}

	public static UserInfo asUserInfo(UserInfo userInfo, UserAuthenticationDto userAuthenticationDto) {
		if (userInfo == null) {
			userInfo = new UserInfo();
		}

		if (userAuthenticationDto == null) {
			userInfo = null;
		}
		else {
			userInfo.setId(userAuthenticationDto.getId());
			userInfo.setLoginAttempts(userAuthenticationDto.getLoginAttempts());
			userInfo.setLastLoginDate(userAuthenticationDto.getLastLoginDate());
			userInfo.setPassword(userAuthenticationDto.getPassword());
			userInfo.setPasswordModifiedDate(userAuthenticationDto.getPasswordModifiedDate());
			userInfo.setPasswordExpireDate(userAuthenticationDto.getPasswordExpireDate());	
			userInfo.setStatus(userAuthenticationDto.getStatus());
			userInfo.setIsLocked(userAuthenticationDto.getIsLocked());
		}
		
		return userInfo;
	}

}
