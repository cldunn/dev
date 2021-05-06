package com.cldbiz.angularSpring.common;

import java.util.ArrayList;
import java.util.List;

public class JsonMessage {
	private String text;
	private List<String> details = new ArrayList<String>();
	
	public JsonMessage(String text) {
		this.text = text;
	}
	
	public JsonMessage(String header, List<String> details) {
		this.text = header;
		this.details = details;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String header) {
		this.text = header;
	}
	
	public List<String> getDetails() {
		return details;
	}
	
	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	public void addDetail(String detail) {
		this.details.add(detail);
	}
}

