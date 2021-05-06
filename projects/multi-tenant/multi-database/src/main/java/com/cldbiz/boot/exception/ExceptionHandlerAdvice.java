package com.cldbiz.boot.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cldbiz.boot.common.JsonResponseModelAndView;


@ControllerAdvice
public class ExceptionHandlerAdvice {

	   public static final String DEFAULT_ERROR_VIEW = "error";

	   /*
	   @ExceptionHandler(NoHandlerFoundException.class)
	   public JsonResponseModelAndView handleNotFoundException(Exception ex, HttpServletRequest req) {
		   
	   }
	   */
	   
	   @ExceptionHandler(value = {Exception.class, RuntimeException.class})
	   public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
	            ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

	        mav.addObject("datetime", new Date());
	        mav.addObject("exception", e);
	        mav.addObject("url", request.getRequestURL());
	        return mav;
	   }
}
