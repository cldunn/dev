package com.cldbiz.afw.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cldbiz.afw.base.AfwBaseTest;
import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.domain.RoleResource;
import com.cldbiz.afw.domain.RoleResourcePK;
import com.cldbiz.afw.domain.UserInfo;
import com.cldbiz.afw.domain.UserInfoRole;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.RoleDto;
import com.cldbiz.afw.dto.RoleResourceDto;
import com.cldbiz.afw.dto.UserInfoDto;

public class RoleDaoTest extends AfwBaseTest {
	private static final Logger log = LoggerFactory.getLogger(RoleDaoTest.class);
	private static Timestamp now;
	
	@Resource
	private RoleDao roleDao;

	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private RoleResourceDao roleResourceDao;
	
	@Resource
	private ResourceDao resourceDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@BeforeClass
	public static void setUpBeforeTests() {
		refresh("com/cldbiz/afw/data/uniqueid.xml");
		refresh("com/cldbiz/afw/data/resource.xml");
		refresh("com/cldbiz/afw/data/role.xml");
		refresh("com/cldbiz/afw/data/roleResource.xml");
		cleanInsert("com/cldbiz/afw/data/userAuthentication.xml");
		cleanInsert("com/cldbiz/afw/data/userInfo.xml");
		cleanInsert("com/cldbiz/afw/data/userInfoRole.xml");
		now = new Timestamp(new Date().getTime());
	}

	@AfterClass
	public static void cleanAfterTests() {
		deleteAll("com/cldbiz/afw/data/userInfoRole.xml");
		deleteAll("com/cldbiz/afw/data/userInfo.xml");
		deleteAll("com/cldbiz/afw/data/userAuthentication.xml");
		delete("com/cldbiz/afw/data/roleResource.xml");
		delete("com/cldbiz/afw/data/role.xml");
		delete("com/cldbiz/afw/data/resource.xml");
		delete("com/cldbiz/afw/data/uniqueid.xml");
	}

	@Test
	public void testGetRole() {
		Role role = roleDao.findRole(2L);
		
		assertNotNull(role);
		assertTrue(role.getUserInfos().size() > 0);
		assertTrue(role.getRoleResources().size() > 0);
	}

	@Test
	public void testIsDuplicate() {
		RoleDto roleDto = new RoleDto();
		roleDto.setName("ADMIN");
		
		boolean isDup = roleDao.isDuplicateRole(roleDto);
		assertTrue(isDup);
		
		Role role = roleDao.findRole(1L);
		roleDto = new RoleDto(role);
		
		isDup = roleDao.isDuplicateRole(roleDto);
		assertFalse(isDup);
	}

	@Test
	public void testCountRoles() {
		RoleDto roleCriteria = new RoleDto();

		roleCriteria.setName("ADMIN");
		Long roleCnt = roleDao.countRoles(roleCriteria);
		
		assertTrue(roleCnt > 0);
		
		roleCriteria.setName("AD");
		roleCnt = roleDao.countRoles(roleCriteria);
		
		assertTrue(roleCnt == 0);
	}

	@Test
	public void testFindRoles() {
		RoleDto roleCriteria = new RoleDto();
		
		roleCriteria.setName("ADMIN");
		
		List<Role> roles = roleDao.findRoles(roleCriteria);
		assertTrue(roles.size() > 0);
		assertTrue(roles.get(0).getUserInfos().size() > 0);
		assertTrue(roles.get(0).getRoleResources().size() > 0);
		
		
		roleCriteria.setName("AD");
		roles = roleDao.findRoles(roleCriteria);
		assertTrue(roles.size() == 0);
	}

	@Test
	public void testSearchRoles() {
		RoleDto roleCriteria = new RoleDto();

		roleCriteria.setName("ADMIN");
		List<Role> roles = roleDao.searchRoles(roleCriteria);
		
		assertTrue(roles.size() > 0);
		assertTrue(roles.get(0).getUserInfos().size() > 0);
		assertTrue(roles.get(0).getRoleResources().size() > 0);
		
		roleCriteria.setName("AD");
		roles = roleDao.searchRoles(roleCriteria);
		
		assertTrue(roles.size() > 0);
		assertTrue(roles.get(0).getUserInfos().size() > 0);
		assertTrue(roles.get(0).getRoleResources().size() > 0);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testSearchRolesPage() {
		PageReqDto<RoleDto> pageReqDto = new PageReqDto<RoleDto>(0L, 1L);
		RoleDto roleCriteria = new RoleDto();

		roleCriteria.setName("ADMIN");
		pageReqDto.setCriteria(roleCriteria);
		List<Role> roles = roleDao.searchRolesPage(pageReqDto);
		
		assertTrue(roles.size() == 1);
		assertTrue(roles.get(0).getUserInfos().size() > 0);
		assertTrue(roles.get(0).getRoleResources().size() > 0);
	}

	// @Test
	// Need to reinstate delete trigger for querydsl delete to cascade role resource delete
	/********************************************************************************************
	IF OBJECT_ID ('tr_ROLE_DELETE', 'TR') IS NOT NULL  
	begin
		DROP TRIGGER tr_ROLE_DELETE
	end
	go

	-- Equivalent BEFORE trigger, cascades delete ROLE_RESOURCE when deleting RESOURCE 
	create trigger tr_ROLE_DELETE
	on ROLE
	instead of delete
	as
	begin
		PRINT N'This message was printed from tr_ROLE_DELETE'
		delete from ROLE_RESOURCE where ROLE_ID in ( select ID from deleted )
		delete ROLE where id in (select id from deleted)
	end
	*********************************************************************************************/

	public void testDeleteRoles() {
		RoleDto roleCriteria = new RoleDto();
		
		List<Role> roles = roleDao.findRoles(roleCriteria);
		assert(roles.size() > 1);
		assert(roles.get(0).getUserInfos().size() > 0);
		assert(roles.get(0).getRoleResources().size() > 0);
		assert(roles.get(1).getUserInfos().size() > 0);
		assert(roles.get(1).getRoleResources().size() > 0);
		
		List<Long>roleIds = new ArrayList<Long>();
		
		roleIds.add(roles.get(0).getId());
		roleIds.add(roles.get(1).getId());
		roleDao.deleteRoles(roleIds);
		
		Long cnt = roleDao.countRoles(roleCriteria);
		assert((cnt + 2) == roles.size());
		
		UserInfo userInfo = userInfoDao.findUserInfo(roles.get(0).getUserInfos().get(0).getId());
		assert(userInfo != null);
		
		Query q = entityManager.createNativeQuery("SELECT uir.USER_INFO_ID, uir.ROLE_ID FROM USER_INFO_ROLE uir WHERE uir.USER_INFO_ID = :userId and uir.ROLE_ID = :roleId", UserInfoRole.class);
		q.setParameter("userId", roles.get(0).getUserInfos().get(0).getId());
        q.setParameter("roleId", roles.get(0).getId());
        
		List<UserInfoRole> userInfoRoles = (List<UserInfoRole>) q.getResultList();
		assert(userInfoRoles.size() == 0);
		
		com.cldbiz.afw.domain.Resource resource = resourceDao.findResource(roles.get(0).getRoleResources().get(0).getPk().getResourceId());
		assert(resource != null);
	}

	@Test
	public void testRemoveRoles() {
		RoleDto roleCriteria = new RoleDto();
		
		List<Role> roles = roleDao.findRoles(roleCriteria);
		assert(roles.size() > 1);
		assert(roles.get(0).getUserInfos().size() > 0);
		assert(roles.get(0).getRoleResources().size() > 0);
		assert(roles.get(1).getUserInfos().size() > 0);
		assert(roles.get(1).getRoleResources().size() > 0);
		
		List<Long>roleIds = new ArrayList<Long>();
		roleIds.add(roles.get(0).getId());
		roleIds.add(roles.get(1).getId());
		roleDao.removeRoles(roleIds);
		
		Long cnt = roleDao.countRoles(roleCriteria);
		assert((cnt + 2) == roles.size());
		
		UserInfo userInfo = userInfoDao.findUserInfo(roles.get(0).getUserInfos().get(0).getId());
		assert(userInfo != null);
		
		Query q = entityManager.createNativeQuery("SELECT uir.USER_INFO_ID, uir.ROLE_ID FROM USER_INFO_ROLE uir WHERE uir.USER_INFO_ID = :userId and uir.ROLE_ID = :roleId", UserInfoRole.class);
		q.setParameter("userId", roles.get(0).getUserInfos().get(0).getId());
        q.setParameter("roleId", roles.get(0).getId());
        List<UserInfoRole> userInfoRoles = (List<UserInfoRole>) q.getResultList();
		assert(userInfoRoles.size() == 0);
		
		com.cldbiz.afw.domain.Resource resource = resourceDao.findResource(roles.get(0).getRoleResources().get(0).getPk().getResourceId());
		assert(resource != null);
	}
	
	@Test
	public void testSaveRole() {
		RoleDto roleCriteria = new RoleDto();
		
		Long original = roleDao.countRoles(roleCriteria);
		
		RoleDto roleDto = new RoleDto();
		roleDto.setName("EMPLOYEE");
		roleDto.setDescription("Employee");
		
		Role role = new Role(roleDto);
		role.getUserInfos().add(userInfoDao.findUserInfo(1L));

		role = roleDao.saveRole(role);
		
		Long count = roleDao.countRoles(roleCriteria);
		assert((original + 1) == count);

		RoleResourceDto roleResourceDto = new RoleResourceDto();
		RoleResourcePK pk = new RoleResourcePK();
		pk.setRoleId(role.getId());
		pk.setResourceId(5L);
		roleResourceDto.setPk(pk);
		roleResourceDto.setIsReadable(true);
		roleResourceDto.setIsUpdatable(true);

		role.getRoleResources().add(new RoleResource(roleResourceDto));
		
		assert(role.getName().equalsIgnoreCase("EMPLOYEE"));
		assert(role.getUserInfos().size() > 0);
		assert(role.getUserInfos().get(0).getEmailAddress().equals("cliffdunntx@yahoo.com"));
		assert(role.getRoleResources().size() > 0);
		assert(role.getRoleResources().get(0).getPk().getResourceId().equals(5L));
		
		RoleResource roleResource = roleResourceDao.findRoleResource(pk);
		assertNotNull(roleResource);
	}

	@Test
	public void testSaveRoles() {
		RoleDto roleCriteria = new RoleDto();
		Long original = roleDao.countRoles(roleCriteria);
		
		RoleDto roleDto1 = new RoleDto();
		roleDto1.setName("EMPLOYEE");
		roleDto1.setDescription("Employee");
		
		Role role1 = new Role(roleDto1);
		
		RoleDto roleDto2 = new RoleDto();
		roleDto2.setName("VENDOR");
		roleDto2.setDescription("Vendor");
		
		Role role2 = new Role(roleDto2);
		role2.getUserInfos().add(userInfoDao.findUserInfo(2L));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role1);
		roles.add(role2);

		roleDao.saveRoles(roles);
		
		Long count = roleDao.countRoles(roleCriteria);
		assert((original + 2) == count);
		
		RoleResourceDto roleResourceDto2 = new RoleResourceDto();
		roleResourceDto2.setPk(new RoleResourcePK(role2.getId(), 5L));
		roleResourceDto2.setIsReadable(true);
		roleResourceDto2.setIsUpdatable(false);
		
		role2.getRoleResources().add(new RoleResource(roleResourceDto2));

		Predicate predicateEmployee = new Predicate() {
            public boolean evaluate(Object object) {
                return ((Role) object).getName().equalsIgnoreCase("EMPLOYEE");
            }
        };
        Collection<Role> filtered = (List<Role>) CollectionUtils.select(roles, predicateEmployee);
        List<Role> filteredList = new ArrayList<Role>(filtered);

        assert(filteredList.size() > 0);
		assert(filteredList.get(0).getUserInfos().size() == 0);
		assert(filteredList.get(0).getRoleResources().size() == 0);

		Predicate predicateVendor = new Predicate() {
            public boolean evaluate(Object object) {
                return ((Role) object).getName().equalsIgnoreCase("VENDOR");
            }
        };
        filtered = (List<Role>) CollectionUtils.select(roles, predicateVendor);
        filteredList = new ArrayList<Role>(filtered);

        assert(filteredList.size() > 0);
		assert(filteredList.get(0).getUserInfos().size() > 0);
		assert(filteredList.get(0).getUserInfos().get(0).getEmailAddress().equalsIgnoreCase("sabinedunntx@yahoo.com"));
		assert(filteredList.get(0).getRoleResources().size() > 0);
		assert(filteredList.get(0).getRoleResources().get(0).getPk().getResourceId().equals(5L));
		assertFalse(filteredList.get(0).getRoleResources().get(0).getIsUpdatable());
	}

}
