package com.cldbiz.afw.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import com.cldbiz.afw.common.AfwConstants;
import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.domain.UserAuthentication;
import com.cldbiz.afw.domain.UserInfo;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.RoleDto;
import com.cldbiz.afw.dto.UserAuthenticationDto;
import com.cldbiz.afw.dto.UserInfoDto;

public class UserInfoDaoTest extends AfwBaseTest {
	private static final Logger log = LoggerFactory.getLogger(UserInfoDaoTest.class);
	private static Timestamp now;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private UserAuthenticationDao userAuthenticationDao;
	
	@Resource
	private RoleDao roleDao;
	
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
	public void testGetUserInfo() {
		UserInfo userInfo = userInfoDao.findUserInfo(1L);
		
		assertNotNull(userInfo);
		assertTrue(userInfo.getRoles().size() > 0);
		assertTrue(userInfo.getUserAuthentication() != null);
	}
	
	@Test
	public void testIsDuplicate() {
		UserInfoDto userInfoDto = new UserInfoDto();

		userInfoDto.setEmailAddress("cliffdunntx@yahoo.com");
		
		boolean isDup = userInfoDao.isDuplicateUserInfo(userInfoDto);
		assertTrue(isDup);
		
		UserInfo userInfo = userInfoDao.findUserInfo(1L);
		userInfoDto = new UserInfoDto(userInfo);
		
		isDup = userInfoDao.isDuplicateUserInfo(userInfoDto);
		assertFalse(isDup);
	}

	@Test
	public void testCountUserInfos() {
		UserInfoDto userInfoCriteria = new UserInfoDto();

		userInfoCriteria.setLastName("Dunn");
		Long userInfoCnt = userInfoDao.countUserInfos(userInfoCriteria);
		
		assertTrue(userInfoCnt > 0);
		
		userInfoCriteria.setLastName("Du");
		userInfoCnt = userInfoDao.countUserInfos(userInfoCriteria);
		
		assertTrue(userInfoCnt == 0);
	}
	
	@Test
	public void testFindUserInfos() {
		UserInfoDto userInfoCriteria = new UserInfoDto();

		userInfoCriteria.setEmailAddress("cliffdunntx@yahoo.com");
		List<UserInfo> userInfos = userInfoDao.findUserInfos(userInfoCriteria);
		
		assertTrue(userInfos.size() > 0);
		assertTrue(userInfos.get(0).getRoles().size() > 0);
		assertTrue(userInfos.get(0).getUserAuthentication() != null);
		
		userInfoCriteria.setEmailAddress("@yahoo.com");
		userInfos = userInfoDao.findUserInfos(userInfoCriteria);
		
		assertTrue(userInfos.size() == 0);
	}
	
	@Test
	public void testSearchUserInfos() {
		UserInfoDto userInfoCriteria = new UserInfoDto();

		userInfoCriteria.setEmailAddress("cliffdunntx@yahoo.com");
		List<UserInfo> userInfos = userInfoDao.searchUserInfos(userInfoCriteria);
		
		assertTrue(userInfos.size() > 0);
		assertTrue(userInfos.get(0).getRoles().size() > 0);
		assertTrue(userInfos.get(0).getUserAuthentication() != null);
		
		userInfoCriteria.setEmailAddress("@yahoo.com");
		userInfos = userInfoDao.searchUserInfos(userInfoCriteria);
		
		assertTrue(userInfos.size() > 0);
		assertTrue(userInfos.get(0).getRoles().size() > 0);
		assertTrue(userInfos.get(0).getUserAuthentication() != null);
	}

	@Test
	public void testSearchUserInfosPage() {
		PageReqDto<UserInfoDto> pageReqDto = new PageReqDto<UserInfoDto>(0L, 1L);
		UserInfoDto userInfoCriteria = new UserInfoDto();

		userInfoCriteria.setLastName("Dunn");
		pageReqDto.setCriteria(userInfoCriteria);
		List<UserInfo> userInfos = userInfoDao.searchUserInfosPage(pageReqDto);
		
		assertTrue(userInfos.size() == 1);
		assertTrue(userInfos.get(0).getRoles().size() > 0);
		assertTrue(userInfos.get(0).getUserAuthentication() != null);
	}

	
	// @Test
	// Need to reinstate delete trigger for querydsl delete to cascade user authentication delete
	/********************************************************************************************
	IF OBJECT_ID ('USER_INFO_DELETE', 'TR') IS NOT NULL  
	begin
		DROP TRIGGER USER_INFO_DELETE
	end
	go
	
	create trigger USER_INFO_DELETE
	on USER_INFO
	for delete
	as
	begin
		delete from USER_AUTHENTICATION where ID in ( select USER_AUTHENTICATION_ID from deleted )
	end
	*********************************************************************************************/
	public void testDeleteUserInfos() {
		
		UserInfoDto userInfoCriteria = new UserInfoDto();
		
		List<UserInfo> userInfos = userInfoDao.findUserInfos(userInfoCriteria);
		assert(userInfos.size() > 1);
		assert(userInfos.get(0).getUserAuthentication() != null);
		assert(userInfos.get(0).getRoles().size() > 0);
		assert(userInfos.get(1).getUserAuthentication() != null);
		assert(userInfos.get(1).getRoles().size() > 0);
		
		List<Long>userInfoIds = new ArrayList<Long>();
		
		userInfoIds.add(userInfos.get(0).getId());
		userInfoIds.add(userInfos.get(1).getId());
		userInfoDao.deleteUserInfos(userInfoIds);
		
		Long cnt = userInfoDao.countUserInfos(userInfoCriteria);
		assert((cnt + 2) == userInfos.size());
		
		UserAuthentication userAuthentication = userAuthenticationDao.findUserAuthentication(userInfos.get(0).getUserAuthentication().getId());
		assert(userAuthentication == null);
		
		Role role = roleDao.findRole(userInfos.get(0).getRoles().get(0).getId());
		assert(role != null);
	}

	@Test
	public void testRemoveUserInfos() {
		UserInfoDto userInfoCriteria = new UserInfoDto();
		
		List<UserInfo> userInfos = userInfoDao.findUserInfos(userInfoCriteria);
		assert(userInfos.size() > 1);
		assert(userInfos.get(0).getUserAuthentication() != null);
		assert(userInfos.get(0).getRoles().size() > 0);
		assert(userInfos.get(1).getUserAuthentication() != null);
		assert(userInfos.get(1).getRoles().size() > 0);
		
		List<Long>userInfoIds = new ArrayList<Long>();
		
		userInfoIds.add(userInfos.get(0).getId());
		userInfoIds.add(userInfos.get(1).getId());
		userInfoDao.removeUserInfos(userInfoIds);
		
		Long cnt = userInfoDao.countUserInfos(userInfoCriteria);
		assert((cnt + 2) == userInfos.size());
		
		UserAuthentication userAuthentication = userAuthenticationDao.findUserAuthentication(userInfos.get(0).getUserAuthentication().getId());
		assert(userAuthentication == null);
		
		Role role = roleDao.findRole(userInfos.get(0).getRoles().get(0).getId());
		assert(role != null);
	}


	@Test
	public void testSaveUserInfo() {
		UserInfoDto userInfoCriteria = new UserInfoDto();
		
		Long original = userInfoDao.countUserInfos(userInfoCriteria);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Sean");
		userInfo.setLastName("Dunn");
		userInfo.setEmailAddress("sean.dunn@yahoo.com");
		
		UserAuthentication userAuthentication = new UserAuthentication();
		userAuthentication.setLogin("sean.dunn");
		userAuthentication.setPassword("guest");
		userAuthentication.setIsLocked(false);
		userAuthentication.setLoginAttempts(0);
		userAuthentication.setLastLoginDate(now);
		userAuthentication.setPasswordModifiedDate(now);
		userAuthentication.setPasswordExpireDate(null);
		userAuthentication.setStatus(AfwConstants.USER_STATUS_PENDING);
		
		userInfo.setUserAuthentication(userAuthentication);
		userAuthentication.setUserInfo(userInfo);
		
		RoleDto roleCriteria = new RoleDto();
		roleCriteria.setName("ADMIN");
		List<Role> roles = roleDao.findRoles(roleCriteria);
		userInfo.setRoles(roles);
		
		userInfo = userInfoDao.saveUserInfo(userInfo);
		
		Long count = userInfoDao.countUserInfos(userInfoCriteria);
		assert((original + 1) == count);
		
		userInfoCriteria.setEmailAddress("sean.dunn@yahoo.com");
		List<UserInfo> userInfos = userInfoDao.findUserInfos(userInfoCriteria);
		
		assert(userInfos.size() > 0);
		assert(userInfos.get(0).getUserAuthentication() != null);
		assert(userInfos.get(0).getUserAuthentication().getLogin().equalsIgnoreCase("sean.dunn"));
		assert(userInfos.get(0).getRoles().size() > 0);
		assert(userInfos.get(0).getRoles().get(0).getName().equals("ADMIN"));
	}
	
	@Test
	public void testSaveUserInfos() {
		UserInfoDto userInfoCriteria = new UserInfoDto();
		
		Long original = userInfoDao.countUserInfos(userInfoCriteria);
		
		UserInfo userInfo1 = new UserInfo();
		userInfo1.setFirstName("Sean");
		userInfo1.setLastName("Dunn");
		userInfo1.setEmailAddress("sean.dunn@yahoo.com");
		
		UserAuthentication userAuthentication1 = new UserAuthentication();
		userAuthentication1.setLogin("sean.dunn");
		userAuthentication1.setPassword("guest");
		userAuthentication1.setIsLocked(false);
		userAuthentication1.setLoginAttempts(0);
		userAuthentication1.setLastLoginDate(now);
		userAuthentication1.setPasswordModifiedDate(now);
		userAuthentication1.setPasswordExpireDate(null);
		userAuthentication1.setStatus(AfwConstants.USER_STATUS_PENDING);
		
		// Create userInfoDto1 with no roles
		userInfo1.setUserAuthentication(userAuthentication1);
		userAuthentication1.setUserInfo(userInfo1);
		
		UserInfo userInfo2 = new UserInfo();
		userInfo2.setFirstName("John");
		userInfo2.setLastName("Smith");
		userInfo2.setEmailAddress("john.smith@yahoo.com");
		
		UserAuthentication userAuthentication2 = new UserAuthentication();
		userAuthentication2.setLogin("john.smith");
		userAuthentication2.setPassword("guest");
		userAuthentication2.setIsLocked(false);
		userAuthentication2.setLoginAttempts(0);
		userAuthentication2.setLastLoginDate(now);
		userAuthentication2.setPasswordModifiedDate(now);
		userAuthentication2.setPasswordExpireDate(null);
		userAuthentication2.setStatus(AfwConstants.USER_STATUS_PENDING);

		RoleDto roleCriteria = new RoleDto();
		roleCriteria.setName("ADMIN");
		List<Role> roles = roleDao.findRoles(roleCriteria);
		
		userInfo2.setUserAuthentication(userAuthentication2);
		userAuthentication2.setUserInfo(userInfo2);
		userInfo2.setRoles(roles);
		
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		userInfos.add(userInfo1);
		userInfos.add(userInfo2);
		
		userInfoDao.saveUserInfos(userInfos);
		
		Long count = userInfoDao.countUserInfos(userInfoCriteria);
		assertTrue((original + 2) == count);
		
		userInfoCriteria.setEmailAddress("sean.dunn@yahoo.com");
		userInfos = userInfoDao.findUserInfos(userInfoCriteria);
		
		assert(userInfos.size() > 0);
		assert(userInfos.get(0).getUserAuthentication() != null);
		assert(userInfos.get(0).getUserAuthentication().getLogin().equalsIgnoreCase("sean.dunn"));
		assert(userInfos.get(0).getRoles().size() == 0);
		
		userInfoCriteria.setEmailAddress("john.smith@yahoo.com");
		userInfos = userInfoDao.findUserInfos(userInfoCriteria);
		
		assert(userInfos.size() > 0);
		assert(userInfos.get(0).getUserAuthentication() != null);
		assert(userInfos.get(0).getUserAuthentication().getLogin().equalsIgnoreCase("john.smith"));
		assert(userInfos.get(0).getRoles().size() > 0);
		assert(userInfos.get(0).getRoles().get(0).getName().equals("ADMIN"));
	}


}
