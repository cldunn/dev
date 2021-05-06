package com.cldbiz.afw.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="USER_INFO_ROLE")
@AssociationOverrides({
	@AssociationOverride(name="pk.userInfoId", joinColumns=@JoinColumn(name="USER_INFO_ID")),
	@AssociationOverride(name="pk.roleId", joinColumns=@JoinColumn(name="ROLE_ID"))
})
public class UserInfoRole extends BaseDomain {
	@EmbeddedId
	private UserInfoRolePK pk = new UserInfoRolePK();
}
