package com.cldbiz.afw.config;

import org.springframework.stereotype.Component;

import com.cldbiz.afw.dto.UserInfoDto;

@Component
public class AfwExecutionContext {

	private static final ThreadLocal<AfwContext> afwContext =
        new ThreadLocal<AfwContext>() {
            @Override protected AfwContext initialValue() {
                return new AfwContext();
        }
    };

	public static String getDsKey() {
		return afwContext.get().getDsKey();
	}

	public static void setDsKey(String dsKey) {
		afwContext.get().setDsKey(dsKey);
	}
	
	public static UserInfoDto getUserInfoDto() {
		return afwContext.get().getUserInfoDto();
	}

	public static void setUserInfoDto(UserInfoDto UserInfoDto) {
		afwContext.get().setUserInfoDto(UserInfoDto);
	}

	public static void remove() {
		afwContext.remove();
	}
}
