package com.cldbiz.angularSpring.controller.ui;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cldbiz.angularSpring.common.AfwConstants;
import com.cldbiz.angularSpring.common.JsonException;
import com.cldbiz.angularSpring.common.JsonFailure;
import com.cldbiz.angularSpring.common.JsonResponse;
import com.cldbiz.angularSpring.controller.BaseController;
import com.cldbiz.angularSpring.dto.UserProfileDto;
import com.cldbiz.angularSpring.service.UserProfileService;
import com.cldbiz.angularSpring.service.cache.AppCodeCacheService;



@RestController
@RequestMapping("/rest/ui/login")
public class LoginUiController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LoginUiController.class);
	
	@Autowired
	AppCodeCacheService appCodeCacheService;

	@Autowired
	UserProfileService userProfileService;

	@RequestMapping(value="/pageConfig", method=RequestMethod.POST)
	public JsonResponse pageConfig(HttpServletRequest request, @RequestBody UserProfileDto userProfileDto) throws JsonFailure {
		String greeting = "Hello " + userProfileDto.getFirstName();
		logger.debug("This is a debugger message from LoginUIController");
		
		try {
			List<UserProfileDto> userProfileDtos = userProfileService.findAll();
			
			// throw new Exception(messagSource.getMessage("ui.demo.test.msg"));
			return JsonResponse.getBuilder(JsonResponse.Status.SUCCESS)
					.data("greeting", greeting)
					.data("userProfileDtos", userProfileDtos)
					.data("spring.autoconfigure.exclude", appEnv.getAutoconfigureExclude())
					.data("prodMode", appCodeCacheService.findById(1L))
					.data("devMode", appCodeCacheService.findById(2L))
					.data("testMode", appCodeCacheService.findById(3L))
					.data("execMode", appCodeCacheService.findByType(AfwConstants.EXECUTION_MODE))
					.data("devTypeCode", appCodeCacheService.findByTypeAndCode(AfwConstants.EXECUTION_MODE, AfwConstants.DEVELOPMENT_MODE))
					.built();
		}
		catch(Exception ex) {
			throw new JsonFailure(new JsonException(ex));
		}
	}
	
	@RequestMapping(value="/user")
	public Principal user(Principal user) throws JsonFailure {
		return user;
	}

}
