package com.cldbiz.angularSpring.service;

import java.util.List;

import com.cldbiz.angularSpring.dto.UserProfileDto;

public interface UserProfileService {
	List<UserProfileDto> findAll();
}
