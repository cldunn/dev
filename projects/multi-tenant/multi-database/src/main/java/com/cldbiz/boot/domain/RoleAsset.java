package com.cldbiz.boot.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cldbiz.boot.dto.AssetDto;
import com.cldbiz.boot.dto.RoleAssetDto;

@Entity
@Table(name="ROLE_ASSET")
@AssociationOverrides({
	@AssociationOverride(name="pk.roleId", joinColumns=@JoinColumn(name="ROLE_ID")),
	@AssociationOverride(name="pk.assetId", joinColumns=@JoinColumn(name="ASSET_ID"))
})
public class RoleAsset extends BaseDomain {
	private static final long serialVersionUID = 3305730792620472530L;

	@EmbeddedId
	private RoleAssetPK pk = new RoleAssetPK();

	@Column(name="IS_CREATABLE")
	private Boolean isCreatable = false;
	
	@Column(name="IS_READABLE")
	private Boolean isReadable = false;

	@Column(name="IS_UPDATABLE")
	private Boolean isUpdatable = false;
	
	@Column(name="IS_DELETABLE")
	private Boolean isDeletable = false;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ASSET_ID", insertable=false, updatable=false, foreignKey = @ForeignKey(name = "FK_ASSET_ID"))
	private Asset asset;

	public RoleAsset() {}
	
	public RoleAsset(RoleAssetDto roleAsseteDto) {
		RoleAsset.asRoleAsset(this, roleAsseteDto);
	}

	
	public RoleAssetPK getPk() {
		return pk;
	}

	public void setPk(RoleAssetPK pk) {
		this.pk = pk;
	}

	public Boolean getIsCreatable() {
		return isCreatable;
	}

	public void setIsCreatable(Boolean isCreatable) {
		this.isCreatable = isCreatable;
	}

	public Boolean getIsReadable() {
		return isReadable;
	}

	public void setIsReadable(Boolean isReadable) {
		this.isReadable = isReadable;
	}

	public Boolean getIsUpdatable() {
		return isUpdatable;
	}

	public void setIsUpdatable(Boolean isUpdatable) {
		this.isUpdatable = isUpdatable;
	}

	public Boolean getIsDeletable() {
		return isDeletable;
	}

	public void setIsDeletable(Boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public static RoleAsset asRoleAsset(RoleAsset roleAsset, RoleAssetDto roleAssetDto) {
		if (roleAsset == null) {
			roleAsset = new RoleAsset();
		}
		
		if (roleAssetDto == null) {
			roleAsset = null;
		}
		else {
			roleAsset.pk = roleAssetDto.getPk();
			roleAsset.setIsCreatable(roleAssetDto.getIsCreatable());
			roleAsset.setIsReadable(roleAssetDto.getIsReadable());
			roleAsset.setIsUpdatable(roleAssetDto.getIsUpdatable());
			roleAsset.setIsDeletable(roleAssetDto.getIsDeletable());
		}
		
		return roleAsset;
	}
	
}
