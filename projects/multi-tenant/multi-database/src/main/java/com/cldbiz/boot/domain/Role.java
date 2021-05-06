package com.cldbiz.boot.domain;

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

import com.cldbiz.boot.dto.RoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ROLE")
public class Role extends EntityDomain {

	private static final long serialVersionUID = 6424147730213188907L;

	@Size(max=40)
    @Column(name="NAME", nullable=false)
	private String name;
	
	@Size(max=40)
    @Column(name="DESCRIPTION", nullable=false)
	private String description;
	
	@Column(name="PRODUCT_ID", nullable=false)
	private Long productId;

	
	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(name="USER_INFO_ROLE", 
			   joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"), 
			   inverseJoinColumns = @JoinColumn(name = "USER_INFO_ID", referencedColumnName = "ID"))
	List<UserInfo> userInfos = new ArrayList<UserInfo>();
	
	@OneToMany(mappedBy="pk.roleId", cascade=CascadeType.ALL, orphanRemoval = true)
	List<RoleAsset> roleAssets = new ArrayList<RoleAsset>();
	
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
	
	public List<RoleAsset> getRoleAssets() {
		return roleAssets;
	}

	public void setRoleAssets(List<RoleAsset> roleAssets) {
		this.roleAssets = roleAssets;
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
			role.setProductId(roleDto.getProductId());
			// TODO: Can get all roleAssets, avoid getting userProfile? can userProfiles go into userInfos, userProfile missing roles
		}
		
		return role;
	}
	
}
