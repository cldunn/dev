package com.cldbiz.angularSpring.spring.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import com.cldbiz.angularSpring.common.AfwExecContext;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class AfwDataSource extends AbstractRoutingDataSource {

	private Map<String, DataSource> dsMap = new ConcurrentHashMap<String, DataSource>();
	
	@Autowired
	AppEnvironment env;
	
	@Override
	protected Object determineCurrentLookupKey() {
		return AfwExecContext.getDsKey();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		String dsKey = (String) determineCurrentLookupKey();
		DataSource ds = dsMap.get(dsKey);
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
			dsMap.put(dsKey, dsProxy);
			ds = dsProxy;
		}
		
		return ds;
	}
	
	@Override
	public void afterPropertiesSet() {}
}
