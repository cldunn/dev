package com.cldbiz.boot.base;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cldbiz.boot.BootApplication;
import com.cldbiz.boot.config.BootExecutionContext;
import com.cldbiz.boot.dto.UserProfileDto;
import com.cldbiz.boot.security.BootPrincipal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public abstract class BaseTest extends DBUnitBaseTest {

	@PostConstruct
	public void initBootBaseTest() {
		BootExecutionContext.setUserProfileDto(getUserProfileDto());
		BootExecutionContext.setDsKey(testProperties.getProperty("ds.test"));
	}
	
	private UserProfileDto getUserProfileDto() {
		UserProfileDto userProfileDto = new UserProfileDto();
		
		userProfileDto.setId(Long.valueOf(testProperties.getProperty("test.userInfo.id")));
		userProfileDto.setFirstName(testProperties.getProperty("test.userInfo.firstName"));
		userProfileDto.setLastName(testProperties.getProperty("test.userInfo.lastName"));
		userProfileDto.setLocale(new Locale(testProperties.getProperty("test.userInfo.localeLanguage"), testProperties.getProperty("test.userInfo.localeCountry")));

		userProfileDto.setPrincipal(new BootPrincipal(userProfileDto.getId(), userProfileDto.getEmailAddress()));
		
		return userProfileDto;
	}
}
