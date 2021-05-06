package com.cldbiz.afw.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.cldbiz.afw.config.AfwExecutionContext;

public class AfwCurrentTenantIdentifier implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		return AfwExecutionContext.getDsKey();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
