package com.cldbiz.afw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.cldbiz.afw.dto.RoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ROLE")
public class Role extends EntityDomain {
	private static final long serialVersionUID = -6167055367946416669L;

	@Size(max=40)
    @Column(name="NAME", nullable=false)
	private String name;
	
	@Size(max=40)
    @Column(name="DESCRIPTION", nullable=false)
	private String description;

	@JsonIgnore
	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(name="USER_INFO_ROLE", 
			   joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"), 
			   inverseJoinColumns = @JoinColumn(name = "USER_INFO_ID", referencedColumnName = "ID"))
	List<UserInfo> userInfos = new ArrayList<UserInfo>();
	
	@JsonIgnore
	@OneToMany(mappedBy="pk.roleId", cascade=CascadeType.ALL, orphanRemoval = true)
	List<RoleResource> roleResources = new ArrayList<RoleResource>();
	
	public Role() {}
	
	public Role(RoleDto roleDto) {
		Role.asRole(this, roleDto);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public List<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(List<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}
	
	public static Role asRole(Role role, RoleDto roleDto) {
		if (role == null) {
			role = new Role();
		}

		if (roleDto == null) { 
			role = null;
		}
		else {
			role.setId(roleDto.getId());
			role.setName(roleDto.getName());
			role.setDescription(roleDto.getDescription());
		}
		
		return role;
	}

}
