package com.cldbiz.afw.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cldbiz.afw.base.AfwBaseTest;
import com.cldbiz.afw.common.AfwListPredicates;
import com.cldbiz.afw.domain.RoleResource;
import com.cldbiz.afw.domain.RoleResourcePK;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.RoleResourceDto;

public class RoleResourceDaoTest extends AfwBaseTest {
	private static final Logger log = LoggerFactory.getLogger(RoleResourceDaoTest.class);
	private static Timestamp now;

	@Resource
	private RoleResourceDao roleResourceDao;

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
	public void testGetRoleResource() {
		RoleResourcePK pk1 = new RoleResourcePK(1L, 5L);
		RoleResource roleResource = roleResourceDao.findRoleResource(pk1);
		
		assertNotNull(roleResource);
		assertTrue(roleResource.getIsCreatable());
		assertTrue(roleResource.getIsDeletable());
		assertTrue(roleResource.getIsReadable());
		assertTrue(roleResource.getIsUpdatable());
	}

	@Test
	public void testCountRoleResources() {
		RoleResourceDto roleResourceCriteria = new RoleResourceDto();
		RoleResourcePK pk = new RoleResourcePK();
		pk.setRoleId(1L);
		
		roleResourceCriteria.setPk(pk);
		Long roleResourceCnt = roleResourceDao.countRoleResources(roleResourceCriteria);
		
		assertTrue(roleResourceCnt > 1);
	}

	@Test
	public void testFindRoleResources() {
		RoleResourceDto roleResourceCriteria = new RoleResourceDto();
		RoleResourcePK pk = new RoleResourcePK();
		
		pk.setRoleId(1L);
		roleResourceCriteria.setPk(pk);
		List<RoleResource> roleResources = roleResourceDao.findRoleResources(roleResourceCriteria);
		
		assertTrue(roleResources.size() > 1);
		
		for (RoleResource roleResource: roleResources) {
			assertTrue(roleResource.getIsCreatable());
			assertTrue(roleResource.getIsDeletable());
			assertTrue(roleResource.getIsReadable());
			assertTrue(roleResource.getIsUpdatable());
		}
		
		pk.setRoleId(null);
		pk.setResourceId(7L);
		roleResources = roleResourceDao.findRoleResources(roleResourceCriteria);
		
		assertTrue(roleResources.size() > 1);
	}

	@Test
	public void testFindRoleResourcesPage() {
		PageReqDto<RoleResourceDto> pageReqDto = new PageReqDto<RoleResourceDto>(0L, 1L);
		RoleResourceDto roleResourceCriteria = new RoleResourceDto();
		RoleResourcePK pk = new RoleResourcePK();
		
		pk.setRoleId(1L);
		roleResourceCriteria.setPk(pk);
		pageReqDto.setCriteria(roleResourceCriteria);
		List<RoleResource> roleResources = roleResourceDao.findRoleResourcesPage(pageReqDto);
		
		assertTrue(roleResources.size() == 1);
		assertTrue(roleResources.get(0).getIsCreatable());
		assertTrue(roleResources.get(0).getIsDeletable());
		assertTrue(roleResources.get(0).getIsReadable());
		assertTrue(roleResources.get(0).getIsUpdatable());
	}
	
	@Test
	public void testDeleteRoleResources() {
		List<RoleResourcePK> roleResourcePKs = new ArrayList<RoleResourcePK>();
		RoleResourceDto roleResourceCriteria = new RoleResourceDto();
		RoleResourcePK pk = new RoleResourcePK();
		
		pk.setRoleId(1L);
		roleResourceCriteria.setPk(pk);
		Long roleResourceCnt = roleResourceDao.countRoleResources(roleResourceCriteria);
		
		assertTrue(roleResourceCnt > 1);
		
		pk.setResourceId(3L);
		roleResourcePKs.add(pk);
		roleResourcePKs.add(new RoleResourcePK(1L, 4L));
		roleResourceDao.deleteRoleResources(roleResourcePKs);
				
		pk.setResourceId(null);
		Long latestCnt = roleResourceDao.countRoleResources(roleResourceCriteria);
		
		assertTrue(latestCnt + 2 == roleResourceCnt);
	}

	@Test
	public void testRemoveRoleResources() {
		List<RoleResourcePK> roleResourcePKs = new ArrayList<RoleResourcePK>();
		RoleResourceDto roleResourceCriteria = new RoleResourceDto();
		RoleResourcePK pk = new RoleResourcePK();
		
		pk.setRoleId(1L);
		roleResourceCriteria.setPk(pk);
		Long roleResourceCnt = roleResourceDao.countRoleResources(roleResourceCriteria);
		
		assertTrue(roleResourceCnt > 1);
		
		pk.setResourceId(3L);
		roleResourcePKs.add(pk);
		roleResourcePKs.add(new RoleResourcePK(1L, 4L));
		roleResourceDao.removeRoleResources(roleResourcePKs);
				
		pk.setResourceId(null);
		Long latestCnt = roleResourceDao.countRoleResources(roleResourceCriteria);
		
		assertTrue(latestCnt + 2 == roleResourceCnt);
	}

	@Test
	public void testSaveRoleResource() {
		RoleResourceDto roleResourceDto = new RoleResourceDto();
		
		RoleResourcePK pk = new RoleResourcePK(2L, 4L);
		roleResourceDto.setPk(pk);
		roleResourceDto.setIsCreatable(true);
		roleResourceDto.setIsUpdatable(true);
		roleResourceDao.saveRoleResource(roleResourceDto);
		
		pk = new RoleResourcePK(2L, 8L);
		roleResourceDto.setPk(pk);
		roleResourceDto.setIsCreatable(true);
		roleResourceDto.setIsDeletable(true);
		roleResourceDto.setIsReadable(true);
		roleResourceDto.setIsUpdatable(true);
		roleResourceDao.saveRoleResource(roleResourceDto);
		
		RoleResourceDto roleResourceCriteria = new RoleResourceDto();
		pk = new RoleResourcePK();
		pk.setRoleId(2L);
		List<RoleResource> roleResources = roleResourceDao.findRoleResources(roleResourceCriteria);
		
		AfwListPredicates<RoleResource> predicates =  new AfwListPredicates<RoleResource>();
        List<RoleResource> filteredList = predicates.selectEqualsPropertyFromList(roleResources, "pk.resourceId", 4L);
        assertTrue(filteredList.get(0).getIsCreatable());
        assertTrue(filteredList.get(0).getIsUpdatable());
        
        filteredList = predicates.selectEqualsPropertyFromList(roleResources, "pk.resourceId", 8L);
        assertTrue(filteredList.get(0).getIsCreatable());
        assertTrue(filteredList.get(0).getIsDeletable());
        assertTrue(filteredList.get(0).getIsReadable());
        assertTrue(filteredList.get(0).getIsUpdatable());
	}
	
	@Test
	public void testSaveRoleResources() {
		RoleResourceDto roleResourceCriteria = new RoleResourceDto();
		RoleResourcePK pk = new RoleResourcePK();
		pk.setRoleId(2L);
		roleResourceCriteria.setPk(pk);
		
		Long beginRoleResourceCnt = roleResourceDao.countRoleResources(roleResourceCriteria);

		RoleResourceDto roleResource1 = new RoleResourceDto();
		RoleResourcePK pk1 = new RoleResourcePK(2L, 4L);
		roleResource1.setPk(pk1);
		roleResource1.setIsCreatable(true);
		roleResource1.setIsDeletable(true);
		roleResource1.setIsReadable(true);
		roleResource1.setIsUpdatable(true);
		
		RoleResourceDto roleResource2 = new RoleResourceDto();
		RoleResourcePK pk2 = new RoleResourcePK(2L, 5L);
		roleResource2.setPk(pk2);
		roleResource2.setIsCreatable(false);
		roleResource2.setIsDeletable(true);
		roleResource2.setIsReadable(true);
		roleResource2.setIsUpdatable(false);
		
		List<RoleResourceDto> roleResourceDtos = new ArrayList<RoleResourceDto>();
		roleResourceDtos.add(roleResource1);
		roleResourceDtos.add(roleResource2);
		
		roleResourceDao.saveRoleResources(roleResourceDtos);
		
		Long endRoleResourceCnt = roleResourceDao.countRoleResources(roleResourceCriteria);
		
		assertTrue(beginRoleResourceCnt + 2 == endRoleResourceCnt);
	}
}
