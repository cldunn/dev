package com.cldbiz.afw.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleResourcePK extends BaseDomain {
	@Column(name="ROLE_ID", nullable=false)
	private Long roleId;
	
	@Column(name="RESOURCE_ID", nullable=false)
	private Long resourceId;

	public RoleResourcePK() {
		
	}
	
	public RoleResourcePK(Long roleId, Long resourceId) {
		this.roleId = roleId;
		this.resourceId = resourceId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
}
