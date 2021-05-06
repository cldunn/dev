package com.cldbiz.angularSpring.aop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cldbiz.angularSpring.common.JsonFailure;
import com.cldbiz.angularSpring.common.JsonResponse;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ResponseBody
	@ExceptionHandler({
		JsonFailure.class
	})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public JsonResponse handleAppException(JsonFailure fail, HttpServletRequest req) {
		if (fail.getStatus().equals(JsonResponse.Status.FAILURE)) {
			return JsonResponse.getBuilder(fail.getStatus(), fail.getMsg()).built(); 
		}
		else {
			return JsonResponse.getBuilder(fail.getStatus(), fail.getMsg(), fail.getEx()).built();
		}
	}
	
}
