package com.cldbiz.angularSpring.common;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.cldbiz.angularSpring.dto.UserProfileDto;

@Component
public class AfwExecContext {

	private static final ThreadLocal<AfwContext> afwContext =
        new ThreadLocal<AfwContext>() {
            @Override protected AfwContext initialValue() {
                return new AfwContext();
	        }
	    };

    public static Locale getLocale() {
		return afwContext.get().getLocale();
	}

	public static void setLocale(Locale locale) {
		afwContext.get().setLocale(locale);
	}

	public static String getDsKey() {
		return afwContext.get().getDsKey();
	}

	public static void setDsKey(String dsKey) {
		afwContext.get().setDsKey(dsKey);
	}
	
	public static UserProfileDto getUserProfileDto() {
		return afwContext.get().getUserProfileDto();
	}

	public static void setUserProfileDto(UserProfileDto userProfileDto) {
		afwContext.get().setUserProfileDto(userProfileDto);
	}

	public static void remove() {
		afwContext.remove();
	}
}
