package com.cldbiz.boot.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.cldbiz.boot.config.BootEnvironment;
import com.cldbiz.boot.config.BootExecutionContext;

public class BootCurrentTenantIdentifier implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenantIdentifier = BootExecutionContext.getDsKey();
		if (tenantIdentifier == null) {
			tenantIdentifier = BootEnvironment.getProperty("ds.default");
		}
		
		return tenantIdentifier;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
