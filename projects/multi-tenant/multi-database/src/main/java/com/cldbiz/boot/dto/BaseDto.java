package com.cldbiz.boot.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;

public class BaseDto implements Serializable {
	private static final long serialVersionUID = 7189928031802909194L;

	@Digits(integer=10, fraction=0)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
