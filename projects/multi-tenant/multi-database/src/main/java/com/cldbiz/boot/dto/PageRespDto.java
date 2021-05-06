package com.cldbiz.boot.dto;

import java.util.ArrayList;
import java.util.List;

public class PageRespDto<T> {
	private Long start;
	private Long limit;
	private Long total;
	private List<T> results;
	
	public PageRespDto() {
		results = new ArrayList<T>();
	}
	
	public PageRespDto(Long start, Long limit) {
		this.start = start;
		this.limit = limit;
		results = new ArrayList<T>();
	}
	
	public Long getStart() {
		return start;
	}
	
	public void setStart(Long start) {
		this.start = start;
	}
	
	public Long getLimit() {
		return limit;
	}
	
	public void setLimit(Long limit) {
		this.limit = limit;
	}
	
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
}
