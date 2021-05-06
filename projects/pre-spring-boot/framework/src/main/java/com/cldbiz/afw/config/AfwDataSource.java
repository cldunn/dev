package com.cldbiz.afw.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Component
public class AfwDataSource extends AbstractRoutingDataSource {

	private Map<String, DataSource>dsMap = new ConcurrentHashMap<String, DataSource>();
	
	
	@Override
	protected Object determineCurrentLookupKey() {
		return AfwExecutionContext.getDsKey();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		String dsKey = (String) determineCurrentLookupKey();
		DataSource ds = dsMap.get(dsKey);
		if (ds == null) {
			BasicDataSource basicDs = new BasicDataSource();
			
			basicDs.setUrl(AfwEnvironment.getProperty(dsKey + ".url"));
			basicDs.setDriverClassName(AfwEnvironment.getProperty(dsKey + ".driverClassName"));
			basicDs.setUsername(AfwEnvironment.getProperty(dsKey + ".username"));
			basicDs.setPassword(AfwEnvironment.getProperty(dsKey + ".password"));
			basicDs.setMaxActive(Integer.valueOf(AfwEnvironment.getProperty(dsKey + ".maxActive")));
			basicDs.setMaxIdle(Integer.valueOf(AfwEnvironment.getProperty(dsKey + ".maxIdle")));
			basicDs.setMaxWait(Integer.valueOf(AfwEnvironment.getProperty(dsKey + ".maxWait")));
			
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
