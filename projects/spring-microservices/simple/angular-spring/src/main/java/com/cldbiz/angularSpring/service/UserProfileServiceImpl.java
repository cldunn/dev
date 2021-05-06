package com.cldbiz.angularSpring.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cldbiz.angularSpring.dao.mybatis.UserProfileMapper;
import com.cldbiz.angularSpring.domain.UserProfile;
import com.cldbiz.angularSpring.dto.UserProfileDto;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	UserProfileMapper userProfileMapper;

	@Override
	public List<UserProfileDto> findAll() {
		List<UserProfile> userProfiles = userProfileMapper.findAll();
		return userProfiles.stream().map(up -> new UserProfileDto(up)).collect(Collectors.toList());
	}
	

}
