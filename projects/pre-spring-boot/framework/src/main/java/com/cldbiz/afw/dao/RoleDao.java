package com.cldbiz.afw.dao;

import java.util.List;

import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.RoleDto;

public interface RoleDao extends BaseDao<Role> {
	public Role findRole(Long roleId);
	public Boolean isDuplicateRole(RoleDto roleDto);
	
	public Long countRoles(RoleDto roleCriteria);
	public List<Role> findRoles(RoleDto roleCriteria);
	public List<Role> searchRoles(RoleDto roleCriteria);
	public List<Role> searchRolesPage(PageReqDto<RoleDto> pageReqDto);

	public void deleteRoles(List<Long> roleIds);
	public void removeRoles(List<Long> roleIds);
	public Role saveRole(Role role);
	public List<Role> saveRoles(List<Role> roles);
}
