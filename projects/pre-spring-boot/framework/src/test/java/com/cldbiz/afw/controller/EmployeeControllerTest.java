package com.cldbiz.afw.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;

import com.cldbiz.afw.base.AfwWebTest;
import com.cldbiz.afw.common.AfwConstants;
import com.cldbiz.afw.common.JsonResponseModelAndView;
import com.cldbiz.afw.config.AfwExecutionContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerTest extends AfwWebTest {

	@Test
	public void employeeInitTest() throws Exception {
		ModelMap modelMap = mockMvc
				.perform(post("/employee/inits").header("host", "localhost:80")
						.sessionAttr(AfwConstants.USER_INFO, AfwExecutionContext.getUserInfoDto())
						.contentType(MediaType.APPLICATION_JSON)
						.content(""))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn().getModelAndView().getModelMap();
		
		assert(JsonResponseModelAndView.Status.SUCCESS.equals(modelMap.get(JsonResponseModelAndView.STATUS_KEY)));
	}
}
