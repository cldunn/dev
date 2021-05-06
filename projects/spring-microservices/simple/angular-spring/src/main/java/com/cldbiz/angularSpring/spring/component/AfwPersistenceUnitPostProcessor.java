package com.cldbiz.angularSpring.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;
import org.springframework.stereotype.Component;

import com.cldbiz.angularSpring.hibernate.AfwDataSourceBasedMultiTenantConnectionProviderImpl;

@Component
public class AfwPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {

	@Autowired
	AfwDataSource afwDataSource;
	
	@Override
	public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
		// post processor for manipulating PersistenceUnitInfo used for creating LocalContainerEntityManagerFactoryBean 
		// facilitating use of Spring components that were not available at the time of LocalContainerEntityManagerFactoryBean creation
		
		pui.setJtaDataSource(null);
		pui.setNonJtaDataSource(afwDataSource);
	}

}
