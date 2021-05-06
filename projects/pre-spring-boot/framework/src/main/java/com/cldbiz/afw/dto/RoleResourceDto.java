package com.cldbiz.afw.dto;

import java.util.ArrayList;
import java.util.List;

import com.cldbiz.afw.domain.RoleResource;
import com.cldbiz.afw.domain.RoleResourcePK;

public class RoleResourceDto extends BaseDto {
	private static final long serialVersionUID = -8626642725096958799L;

	private RoleResourcePK pk = new RoleResourcePK();
	
	private Boolean isCreatable;
	private Boolean isReadable;
	private Boolean isUpdatable;
	private Boolean isDeletable;
	private List<RoleResourceDto> children = new ArrayList<RoleResourceDto>();
	
	public RoleResourceDto() {
		
	}
	
	public RoleResourceDto(RoleResource roleResource) {
		RoleResourceDto.asRoleResourceDto(this, roleResource);
	}
	
	public RoleResourcePK getPk() {
		return pk;
	}
	
	public void setPk(RoleResourcePK pk) {
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
	
	public List<RoleResourceDto> getChildren() {
		return children;
	}

	public void setChildren(List<RoleResourceDto> children) {
		this.children = children;
	}

	public static RoleResourceDto asRoleResourceDto(RoleResourceDto roleResourceDto, RoleResource roleResource) {
		if (roleResourceDto == null) {
			roleResourceDto = new RoleResourceDto();
		}
		
		if (roleResource == null) {
			roleResourceDto = null;
		}
		else {
			roleResourceDto.pk = roleResource.getPk();
			roleResourceDto.setIsCreatable(roleResource.getIsCreatable());
			roleResourceDto.setIsReadable(roleResource.getIsReadable());
			roleResourceDto.setIsUpdatable(roleResource.getIsUpdatable());
			roleResourceDto.setIsDeletable(roleResource.getIsDeletable());
		}
		
		return roleResourceDto;
	}
}
