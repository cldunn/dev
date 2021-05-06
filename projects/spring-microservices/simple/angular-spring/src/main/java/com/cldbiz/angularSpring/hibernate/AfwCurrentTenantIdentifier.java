package com.cldbiz.angularSpring.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.cldbiz.angularSpring.common.AfwExecContext;

public class AfwCurrentTenantIdentifier implements CurrentTenantIdentifierResolver {
	
	@Override
	public String resolveCurrentTenantIdentifier() {
		return AfwExecContext.getDsKey();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
