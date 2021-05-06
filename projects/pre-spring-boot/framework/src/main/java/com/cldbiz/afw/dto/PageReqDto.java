package com.cldbiz.afw.dto;

public class PageReqDto<T> {
	private Long start;
	private Long limit;
	private T criteria;
	
	public PageReqDto() {
	}
	
	public PageReqDto(Long start, Long limit) {
		this.start = start;
		this.limit = limit;
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
	
	public T getCriteria() {
		return criteria;
	}
	
	public void setCriteria(T criteria) {
		this.criteria = criteria;
	}
}
