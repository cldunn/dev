package com.cldbiz.afw.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.cldbiz.afw.domain.Resource;

public class ResourceDto extends BaseDto {
	private static final long serialVersionUID = 5940919031330793608L;

	@Size(max=40)
	private String name;
	
	@Size(max=80)
	private String refKey;
	
	private List<ResourceDto> children = new ArrayList<ResourceDto>();
	
	public ResourceDto() {}
	
	public ResourceDto(Resource resource) {
		ResourceDto.asResourceDto(this, resource);
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

	public List<ResourceDto> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceDto> children) {
		this.children = children;
	}
	
	public static ResourceDto asResourceDto(ResourceDto resourceDto, Resource resource) {
		if (resourceDto == null) {
			resourceDto = new ResourceDto();
		}
		
		if (resource == null) {
			resourceDto = null;
		}
		else {
			resourceDto.setId(resource.getId());
			resourceDto.setName(resource.getName());
			resourceDto.setRefKey(resource.getRefKey());
		}

		return resourceDto;
	}

	/*
	public Resource updateResource(Resource resource) {
		resource.setName(this.getName());
		resource.setRefKey(this.getRefKey());
		resource.setSortOrder(this.getSortOrder());
		
		if (this.getParentDto() != null) {
			this.parentDto.updateResource(resource.getParent());
		}
		else {
			resource.setParent(null);
		}
		
		return resource;
	}
	
	public static List<ResourceDto> asResourceDtos(List<Resource> resources) {
		List<ResourceDto> resourceDtos = new ArrayList<ResourceDto>();
		for(Resource resource: resources) {
			resourceDtos.add(new ResourceDto(resource));
		}
		
		return resourceDtos;
	}
	*/
}
