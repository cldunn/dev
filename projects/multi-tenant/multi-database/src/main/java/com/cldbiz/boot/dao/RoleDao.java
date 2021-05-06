package com.cldbiz.boot.dao;

import java.util.List;

import com.cldbiz.boot.domain.Role;
import com.cldbiz.boot.dto.RoleDto;

public interface RoleDao extends BaseDao<Role> { 
	public Role findRole(Long roleId);
	public List<Role> findRoles(RoleDto roleDto);
}
