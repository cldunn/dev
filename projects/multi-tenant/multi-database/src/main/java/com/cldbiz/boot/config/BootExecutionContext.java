package com.cldbiz.boot.config;

import org.springframework.stereotype.Component;

import com.cldbiz.boot.dto.UserProfileDto;

// import com.cldbiz.afw.dto.UserInfoDto;

@Component
public class BootExecutionContext {

	private static final ThreadLocal<BootContext> bootContext =
        new ThreadLocal<BootContext>() {
            @Override protected BootContext initialValue() {
                return new BootContext();
        }
    };

	public static String getDsKey() {
		return bootContext.get().getDsKey();
	}

	public static void setDsKey(String dsKey) {
		bootContext.get().setDsKey(dsKey);
	}
	
	public static UserProfileDto getUserProfileDto() {
		return bootContext.get().getUserProfileDto();
	}

	public static void setUserProfileDto(UserProfileDto userProfileDto) {
		bootContext.get().setUserProfileDto(userProfileDto);
	}

	public static void remove() {
		bootContext.remove();
	}
}
