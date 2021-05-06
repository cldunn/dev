package com.cldbiz.afw.dao;

import static org.junit.Assert.assertFalse;
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
import com.cldbiz.afw.common.AfwConstants;
import com.cldbiz.afw.common.AfwListPredicates;
import com.cldbiz.afw.domain.UserAuthentication;
import com.cldbiz.afw.domain.UserInfo;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.UserAuthenticationDto;
import com.cldbiz.afw.dto.UserInfoDto;

public class UserAuthenticationDaoTest extends AfwBaseTest {
	private static final Logger log = LoggerFactory.getLogger(UserAuthenticationDaoTest.class);
	private static Timestamp now;
	
	@Resource
	private UserAuthenticationDao userAuthenticationDao;
	
	@Resource
	private UserInfoDao userInfoDao;


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
	public void testGetUserAuthentication() {
		UserAuthentication userAuthentication = userAuthenticationDao.findUserAuthentication(1L);
		
		assertNotNull(userAuthentication);
		assertTrue(userAuthentication.getUserInfo() != null);
	}
	
	@Test
	public void testIsDuplicate() {
		UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto();

		userAuthenticationDto.setLogin("cliff.dunn");
		
		boolean isDup = userAuthenticationDao.isDuplicateUserAuthentication(userAuthenticationDto);
		assertTrue(isDup);
		
		UserAuthentication userAuthentication = userAuthenticationDao.findUserAuthentication(1L);
		userAuthenticationDto = new UserAuthenticationDto(userAuthentication);
		
		isDup = userAuthenticationDao.isDuplicateUserAuthentication(userAuthenticationDto);
		assertFalse(isDup);
	}

	@Test
	public void testCountUserAuthentications() {
		UserAuthenticationDto userAuthenticationCriteria = new UserAuthenticationDto();

		userAuthenticationCriteria.setLogin("cliff.dunn");
		Long userAuthenticationCnt = userAuthenticationDao.countUserAuthentications(userAuthenticationCriteria);
		
		assertTrue(userAuthenticationCnt > 0);

		userAuthenticationCriteria.setLogin("cliff");
		userAuthenticationCnt = userAuthenticationDao.countUserAuthentications(userAuthenticationCriteria);
		
		assertTrue(userAuthenticationCnt == 0);
}
	
	@Test
	public void testFindUserAuthentications() {
		UserAuthenticationDto userAuthenticationCriteria = new UserAuthenticationDto();
		
		userAuthenticationCriteria.setLogin("cliff.dunn");
		List<UserAuthentication> userAuthentications = userAuthenticationDao.findUserAuthentications(userAuthenticationCriteria);
		
		assertTrue(userAuthentications.size() > 0);
		assertTrue(userAuthentications.get(0).getUserInfo() != null);
		
		userAuthenticationCriteria.setLogin("cliff");
		userAuthentications = userAuthenticationDao.findUserAuthentications(userAuthenticationCriteria);
		
		assertTrue(userAuthentications.size() == 0);
	}
	
	@Test
	public void testSearchUserAuthentications() {
		UserAuthenticationDto userAuthenticationCriteria = new UserAuthenticationDto();

		userAuthenticationCriteria.setLogin("cliff");
		List<UserAuthentication> userAuthentications = userAuthenticationDao.searchUserAuthentications(userAuthenticationCriteria);
		
		assertTrue(userAuthentications.size() > 0);
		assertTrue(userAuthentications.get(0).getUserInfo() != null);
		
		userAuthenticationCriteria.setLogin(null);
		userAuthenticationCriteria.setStatus(AfwConstants.USER_STATUS_ACTIVE);
		userAuthentications = userAuthenticationDao.searchUserAuthentications(userAuthenticationCriteria);
		
		assertTrue(userAuthentications.size() > 1);
		assertTrue(userAuthentications.get(0).getUserInfo() != null);
	}

	@Test
	public void testSearchUserAuthenticationsPage() {
		PageReqDto<UserAuthenticationDto> pageReqDto = new PageReqDto<UserAuthenticationDto>(0L, 1L);
		UserAuthenticationDto userAuthenticationCriteria = new UserAuthenticationDto();

		userAuthenticationCriteria.setStatus(AfwConstants.USER_STATUS_ACTIVE);
		pageReqDto.setCriteria(userAuthenticationCriteria);
		List<UserAuthentication> userAuthentications = userAuthenticationDao.searchUserAuthenticationsPage(pageReqDto);
		
        assertTrue(userAuthentications.size() == 1);
        assertTrue(userAuthentications.get(0).getUserInfo() != null);
	}

	@Test
	/********************************************************************************************
	Works Because of cascade delete on USER_INFO (owner) AUTHENTICATION_ID
	*********************************************************************************************/
	public void testDeleteUserAuthentication() {
		UserAuthenticationDto userAuthenticationCriteria = new UserAuthenticationDto();
		
		List<UserAuthentication> userAuthentications = userAuthenticationDao.findUserAuthentications(userAuthenticationCriteria);
		assert(userAuthentications.size() > 1);
		assert(userAuthentications.get(0).getUserInfo() != null);
		assert(userAuthentications.get(1).getUserInfo() != null);
		
		List<Long>userAuthenticationIds = new ArrayList<Long>();
		
		userAuthenticationIds.add(userAuthentications.get(0).getId());
		userAuthenticationIds.add(userAuthentications.get(1).getId());
		userAuthenticationDao.deleteUserAuthentications(userAuthenticationIds);
		
		Long cnt = userAuthenticationDao.countUserAuthentications(userAuthenticationCriteria);
		assert((cnt + 2) == userAuthentications.size());
		
		UserInfo userInfo = userInfoDao.findUserInfo(userAuthentications.get(0).getUserInfo().getId());
		assert(userInfo == null);
		
	}
	
	@Test
	public void testRemoveUserAuthentications() {
		UserAuthenticationDto userAuthenticationCriteria = new UserAuthenticationDto();
		
		List<UserAuthentication> userAuthentications = userAuthenticationDao.findUserAuthentications(userAuthenticationCriteria);
		assert(userAuthentications.size() > 1);
		assert(userAuthentications.get(0).getUserInfo() != null);
		assert(userAuthentications.get(1).getUserInfo() != null);
		
		// userAuthentications.get(0).getUserInfo() will be null after removal, save id for test
		Long userInfoId = userAuthentications.get(0).getUserInfo().getId();

		List<Long>userAuthenticationIds = new ArrayList<Long>();
		userAuthenticationIds.add(userAuthentications.get(0).getId());
		userAuthenticationIds.add(userAuthentications.get(1).getId());
		userAuthenticationDao.removeUserAuthentications(userAuthenticationIds);
		
		Long cnt = userAuthenticationDao.countUserAuthentications(userAuthenticationCriteria);
		assert((cnt + 2) == userAuthentications.size());
		
		UserInfo userInfo = userInfoDao.findUserInfo(userInfoId);
		assert(userInfo == null);
	}
	
	@Test
	public void testSaveUserAuthentication() {
		UserAuthenticationDto userAuthenticationCriteria = new UserAuthenticationDto();
		
		Long original = userAuthenticationDao.countUserAuthentications(userAuthenticationCriteria);
		
		UserAuthentication userAuthentication = new UserAuthentication();
		userAuthentication.setLogin("sean.dunn");
		userAuthentication.setPassword("guest");
		userAuthentication.setIsLocked(false);
		userAuthentication.setLoginAttempts(0);
		userAuthentication.setLastLoginDate(now);
		userAuthentication.setPasswordModifiedDate(now);
		userAuthentication.setPasswordExpireDate(null);
		userAuthentication.setStatus(AfwConstants.USER_STATUS_PENDING);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setEmailAddress("seandunntx@yahoo.com");
		
		userAuthentication.setUserInfo(userInfo);
		userInfo.setUserAuthentication(userAuthentication);
		userAuthenticationDao.saveUserAuthentication(userAuthentication);
		
		Long count = userAuthenticationDao.countUserAuthentications(userAuthenticationCriteria);
		assert((original + 1) == count);
		
		userAuthenticationCriteria.setLogin("sean.dunn");
		List<UserAuthentication> userAuthentications = userAuthenticationDao.findUserAuthentications(userAuthenticationCriteria);
		
		assert(userAuthentications.size() == 1);
		assert(userAuthentications.get(0).getUserInfo() != null);
		
	}
	
	@Test
	public void testSaveUserAuthentications() {
		UserAuthenticationDto userAuthenticationCriteria = new UserAuthenticationDto();
		Long original = userAuthenticationDao.countUserAuthentications(userAuthenticationCriteria);

		UserAuthentication userAuthentication1 = new UserAuthentication();
		userAuthentication1.setLogin("sean.dunn");
		userAuthentication1.setPassword("guest");
		userAuthentication1.setIsLocked(false);
		userAuthentication1.setLoginAttempts(0);
		userAuthentication1.setLastLoginDate(now);
		userAuthentication1.setPasswordModifiedDate(now);
		userAuthentication1.setPasswordExpireDate(null);
		userAuthentication1.setStatus(AfwConstants.USER_STATUS_PENDING);
		
		UserInfo userInfo1 = new UserInfo();
		userInfo1.setEmailAddress("sean.dunn@yahoo.com");

		userAuthentication1.setUserInfo(userInfo1);
		userInfo1.setUserAuthentication(userAuthentication1);
		
		UserAuthentication userAuthentication2 = new UserAuthentication();
		userAuthentication2.setLogin("john.smith");
		userAuthentication2.setPassword("guest");
		userAuthentication2.setIsLocked(false);
		userAuthentication2.setLoginAttempts(0);
		userAuthentication2.setLastLoginDate(now);
		userAuthentication2.setPasswordModifiedDate(now);
		userAuthentication2.setPasswordExpireDate(null);
		userAuthentication2.setStatus(AfwConstants.USER_STATUS_PENDING);
		
		UserInfo userInfo2 = new UserInfo();
		userInfo2.setEmailAddress("john.smith@yahoo.com");

		userAuthentication2.setUserInfo(userInfo2);
		userInfo2.setUserAuthentication(userAuthentication2);
		
		List<UserAuthentication> userAuthentications = new ArrayList<UserAuthentication>();
		userAuthentications.add(userAuthentication1);
		userAuthentications.add(userAuthentication2);
		
		userAuthentications = userAuthenticationDao.saveUserAuthentications(userAuthentications);
		
		Long count = userAuthenticationDao.countUserAuthentications(userAuthenticationCriteria);
		assertTrue((original + 2) == count);
	}
	

}
