package com.cldbiz.angularSpring.common;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class JsonException {
	private Throwable cause;
	private String stackTrace;
	
	public JsonException(Throwable ex) {
		this.cause = ex;
		this.stackTrace = ExceptionUtils.getStackTrace(ex);
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
}
