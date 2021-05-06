package com.cldbiz.angularSpring.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cldbiz.angularSpring.domain.UserProfile;

@Mapper
public interface UserProfileMapper {
	 List<UserProfile> findAll();
}
