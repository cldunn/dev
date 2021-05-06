package com.cldbiz.boot.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// @Embeddable
public class UserInfoRolePK extends BaseDomain {
	private static final long serialVersionUID = 4136440915462393090L;

	@Column(name="USER_INFO_ID", nullable=false)
	private Long userInfoId;
	
	@Column(name="ROLE_ID", nullable=false)
	private Long roleId;

	public UserInfoRolePK() {
		
	}
	
	public UserInfoRolePK(Long userInfoId, Long roleId) {
		this.userInfoId = userInfoId;
		this.roleId = roleId;
	}

	public Long getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
