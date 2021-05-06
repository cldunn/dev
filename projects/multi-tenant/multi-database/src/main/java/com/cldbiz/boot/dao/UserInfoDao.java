package com.cldbiz.boot.dao;

import java.util.List;

import com.cldbiz.boot.domain.UserInfo;
import com.cldbiz.boot.dto.PageReqDto;
import com.cldbiz.boot.dto.UserProfileDto;

public interface UserInfoDao extends BaseDao<UserInfo> {
	public Long countUserInfos(UserProfileDto userProfileDto);
	public Boolean isDuplicateUserInfo(UserProfileDto userProfileDto);
	
	public UserInfo findUserInfo(Long userInfoId);
	public List<UserInfo> findUserInfos(UserProfileDto userProfileDto);
	public List<UserInfo> searchUserInfos(UserProfileDto userProfileDto);
	public List<UserInfo> searchUserInfosPage(PageReqDto<UserProfileDto> pageReqDto);
	
	public void removeUserInfos(List<Long> userInfoIds);
	public UserInfo saveUserInfo(UserInfo userInfo);
	public List<UserInfo> saveUserInfos(List<UserInfo> userInfos);
}
