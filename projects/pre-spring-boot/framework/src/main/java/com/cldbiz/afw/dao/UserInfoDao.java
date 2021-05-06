package com.cldbiz.afw.dao;

import java.util.List;

import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.domain.UserInfo;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.UserInfoDto;

public interface UserInfoDao extends BaseDao<UserInfo> {
	public UserInfo findUserInfo(Long userInfoId);
	public Boolean isDuplicateUserInfo(UserInfoDto userInfoDto);
	
	public Long countUserInfos(UserInfoDto userInfoCriteria);
	public List<UserInfo> findUserInfos(UserInfoDto userInfoCriteria);
	public List<UserInfo> searchUserInfos(UserInfoDto userInfoCriteria);
	public List<UserInfo> searchUserInfosPage(PageReqDto<UserInfoDto> pgReqDto);

	public void deleteUserInfos(List<Long> userInfoIds);
	public void removeUserInfos(List<Long> userInfoIds);
	public UserInfo saveUserInfo(UserInfo userInfo);
	public List<UserInfo> saveUserInfos(List<UserInfo> userInfo);

}
