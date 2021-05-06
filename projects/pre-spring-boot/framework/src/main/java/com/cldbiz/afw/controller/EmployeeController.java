package com.cldbiz.afw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cldbiz.afw.common.JsonResponseModelAndView;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends AfwBaseController {

	@ResponseBody
	@RequestMapping(value="/inits", method=RequestMethod.POST)
	public JsonResponseModelAndView pageConfig() {
		return JsonResponseModelAndView.getBuilder(JsonResponseModelAndView.Status.SUCCESS).built();
	}
	
}
