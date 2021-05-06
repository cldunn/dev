package com.cldbiz.afw.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

public class JsonResponseModelAndView extends ModelAndView {
	private Map<String, Object> model = this.getModel();
	
	public static final String STATUS_KEY = "status";
	public static final String I18N_KEY = "i18n";
	public static final String PERMISSION_KEY = "permissions";
	public static final String MESSAGE_KEY = "message";
	public static final String DATA_KEY = "data";
	
	public enum Status {
		SUCCESS, FAILURE;
		
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	public class Message {
		private String header;
		private List<String> details = new ArrayList<String>();
		
		public Message(String header) {
			this.header = header;
		}
		
		public Message(String header, List<String> details) {
			this.header = header;
			this.details = details;
		}
		
		public String getHeader() {
			return header;
		}
		
		public void setHeader(String header) {
			this.header = header;
		}
		
		public List<String> getDetails() {
			return details;
		}
		
		public void setDetails(List<String> details) {
			this.details = details;
		}
	}
	
	private JsonResponseModelAndView() {
		super();
		super.setView(null);
		super.setViewName(null);
		model.put(JsonResponseModelAndView.STATUS_KEY, null);
		model.put(JsonResponseModelAndView.I18N_KEY, new HashMap<String, String>());
		model.put(JsonResponseModelAndView.PERMISSION_KEY, new ArrayList<String>());
		model.put(JsonResponseModelAndView.MESSAGE_KEY, null);
		model.put(JsonResponseModelAndView.DATA_KEY, new HashMap<String, Object>());
	}
	
	private void setStatus(Status status) {
		model.put(JsonResponseModelAndView.STATUS_KEY, status);
	}
	
	private void setI18n(Map<String, String> i18n) {
		model.put(JsonResponseModelAndView.I18N_KEY, i18n);
	}
	
	private void setPermissions(List<String> permissions) {
		model.put(JsonResponseModelAndView.PERMISSION_KEY, permissions);
	}

	private void setMessage(String header) {
		model.put(JsonResponseModelAndView.MESSAGE_KEY, new Message(header));
	}

	private void setMessage(String header, String detail) {
		model.put(JsonResponseModelAndView.MESSAGE_KEY, new Message(header, Arrays.asList(detail)));
	}

	private void setMessage(String header, List<String> details) {
		model.put(JsonResponseModelAndView.MESSAGE_KEY, details);
	}
	
	private Map<String, Object> getData() {
		return (Map<String, Object>) model.get(JsonResponseModelAndView.DATA_KEY);
	}
	
	public static Builder getBuilder(Status status, Map<String, String> i18n, List<String> permissions) {
		return new Builder(status, i18n, permissions);
	}

	public static Builder getBuilder(Status status, String header) {
		return new Builder(status, header);
	}

	public static Builder getBuilder(Status status, String header, String detail) {
		return new Builder(status, header, detail);
	}

	public static Builder getBuilder(Status status, String header, List<String> details) {
		return new Builder(status, header, details);
	}

	public static Builder getBuilder(Status status) {
		return new Builder(status);
	}
	
	public static class Builder {
		private JsonResponseModelAndView jsonResponseModelAndView;
		
		public Builder(Status status, Map<String, String> i18n, List<String> permissions) {
			jsonResponseModelAndView = new JsonResponseModelAndView();
			jsonResponseModelAndView.setStatus(status);
			jsonResponseModelAndView.setI18n(i18n);
			jsonResponseModelAndView.setPermissions(permissions);
		}

		public Builder(Status status, String header) {
			jsonResponseModelAndView = new JsonResponseModelAndView();
			jsonResponseModelAndView.setStatus(status);
			jsonResponseModelAndView.setMessage(header);
		}

		public Builder(Status status, String header, String detail) {
			jsonResponseModelAndView = new JsonResponseModelAndView();
			jsonResponseModelAndView.setStatus(status);
			jsonResponseModelAndView.setMessage(header);
			jsonResponseModelAndView.setMessage(header, Arrays.asList(detail));
		}

		public Builder(Status status, String header, List<String> details) {
			jsonResponseModelAndView = new JsonResponseModelAndView();
			jsonResponseModelAndView.setStatus(status);
			jsonResponseModelAndView.setMessage(header, details);
		}

		public Builder(Status status) {
			jsonResponseModelAndView = new JsonResponseModelAndView();
			jsonResponseModelAndView.setStatus(status);
		}
		
		public Builder data(String key, Object value) {
			jsonResponseModelAndView.getData().put(key, value);
			return this;
		}
		
		public Builder view(View view) {
			jsonResponseModelAndView.setView(view);
			return this;
		}

		public Builder viewName(String viewName) {
			jsonResponseModelAndView.setViewName(viewName);
			return this;
		}

		public JsonResponseModelAndView built() {
			return jsonResponseModelAndView;
		}
	}
}
