package com.cldbiz.boot.hibernate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import com.cldbiz.boot.config.BootEnvironment;
import com.cldbiz.boot.config.BootExecutionContext;

public class BootDataSourceBasedMultiTenantConnectionProviderImpl
		extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
	private static final long serialVersionUID = -4804547812176790828L;
	
	private static Map<String, DataSource> dsMap = new ConcurrentHashMap<String, DataSource>();
	
	@Override
	protected DataSource selectAnyDataSource() {
		return selectDataSource(BootEnvironment.getProperty("ds.default"));
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		DataSource ds = dsMap.get(tenantIdentifier);
		
		if (ds == null) {
			BasicDataSource basicDs = new BasicDataSource();
			
			basicDs.setUrl(BootEnvironment.getProperty(tenantIdentifier + ".url"));
			basicDs.setDriverClassName(BootEnvironment.getProperty(tenantIdentifier + ".driverClassName"));
			basicDs.setUsername(BootEnvironment.getProperty(tenantIdentifier + ".username"));
			basicDs.setPassword(BootEnvironment.getProperty(tenantIdentifier + ".password"));
			basicDs.setMaxActive(Integer.valueOf(BootEnvironment.getProperty(tenantIdentifier + ".maxActive")));
			basicDs.setMaxIdle(Integer.valueOf(BootEnvironment.getProperty(tenantIdentifier + ".maxIdle")));
			basicDs.setMaxWait(Integer.valueOf(BootEnvironment.getProperty(tenantIdentifier + ".maxWait")));

			TransactionAwareDataSourceProxy dsProxy = new TransactionAwareDataSourceProxy(basicDs);
			dsProxy.setReobtainTransactionalConnections(true);
			dsMap.put(tenantIdentifier, dsProxy);
			ds = dsProxy;
		}
		
		return ds;
	}
	
	public DataSource getCtxDataSource() {
		DataSource ds = null;
		String tenantIdentifier = BootExecutionContext.getDsKey();
		
		if (tenantIdentifier != null) {
			ds = selectDataSource(tenantIdentifier);
		}
		else {
			ds = selectAnyDataSource();
		}
		
		return ds;
	}

}
