package com.cldbiz.afw.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cldbiz.afw.base.AfwBaseTest;
import com.cldbiz.afw.common.AfwListPredicates;
import com.cldbiz.afw.domain.Resource;
import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.domain.RoleResourcePK;
import com.cldbiz.afw.domain.UserInfo;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.ResourceDto;
import com.cldbiz.afw.dto.RoleDto;
import com.cldbiz.afw.dto.RoleResourceDto;

public class ResourceDaoTest extends AfwBaseTest {
	private static final Logger log = LoggerFactory.getLogger(ResourceDaoTest.class);
	private static Timestamp now;

	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
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
	public void testGetResource() {
		Resource resource = resourceDao.findResource(2L);
		
		assertNotNull(resource);
		resource.getChildren().size();
		
		List<Resource> children = resource.getChildren();
		assertNotNull(children);
		assertNotNull(children.size() > 0);
	}

	@Test
	public void testIsDuplicate() {
		ResourceDto resourceDto = new ResourceDto();
		
		resourceDto.setRefKey("resrc.users.userInfo");
		boolean isDup = resourceDao.isDuplicateResource(resourceDto);
		
		assertTrue(isDup);
		
		Resource resource = resourceDao.findResource(3L);
		resourceDto = new ResourceDto(resource);
		resourceDto.setRefKey("resrc.users.userInfo");
		isDup = resourceDao.isDuplicateResource(resourceDto);
		
		assertFalse(isDup);

		resource = resourceDao.findResource(2L);
		resourceDto = new ResourceDto(resource);
		resourceDto.setRefKey("resrc.users.userInfo");
		isDup = resourceDao.isDuplicateResource(resourceDto);
		
		assertTrue(isDup);
	}

	@Test
	public void testCountResources() {
		ResourceDto resourceCriteria = new ResourceDto();

		resourceCriteria.setRefKey("resrc.users.userInfo");
		Long resourceCnt = resourceDao.countResources(resourceCriteria);
		
		assertTrue(resourceCnt > 0);
		
		resourceCriteria.setRefKey("resrc.");
		resourceCnt = resourceDao.countResources(resourceCriteria);
		
		assertTrue(resourceCnt == 0);
	}
	
	@Test
	public void testFindResources() {
		ResourceDto resourceCriteria = new ResourceDto();
		
		resourceCriteria.setRefKey("resrc.users");
		List<Resource> resources = resourceDao.findResources(resourceCriteria);
		
		assertTrue(resources.size() > 0);
		assertNotNull(resources.get(0).getChildren());
		
		resourceCriteria.setRefKey("resrc.");
		resources = resourceDao.findResources(resourceCriteria);
		assertTrue(resources.size() == 0);
	}

	@Test
	public void testSearchResources() {
		ResourceDto resourceCriteria = new ResourceDto();

		resourceCriteria.setRefKey("resrc.users.");
		List<Resource> resources = resourceDao.searchResources(resourceCriteria);
		
		assertTrue(resources.size() > 1);
		assertTrue(resources.get(0).getChildren().size() == 0);
	}

	@Test
	public void testSearchResourcesPage() {
		PageReqDto<ResourceDto> pageReqDto = new PageReqDto<ResourceDto>(0L, 1L);
		ResourceDto resourceCriteria = new ResourceDto();

		resourceCriteria.setRefKey("resrc.users.");
		pageReqDto.setCriteria(resourceCriteria);
		List<Resource> resources = resourceDao.searchResourcesPage(pageReqDto);
		
		assertTrue(resources.size() == 1);
		assertTrue(resources.get(0).getChildren().size() == 0);
	}

	@Test
	public void testRemoveResources() {
		ResourceDto resourceCriteria = new ResourceDto();
		Long beginResourceCnt = resourceDao.countResources(resourceCriteria);
		
		resourceCriteria.setRefKey("resrc.users.");
		List<Resource> resources = resourceDao.searchResources(resourceCriteria);
		
		assertTrue(resources.size() > 1);
		assertTrue(resources.get(0).getChildren().size() == 0);
		assertTrue(resources.get(1).getChildren().size() == 0);
		
		List<Long>resourceIds = new ArrayList<Long>();
		resourceIds.add(resources.get(0).getId());
		resourceIds.add(resources.get(1).getId());
		resourceDao.removeResources(resourceIds);
		
		Long cnt = resourceDao.countResources(new ResourceDto());
		assertTrue((cnt + 2) == beginResourceCnt);
		
		resourceCriteria.setRefKey("resrc.roles");
		resources = resourceDao.findResources(resourceCriteria);
		
		assertTrue(resources.size() == 1);
		assertTrue(resources.get(0).getChildren().size() == 2);
		
		resourceIds.clear();
		resourceIds.add(resources.get(0).getId());
		resourceDao.removeResources(resourceIds);
		
		cnt = resourceDao.countResources(new ResourceDto());
		assertTrue((cnt + 5) == beginResourceCnt);
		
		RoleResourceDto roleResourceCriteria = new RoleResourceDto();
		Resource child = resources.get(0).getChildren().get(0);
		RoleResourcePK pk = new RoleResourcePK();
		pk.setResourceId(child.getId());
		roleResourceCriteria.setPk(pk);

		Long roleResourceCnt = roleResourceDao.countRoleResources(roleResourceCriteria);
		assertTrue(roleResourceCnt == 0);
	}
	
	@Test
	public void testSaveResource() {
		Long beginResourceCnt = resourceDao.countResources(new ResourceDto());
		
		Resource parent = new Resource();
		parent.setName("Tests");
		parent.setRefKey("resrc.tests");
		
		Resource child = new Resource();
		child.setName("Test One");
		child.setRefKey("resrc.tests.one");
		parent.getChildren().add(child);
		
		Resource resource = resourceDao.saveResource(parent);
		
		Long count = resourceDao.countResources(new ResourceDto());
		assert((beginResourceCnt + 2) == count );
		
		assertNotNull(resource);
		assertTrue(resource.getChildren().size() > 0);
	}

	@Test
	public void testSaveResources() {
		Long beginResourceCnt = resourceDao.countResources(new ResourceDto());
		
		Resource parent1 = new Resource();
		parent1.setName("Tests");
		parent1.setRefKey("resrc.tests");
		
		Resource child = new Resource();
		child.setName("Test One");
		child.setRefKey("resrc.tests.one");
		parent1.getChildren().add(child);
		
		Resource parent2 = new Resource();
		parent2.setName("Animals");
		parent2.setRefKey("resrc.animals");

		List<Resource> parents = new ArrayList<Resource>();
		parents.add(parent1);
		parents.add(parent2);
		
		List<Resource> resources = resourceDao.saveResources(parents);
		
		assertTrue(resources.size() == 2);
		assertTrue(resources.get(0).getId() != null);
		
		AfwListPredicates<Resource> predicates =  new AfwListPredicates<Resource>();
        List<Resource> filteredList = predicates.selectEqualsPropertyFromList(resources, "refKey", "resrc.tests");
        
        assertTrue(filteredList.size() > 0);
		assertTrue(filteredList.get(0).getChildren().size() > 0);
		
        filteredList = predicates.selectEqualsPropertyFromList(resources, "refKey", "resrc.animals");
        
        assertTrue(filteredList.size() > 0);
        assertTrue(filteredList.get(0).getChildren().size() == 0);
		
		Long afterResourceCnt = resourceDao.countResources(new ResourceDto());
		
		assertTrue((beginResourceCnt + 3) == afterResourceCnt);
	}
}
