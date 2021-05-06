package com.cldbiz.afw.dao;

import java.util.List;

import com.cldbiz.afw.domain.Resource;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.ResourceDto;

public interface ResourceDao extends BaseDao<Resource> {
	public Resource findResource(Long resourceId);
	public Boolean isDuplicateResource(ResourceDto resourceDto);
	
	public Long countResources(ResourceDto resourceCriteria);
	public List<Resource> findResources(ResourceDto resourceCriteria);
	public List<Resource> searchResources(ResourceDto resourceCriteria);
	public List<Resource> searchResourcesPage(PageReqDto<ResourceDto> pageReqDto);

	public void deleteResources(List<Long> resourceIds);
	public void removeResources(List<Long> resourceIds);
	public Resource saveResource(Resource resource);
	public List<Resource> saveResources(List<Resource> resources);
}
