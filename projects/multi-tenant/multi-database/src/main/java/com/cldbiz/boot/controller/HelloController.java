package com.cldbiz.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cldbiz.boot.common.JsonResponseModelAndView;

@RestController
public class HelloController {
    
    @ResponseBody
    @RequestMapping("/errPg")
    public String errPg() throws Exception  {
    	throw new NullPointerException("My Bad");
    }

    @ResponseBody
    @RequestMapping("/greetings")
    public String greetings() throws Exception  {
    	
    	return "Greetings from cldbiz boot!";
    }
    
    /*
    @ResponseBody
    @RequestMapping(value = "/api/greetings", method=RequestMethod.GET, produces="application/json")
    public String greeting() {
        return "Howdy from cldbiz boot!";
    }
    */
    
    @ResponseBody
    @RequestMapping(value = "/api/greetings", method=RequestMethod.GET, produces="application/json")
    public JsonResponseModelAndView apiGreetings() {
    	JsonResponseModelAndView result =
    			JsonResponseModelAndView.getBuilder(JsonResponseModelAndView.Status.SUCCESS).data("msg", "Howdy from cldbiz boot!").built();
    	return result;
    }
	
}

