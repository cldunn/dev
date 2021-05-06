package com.cldbiz.afw.hibernate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import com.cldbiz.afw.config.AfwEnvironment;

public class AfwDataSourceBasedMultiTenantConnectionProviderImpl
		extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
	private static final long serialVersionUID = -4804547812176790828L;
	
	private static Map<String, DataSource>dsMap = new ConcurrentHashMap<String, DataSource>();
	
	@Override
	protected DataSource selectAnyDataSource() {
		return selectDataSource(AfwEnvironment.getProperty("ds.default"));
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		DataSource ds = dsMap.get(tenantIdentifier);
		if (ds == null) {
			BasicDataSource basicDs = new BasicDataSource();
			
			basicDs.setUrl(AfwEnvironment.getProperty(tenantIdentifier + ".url"));
			basicDs.setDriverClassName(AfwEnvironment.getProperty(tenantIdentifier + ".driverClassName"));
			basicDs.setUsername(AfwEnvironment.getProperty(tenantIdentifier + ".username"));
			basicDs.setPassword(AfwEnvironment.getProperty(tenantIdentifier + ".password"));
			basicDs.setMaxActive(Integer.valueOf(AfwEnvironment.getProperty(tenantIdentifier + ".maxActive")));
			basicDs.setMaxIdle(Integer.valueOf(AfwEnvironment.getProperty(tenantIdentifier + ".maxIdle")));
			basicDs.setMaxWait(Integer.valueOf(AfwEnvironment.getProperty(tenantIdentifier + ".maxWait")));

			TransactionAwareDataSourceProxy dsProxy = new TransactionAwareDataSourceProxy(basicDs);
			dsProxy.setReobtainTransactionalConnections(true);
			dsMap.put(tenantIdentifier, dsProxy);
			ds = dsProxy;
		}
		
		return ds;

	}

}
