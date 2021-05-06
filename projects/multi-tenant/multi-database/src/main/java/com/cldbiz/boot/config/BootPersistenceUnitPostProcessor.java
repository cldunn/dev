package com.cldbiz.boot.config;

import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

public class BootPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {
	@Override
	public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
		// post processor for manipulating PersistenceUnitInfo used for creating LocalContainerEntityManagerFactoryBean 
		// facilitating use of Spring components that were not available at the time of LocalContainerEntityManagerFactoryBean creation
	}
}
