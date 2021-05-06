package com.cldbiz.afw.dao;

import java.util.List;

import com.cldbiz.afw.domain.RoleResource;
import com.cldbiz.afw.domain.RoleResourcePK;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.RoleResourceDto;

public interface RoleResourceDao extends BaseDao<RoleResource> {
	public RoleResource findRoleResource(RoleResourcePK roleResourcePK);
	
	public Long countRoleResources(RoleResourceDto roleResourceCriteria);
	public List<RoleResource> findRoleResources(RoleResourceDto roleResourceCriteria);
	public List<RoleResource> findRoleResourcesPage(PageReqDto<RoleResourceDto> pageReqDto);

	public void deleteRoleResources(List<RoleResourcePK> roleResourcePKs);
	public void removeRoleResources(List<RoleResourcePK> roleResourcePKs);
	public RoleResource saveRoleResource(RoleResourceDto roleResourceDto);
	public List<RoleResource> saveRoleResources(List<RoleResourceDto> roleResourceDtos);
}
