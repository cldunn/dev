package com.cldbiz.boot.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleAssetPK extends BaseDomain {
	@Column(name="ROLE_ID", nullable=false)
	private Long roleId;
	
	@Column(name="ASSET_ID", nullable=false)
	private Long assetId;
	
	public RoleAssetPK() {
		
	}
	
	public RoleAssetPK(Long roleId, Long assetId) {
		this.roleId = roleId;
		this.assetId = assetId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
	
}
