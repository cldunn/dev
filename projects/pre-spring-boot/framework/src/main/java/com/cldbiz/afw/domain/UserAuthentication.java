package com.cldbiz.afw.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.cldbiz.afw.common.AfwConstants;
import com.cldbiz.afw.dto.UserAuthenticationDto;

@Entity
@Table(name="USER_AUTHENTICATION",
	   uniqueConstraints= {@UniqueConstraint(name = "UK_LOGIN", columnNames={"LOGIN"})}
)
public class UserAuthentication extends EntityDomain {
	private static final long serialVersionUID = -8151818890980460327L;

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
	private String status = AfwConstants.USER_STATUS_INACTIVE;

	@Column(name="IS_LOCKED")
	private Boolean isLocked;
	
	@OneToOne(mappedBy="userAuthentication", cascade=CascadeType.ALL, optional=false, orphanRemoval=true)
	private UserInfo userInfo;

	public UserAuthentication() {
		
	}
	
	public UserAuthentication(UserAuthenticationDto userAuthenticationDto) {
		UserAuthentication.asUserAuthentication(this, userAuthenticationDto);
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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public static UserAuthentication asUserAuthentication(UserAuthentication userAuthentication, UserAuthenticationDto userAuthenticationDto) {
		if (userAuthentication == null) {
			userAuthentication = new UserAuthentication();
		}

		if (userAuthenticationDto == null) {
			userAuthentication = null;
		}
		else {
			userAuthentication.setId(userAuthenticationDto.getId());
			userAuthentication.setLogin(userAuthenticationDto.getLogin());
			userAuthentication.setLoginAttempts(userAuthenticationDto.getLoginAttempts());
			userAuthentication.setLastLoginDate(userAuthenticationDto.getLastLoginDate());
			userAuthentication.setPassword(userAuthenticationDto.getPassword());
			userAuthentication.setPasswordModifiedDate(userAuthenticationDto.getPasswordModifiedDate());
			userAuthentication.setPasswordExpireDate(userAuthenticationDto.getPasswordExpireDate());	
			userAuthentication.setStatus(userAuthenticationDto.getStatus());
			userAuthentication.setIsLocked(userAuthenticationDto.getIsLocked());
		}
		
		return userAuthentication;
	}
	
	
	@PreRemove
	public void removeUserAuthentication() {
		this.setUserInfo(null);
	}
	
}
