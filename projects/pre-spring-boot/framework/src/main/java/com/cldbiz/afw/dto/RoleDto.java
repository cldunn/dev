package com.cldbiz.afw.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.domain.RoleResource;
import com.cldbiz.afw.domain.UserInfo;

public class RoleDto extends BaseDto {
	private static final long serialVersionUID = -2596922597269001108L;

	@Size(max=40)
	private String name;
	
	@Size(max=40)
	private String description;
	
	private List<UserInfoDto> userInfoDtos = new ArrayList<UserInfoDto>();
		
	private List<RoleResourceDto> roleResourceDtos = new ArrayList<RoleResourceDto>();

	private UserInfoDto userInfoCriteria;
	
	public RoleDto() {
		
	}
	
	public RoleDto(Role role) {
		RoleDto.asRoleDto(this, role);
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
	
	
	public List<UserInfoDto> getUserInfoDtos() {
		return userInfoDtos;
	}

	public void setUserInfoDtos(List<UserInfoDto> userInfoDtos) {
		this.userInfoDtos = userInfoDtos;
	}

	public void assignUserInfoDtos(List<UserInfo> userInfos) {
		for(UserInfo userInfo: userInfos) {
			this.getUserInfoDtos().add(new UserInfoDto(userInfo));
		}
	}

	public List<RoleResourceDto> getRoleResourceDtos() {
		return roleResourceDtos;
	}

	public void setRoleResourceDtos(List<RoleResourceDto> roleResourceDtos) {
		this.roleResourceDtos = roleResourceDtos;
	}

	public void assignRoleResourceDtos(List<RoleResource> roleResources) {
		for(RoleResource roleResource: roleResources) {
			this.getRoleResourceDtos().add(new RoleResourceDto(roleResource));
		}
	}

	
	public UserInfoDto getUserInfoCriteria() {
		return userInfoCriteria;
	}

	public void setUserInfoCriteria(UserInfoDto userInfoCriteria) {
		this.userInfoCriteria = userInfoCriteria;
	}

	public static RoleDto asRoleDto(RoleDto roleDto, Role role) {
		if (roleDto == null) {
			roleDto = new RoleDto();
		}

		if (role == null) { 
			roleDto = null;
		}
		else {
			roleDto.setId(role.getId());
			roleDto.setName(role.getName());
			roleDto.setDescription(role.getDescription());
		}
		
		return roleDto;
	}
}
