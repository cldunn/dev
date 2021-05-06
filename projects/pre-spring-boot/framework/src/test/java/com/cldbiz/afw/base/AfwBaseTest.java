package com.cldbiz.afw.base;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cldbiz.afw.config.AfwConfig;
import com.cldbiz.afw.config.AfwExecutionContext;
import com.cldbiz.afw.config.TstConfig;
import com.cldbiz.afw.dto.UserInfoDto;
import com.cldbiz.afw.security.AfwPrincipal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
	@ContextConfiguration(classes = TstConfig.class),
	@ContextConfiguration(classes = AfwConfig.class)
	
})
@Transactional
@Rollback(true)
public abstract class AfwBaseTest extends DBUnitBaseTest {

	@PostConstruct
	public void initAfwBaseTest() {
		AfwExecutionContext.setUserInfoDto(getUserInfoDto());
		AfwExecutionContext.setDsKey(testProperties.getProperty("ds.test"));
	}
	
	private UserInfoDto getUserInfoDto() {
		UserInfoDto userInfoDto = new UserInfoDto();
		
		userInfoDto.setId(Long.valueOf(testProperties.getProperty("test.userInfo.id")));
		userInfoDto.setFirstName(testProperties.getProperty("test.userInfo.firstName"));
		userInfoDto.setLastName(testProperties.getProperty("test.userInfo.lastName"));
		userInfoDto.setLocale(new Locale(testProperties.getProperty("test.userInfo.localeLanguage"), testProperties.getProperty("test.userInfo.localeCountry")));

		userInfoDto.setPrincipal(new AfwPrincipal(userInfoDto.getId(), userInfoDto.getEmailAddress()));
		
		return userInfoDto;
	}
}
