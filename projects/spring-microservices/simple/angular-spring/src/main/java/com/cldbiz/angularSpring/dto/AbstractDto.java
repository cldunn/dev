package com.cldbiz.angularSpring.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;

import com.cldbiz.angularSpring.domain.AbstractDomain;

// import com.cldbiz.userportal.domain.AbstractDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract @Data class AbstractDto implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;

	@EqualsAndHashCode.Exclude
	private Integer start = 0;
	
	@EqualsAndHashCode.Exclude
	private Integer limit = 100;
	
	@EqualsAndHashCode.Exclude
	private List<Sort.Order> sortOrders;
	
	protected AbstractDto() {}
	
	protected AbstractDto(AbstractDomain domain) {
		this.id = domain.getId();
	}
}
