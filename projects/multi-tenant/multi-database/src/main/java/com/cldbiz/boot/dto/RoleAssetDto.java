package com.cldbiz.boot.dto;

import com.cldbiz.boot.domain.RoleAsset;
import com.cldbiz.boot.domain.RoleAssetPK;

public class RoleAssetDto extends BaseDto {
	private static final long serialVersionUID = -8707573999445199344L;

	private RoleAssetPK pk = new RoleAssetPK();
	
	private Boolean isCreatable;
	
	private Boolean isReadable;
	
	private Boolean isUpdatable;
	
	private Boolean isDeletable;

	private AssetDto assetDto;
	
	public RoleAssetDto() {
		
	}
	
	public RoleAssetDto(RoleAsset roleAsset) {
		RoleAssetDto.asRoleAssetDto(this, roleAsset);
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

	public AssetDto getAssetDto() {
		return assetDto;
	}

	public void setAssetDto(AssetDto assetDto) {
		this.assetDto = assetDto;
	}

	public static RoleAssetDto asRoleAssetDto(RoleAssetDto roleAssetDto, RoleAsset roleAsset) {
		if (roleAssetDto == null) {
			roleAssetDto = new RoleAssetDto();
		}
		
		if (roleAsset == null) {
			roleAssetDto = null;
		}
		else {
			roleAssetDto.pk = roleAsset.getPk();
			roleAssetDto.setIsCreatable(roleAsset.getIsCreatable());
			roleAssetDto.setIsReadable(roleAsset.getIsReadable());
			roleAssetDto.setIsUpdatable(roleAsset.getIsUpdatable());
			roleAssetDto.setIsDeletable(roleAsset.getIsDeletable());
		}
		
		return roleAssetDto;
	}

}
