package com.cldbiz.afw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.cldbiz.afw.dto.ResourceDto;

@Entity
@Table(name="RESOURCE",
	   uniqueConstraints= { @UniqueConstraint(name = "UK_REF_KEY", columnNames={"REF_KEY"})})
public class Resource extends EntityDomain {
	private static final long serialVersionUID = -5258570289507572087L;

	@Size(max=40)
    @Column(name="NAME", nullable=false)
	private String name;
	
	@Size(max=80)
    @Column(name="REF_KEY", nullable=false)
	private String refKey;
	
 	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", nullable=true, foreignKey = @ForeignKey(name = "FK_PARENT_RESOURCE_ID"))
	private List<Resource> children = new ArrayList<Resource>();
	
	@OneToMany(mappedBy="resource", cascade=CascadeType.REMOVE)
	private List<RoleResource> roleResources = new ArrayList<RoleResource>();
	
    public Resource() {}
	
	public Resource(ResourceDto resourceDto) {
		Resource.asResource(this, resourceDto);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public List<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(List<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}

	public static Resource asResource(Resource resource, ResourceDto resourceDto) {
		if (resource == null) {
			resource = new Resource();
		}
		
		if (resourceDto == null) {
			resource = null;
		}
		else {
			resource.setId(resourceDto.getId());
			resource.setName(resourceDto.getName());
			resource.setRefKey(resourceDto.getRefKey());
		}
		
		return resource;
	}
}
