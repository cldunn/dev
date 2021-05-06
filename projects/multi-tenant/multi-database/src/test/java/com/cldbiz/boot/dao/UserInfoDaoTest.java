package com.cldbiz.boot.dao;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.cldbiz.boot.BootApplication;
import com.cldbiz.boot.base.BaseTest;
import com.cldbiz.boot.common.BootConstants;
import com.cldbiz.boot.domain.Role;
import com.cldbiz.boot.domain.UserInfo;
import com.cldbiz.boot.dto.PageReqDto;
import com.cldbiz.boot.dto.RoleDto;
import com.cldbiz.boot.dto.UserProfileDto;


public class UserInfoDaoTest extends BaseTest {
	private static final Logger log = LoggerFactory.getLogger(UserInfoDaoTest.class);
	private static Timestamp now;
	
	@Resource
	private UserInfoDao userInfoDao;

	@Resource
	private RoleDao roleDao;
	
	@BeforeClass
	public static void setUpBeforeTests() {
		refresh("com/cldbiz/boot/data/uniqueid.xml");
		refresh("com/cldbiz/boot/data/product.xml");
		refresh("com/cldbiz/boot/data/asset.xml");
		refresh("com/cldbiz/boot/data/role.xml");
		refresh("com/cldbiz/boot/data/roleAsset.xml");
		cleanInsert("com/cldbiz/boot/data/userInfo.xml");
		cleanInsert("com/cldbiz/boot/data/userInfoRole.xml");
		now = new Timestamp(new Date().getTime());
	}

	@AfterClass
	public static void cleanAfterTests() {
		deleteAll("com/cldbiz/boot/data/userInfoRole.xml");
		deleteAll("com/cldbiz/boot/data/userInfo.xml");
		delete("com/cldbiz/boot/data/roleAsset.xml");
		delete("com/cldbiz/boot/data/asset.xml");
		delete("com/cldbiz/boot/data/role.xml");
		delete("com/cldbiz/boot/data/product.xml");
		delete("com/cldbiz/boot/data/uniqueid.xml");
	}

	@Test
	public void testGetUserInfo() {
		UserInfo userInfo = userInfoDao.findUserInfo(1L);
		
		assertNotNull(userInfo);
		assertTrue(userInfo.getRoles().size() > 0);
	}

	@Test
	public void testIsDuplicate() {
		UserProfileDto userProfileDto = new UserProfileDto();

		userProfileDto.setEmailAddress("cliffdunntx@yahoo.com");
		
		boolean isDup = userInfoDao.isDuplicateUserInfo(userProfileDto);
		assertTrue(isDup);
		
		UserInfo userInfo = userInfoDao.findUserInfo(1L);
		userProfileDto = new UserProfileDto(userInfo);
		
		isDup = userInfoDao.isDuplicateUserInfo(userProfileDto);
		assertFalse(isDup);
	}

	@Test
	public void testCountUserInfos() {
		UserProfileDto userProfileDto = new UserProfileDto();

		userProfileDto.setLastName("Dunn");
		Long userInfoCnt = userInfoDao.countUserInfos(userProfileDto);
		
		assertTrue(userInfoCnt > 0);
		
		userProfileDto.setLastName("Du");
		userInfoCnt = userInfoDao.countUserInfos(userProfileDto);
		
		assertTrue(userInfoCnt == 0);
	}
	
	@Test
	public void testFindUserInfos() {
		UserProfileDto userProfileDto = new UserProfileDto();

		userProfileDto.setEmailAddress("cliffdunntx@yahoo.com");
		List<UserInfo> userInfos = userInfoDao.findUserInfos(userProfileDto);
		
		assertTrue(userInfos.size() > 0);
		assertTrue(userInfos.get(0).getRoles().size() > 0);
		
		userProfileDto.setEmailAddress("@yahoo.com");
		userInfos = userInfoDao.findUserInfos(userProfileDto);
		
		assertTrue(userInfos.size() == 0);
	}

	@Test
	public void testSearchUserInfos() {
		UserProfileDto userInfoCriteria = new UserProfileDto();

		userInfoCriteria.setEmailAddress("cliffdunntx@yahoo.com");
		List<UserInfo> userInfos = userInfoDao.searchUserInfos(userInfoCriteria);
		
		assertTrue(userInfos.size() > 0);
		assertTrue(userInfos.get(0).getRoles().size() > 0);
		
		userInfoCriteria.setEmailAddress("@yahoo.com");
		userInfos = userInfoDao.searchUserInfos(userInfoCriteria);
		
		assertTrue(userInfos.size() > 0);
		assertTrue(userInfos.get(0).getRoles().size() > 0);
	}

	@Test
	public void testSearchUserInfosPage() {
		PageReqDto<UserProfileDto> pageReqDto = new PageReqDto<UserProfileDto>(0L, 1L);
		UserProfileDto userProfileDto = new UserProfileDto();

		userProfileDto.setLastName("Dunn");
		pageReqDto.setCriteria(userProfileDto);
		List<UserInfo> userInfos = userInfoDao.searchUserInfosPage(pageReqDto);
		
		assertTrue(userInfos.size() == 1);
		assertTrue(userInfos.get(0).getRoles().size() > 0);
	}
	
	@Test
	public void testRemoveUserInfos() {
		UserProfileDto userProfileDto = new UserProfileDto();
		
		List<UserInfo> userInfos = userInfoDao.findUserInfos(userProfileDto);
		assert(userInfos.size() > 1);
		assert(userInfos.get(0).getRoles().size() > 0);
		assert(userInfos.get(1).getRoles().size() > 0);
		
		List<Long>userInfoIds = new ArrayList<Long>();
		
		userInfoIds.add(userInfos.get(0).getId());
		userInfoIds.add(userInfos.get(1).getId());
		userInfoDao.removeUserInfos(userInfoIds);
		
		Long cnt = userInfoDao.countUserInfos(userProfileDto);
		assert((cnt + 2) == userInfos.size());
		
		Role role = roleDao.findRole(userInfos.get(0).getRoles().get(0).getId());
		assert(role != null);
	}

	@Test
	public void testSaveUserInfo() {
		UserProfileDto userProfileDto = new UserProfileDto();
		
		Long original = userInfoDao.countUserInfos(userProfileDto);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Sean");
		userInfo.setLastName("Dunn");
		userInfo.setEmailAddress("sean.dunn@yahoo.com");
		
		userInfo.setLogin("sean.dunn");
		userInfo.setPassword("guest");
		userInfo.setIsLocked(false);
		userInfo.setLoginAttempts(0);
		userInfo.setLastLoginDate(now);
		userInfo.setPasswordModifiedDate(now);
		userInfo.setPasswordExpireDate(null);
		userInfo.setStatus(BootConstants.USER_STATUS_PENDING);
		
		RoleDto roleDto = new RoleDto();
		roleDto.setName("ADMIN");
		List<Role> roles = roleDao.findRoles(roleDto);
		
		userInfo.setRoles(roles);
		
		userInfo = userInfoDao.saveUserInfo(userInfo);
		
		Long count = userInfoDao.countUserInfos(userProfileDto);
		assert((original + 1) == count);
		
		userProfileDto.setEmailAddress("sean.dunn@yahoo.com");
		List<UserInfo> userInfos = userInfoDao.findUserInfos(userProfileDto);
		
		assert(userInfos.size() > 0);
		
		assert(userInfos.get(0).getRoles().size() > 0);
		assert(userInfos.get(0).getRoles().get(0).getName().equals("ADMIN"));
	}
	

	@Test
	public void testSaveUserInfos() {
		UserProfileDto userProfileDto = new UserProfileDto();
		
		Long original = userInfoDao.countUserInfos(userProfileDto);
		
		UserInfo userInfo1 = new UserInfo();
		userInfo1.setFirstName("Sean");
		userInfo1.setLastName("Dunn");
		userInfo1.setEmailAddress("sean.dunn@yahoo.com");
		
		userInfo1.setLogin("sean.dunn");
		userInfo1.setPassword("guest");
		userInfo1.setIsLocked(false);
		userInfo1.setLoginAttempts(0);
		userInfo1.setLastLoginDate(now);
		userInfo1.setPasswordModifiedDate(now);
		userInfo1.setPasswordExpireDate(null);
		userInfo1.setStatus(BootConstants.USER_STATUS_PENDING);
		
		UserInfo userInfo2 = new UserInfo();
		userInfo2.setFirstName("John");
		userInfo2.setLastName("Smith");
		userInfo2.setEmailAddress("john.smith@yahoo.com");
		
		userInfo2.setLogin("john.smith");
		userInfo2.setPassword("guest");
		userInfo2.setIsLocked(false);
		userInfo2.setLoginAttempts(0);
		userInfo2.setLastLoginDate(now);
		userInfo2.setPasswordModifiedDate(now);
		userInfo2.setPasswordExpireDate(null);
		userInfo2.setStatus(BootConstants.USER_STATUS_PENDING);

		RoleDto roleCriteria = new RoleDto();
		roleCriteria.setName("ADMIN");
		List<Role> roles = roleDao.findRoles(roleCriteria);
		
		userInfo2.setRoles(roles);
		
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		userInfos.add(userInfo1);
		userInfos.add(userInfo2);
		
		userInfoDao.saveUserInfos(userInfos);
		
		Long count = userInfoDao.countUserInfos(userProfileDto);
		assertTrue((original + 2) == count);
		
		userProfileDto.setEmailAddress("sean.dunn@yahoo.com");
		userInfos = userInfoDao.findUserInfos(userProfileDto);
		
		assert(userInfos.size() > 0);
		assert(userInfos.get(0) != null);
		assert(userInfos.get(0).getLogin().equalsIgnoreCase("sean.dunn"));
		assert(userInfos.get(0).getRoles().size() == 0);
		
		userProfileDto.setEmailAddress("john.smith@yahoo.com");
		userInfos = userInfoDao.findUserInfos(userProfileDto);
		
		assert(userInfos.size() > 0);
		assert(userInfos.get(0) != null);
		assert(userInfos.get(0).getLogin().equalsIgnoreCase("john.smith"));
		assert(userInfos.get(0).getRoles().size() > 0);
		assert(userInfos.get(0).getRoles().get(0).getName().equals("ADMIN"));
	}


}
