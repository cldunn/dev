package com.cldbiz.boot.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.cldbiz.boot.domain.UserInfoRolePK;

/*
@Entity
@Table(name="USER_INFO_ROLE")
@AssociationOverrides({
	@AssociationOverride(name="pk.userInfoId", joinColumns=@JoinColumn(name="USER_INFO_ID")),
	@AssociationOverride(name="pk.roleId", joinColumns=@JoinColumn(name="ROLE_ID"))
})
*/
public class UserInfoRole extends BaseDomain {
	private static final long serialVersionUID = -6918844613275328758L;
	
	@EmbeddedId
	private UserInfoRolePK pk = new UserInfoRolePK();
}
