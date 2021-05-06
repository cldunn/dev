package com.cldbiz.boot.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.cldbiz.boot.domain.Role;
import com.cldbiz.boot.dto.RoleDto;
import com.cldbiz.boot.dto.UserProfileDto;

public class RoleDto extends BaseDto {
	private static final long serialVersionUID = 5804586563307839369L;

	@Size(max=40)
	private String name;
	
	@Size(max=40)
	private String description;
	
	private Long productId;
	
	private List<UserProfileDto> userProfileDtos = new ArrayList<UserProfileDto>();
	
	private List<RoleAssetDto> roleAssetDtos = new ArrayList<RoleAssetDto>();

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
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<UserProfileDto> getUserProfileDtos() {
		return userProfileDtos;
	}

	public void setUserProfileDtos(List<UserProfileDto> userProfileDtos) {
		this.userProfileDtos = userProfileDtos;
	}

	public List<RoleAssetDto> getRoleAssetDtos() {
		return roleAssetDtos;
	}

	public void setRoleAssetDtos(List<RoleAssetDto> roleAssetDtos) {
		this.roleAssetDtos = roleAssetDtos;
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
			roleDto.setProductId(role.getProductId());
		}
		
		return roleDto;
	}
	
}
