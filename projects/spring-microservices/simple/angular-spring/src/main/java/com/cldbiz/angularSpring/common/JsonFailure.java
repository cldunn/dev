package com.cldbiz.angularSpring.common;

public class JsonFailure extends RuntimeException {
	private JsonMessage msg;
	private JsonException ex;
	private JsonResponse.Status status;
	
	public JsonFailure(JsonMessage msg) {
		this.status = JsonResponse.Status.FAILURE;
		this.msg = msg;
	}

	public JsonFailure(JsonException ex) {
		this.status = JsonResponse.Status.EXCEPTION;
		this.msg = new JsonMessage(ex.getCause().getMessage());
		this.ex = ex;
	}

	public JsonFailure(JsonMessage msg, JsonException ex) {
		this.status = JsonResponse.Status.EXCEPTION;
		this.msg = msg;
		this.ex = ex;
	}

	public JsonMessage getMsg() {
		return msg;
	}

	public JsonException getEx() {
		return ex;
	}

	public JsonResponse.Status getStatus() {
		return status;
	}
}
