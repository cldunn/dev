package com.cldbiz.angularSpring.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

public class JsonResponse extends ModelMap {
	public static final String STATUS_KEY = "status";
	public static final String I18N_KEY = "i18n";
	public static final String PERMISSION_KEY = "permissions";
	public static final String EXCEPTION_KEY = "exception";
	public static final String MESSAGE_KEY = "message";
	public static final String DATA_KEY = "data";
	
	public enum Status {
		SUCCESS, FAILURE, EXCEPTION;
		
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	private JsonResponse() {
		super();
		this.put(JsonResponse.STATUS_KEY, null);
		this.put(JsonResponse.MESSAGE_KEY, null);
		this.put(JsonResponse.EXCEPTION_KEY, null);
		this.put(JsonResponse.I18N_KEY, new HashMap<String, String>());
		this.put(JsonResponse.PERMISSION_KEY, new ArrayList<String>());
		this.put(JsonResponse.DATA_KEY, new HashMap<String, Object>());
	}
	
	private void setStatus(Status status) {
		this.put(JsonResponse.STATUS_KEY, status);
	}
	
	private void setI18n(Map<String, String> i18n) {
		this.put(JsonResponse.I18N_KEY, i18n);
	}
	
	private void setPermissions(List<String> permissions) {
		this.put(JsonResponse.PERMISSION_KEY, permissions);
	}

	private void setMessage(JsonMessage msg) {
		this.put(JsonResponse.MESSAGE_KEY, msg);
	}

	private void setException(JsonException ex) {
		this.put(JsonResponse.EXCEPTION_KEY, ex);
	}

	private Map<String, Object> getData() {
		return (Map<String, Object>) this.get(JsonResponse.DATA_KEY);
	}
	
	public static Builder getBuilder(Status status) {
		return new Builder(status);
	}
	
	public static Builder getBuilder(Status status, JsonMessage msg) {
		return new Builder(status, msg);
	}

	public static Builder getBuilder(Status status, JsonMessage msg, JsonException ex) {
		return new Builder(status, msg, ex);
	}

	public static Builder getBuilder(Status status, Map<String, String> i18n, List<String> permissions) {
		return new Builder(status, i18n, permissions);
	}

	public static class Builder {
		private JsonResponse jsonResponse;
		
		public Builder(Status status) {
			jsonResponse = new JsonResponse();
			jsonResponse.setStatus(status);
		}
		
		public Builder(Status status, JsonMessage msg) {
			jsonResponse = new JsonResponse();
			jsonResponse.setStatus(status);
			jsonResponse.setMessage(msg);
		}

		public Builder(Status status, JsonMessage msg, JsonException ex) {
			jsonResponse = new JsonResponse();
			jsonResponse.setStatus(status);
			jsonResponse.setMessage(msg);
			jsonResponse.setException(ex);
		}

		public Builder(Status status, Map<String, String> i18n, List<String> permissions) {
			jsonResponse = new JsonResponse();
			jsonResponse.setStatus(status);
			jsonResponse.setI18n(i18n);
			jsonResponse.setPermissions(permissions);
		}

		public Builder data(String key, Object value) {
			jsonResponse.getData().put(key, value);
			return this;
		}
		
		public JsonResponse built() {
			return jsonResponse;
		}
		
	}

}
