package com.cldbiz.angularSpring.hibernate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;

import com.cldbiz.angularSpring.spring.component.AfwEnvironment;
import com.cldbiz.angularSpring.spring.component.AppEnvironment;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// @Component
public class AfwDataSourceBasedMultiTenantConnectionProviderImpl
		extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private static Map<String, DataSource>dsMap = new ConcurrentHashMap<String, DataSource>();
	
	@Autowired
	AppEnvironment env;
	
	@Override
	protected DataSource selectAnyDataSource() {
		return selectDataSource(env.getDataSourceDefaultTenant());
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		DataSource ds = dsMap.get(tenantIdentifier);
		if (ds == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(env.getDataSourceUrl());
			config.setDriverClassName(env.getDataSourceDriverClassName());
			config.setUsername(env.getDataSourceUsername());
		    config.setPassword(env.getDataSourcePassword());
		    config.setMaximumPoolSize(env.getDataSourceMaxActive());
		    config.setMinimumIdle(env.getDataSouceMinIdle());
		    config.setConnectionTimeout(env.getDataSourceMaxWait());
			
			HikariDataSource basicDs = new HikariDataSource(config);

			TransactionAwareDataSourceProxy dsProxy = new TransactionAwareDataSourceProxy(basicDs);
			dsProxy.setReobtainTransactionalConnections(true);
			dsMap.put(tenantIdentifier, dsProxy);
			ds = dsProxy;
		}
		
		return ds;

	}
}
