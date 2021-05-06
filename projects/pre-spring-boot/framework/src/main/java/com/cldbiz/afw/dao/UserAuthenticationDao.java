package com.cldbiz.afw.dao;

import java.util.List;

import com.cldbiz.afw.domain.UserAuthentication;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.UserAuthenticationDto;

public interface UserAuthenticationDao extends BaseDao<UserAuthentication> {
	public UserAuthentication findUserAuthentication(Long userAuthenticationId);
	public Boolean isDuplicateUserAuthentication(UserAuthenticationDto userAuthenticationDto);
	
	public Long countUserAuthentications(UserAuthenticationDto userAuthenticationDto);
	public List<UserAuthentication> findUserAuthentications(UserAuthenticationDto userAuthenticationDto);
	public List<UserAuthentication> searchUserAuthentications(UserAuthenticationDto userAuthenticationDto);
	public List<UserAuthentication> searchUserAuthenticationsPage(PageReqDto<UserAuthenticationDto> pageReqDto);

	public void deleteUserAuthentications(List<Long> userAuthenticationIds);
	public void removeUserAuthentications(List<Long> userAuthenticationIds);
	public UserAuthentication saveUserAuthentication(UserAuthentication userAuthentication);
	public List<UserAuthentication> saveUserAuthentications(List<UserAuthentication> userAuthentications);
}
