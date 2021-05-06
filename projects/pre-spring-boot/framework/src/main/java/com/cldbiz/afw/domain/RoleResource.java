package com.cldbiz.afw.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cldbiz.afw.dto.RoleResourceDto;

@Entity
@Table(name="ROLE_RESOURCE")
@AssociationOverrides({
	@AssociationOverride(name="pk.roleId", joinColumns=@JoinColumn(name="ROLE_ID")),
	@AssociationOverride(name="pk.resourceId", joinColumns=@JoinColumn(name="RESOURCE_ID"))
})
public class RoleResource extends BaseDomain {
	private static final long serialVersionUID = -5665141782378672730L;

	@EmbeddedId
	private RoleResourcePK pk = new RoleResourcePK();
	
	@Column(name="IS_CREATABLE")
	private Boolean isCreatable = false;
	
	@Column(name="IS_READABLE")
	private Boolean isReadable = false;

	@Column(name="IS_UPDATABLE")
	private Boolean isUpdatable = false;
	
	@Column(name="IS_DELETABLE")
	private Boolean isDeletable = false;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RESOURCE_ID", insertable=false, updatable=false)
	private Resource resource;
	
	public RoleResource() {}
	
	public RoleResource(RoleResourceDto roleResourceDto) {
		RoleResource.asRoleResource(this, roleResourceDto);
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
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public static RoleResource asRoleResource(RoleResource roleResource, RoleResourceDto roleResourceDto) {
		if (roleResource == null) {
			roleResource = new RoleResource();
		}
		
		if (roleResourceDto == null) {
			roleResourceDto = new RoleResourceDto();
		}
		else {
			roleResource.pk = roleResourceDto.getPk();
			roleResource.setIsCreatable(roleResourceDto.getIsCreatable());
			roleResource.setIsReadable(roleResourceDto.getIsReadable());
			roleResource.setIsUpdatable(roleResourceDto.getIsUpdatable());
			roleResource.setIsDeletable(roleResourceDto.getIsDeletable());
		}
		
		return roleResource;
	}
	
}
