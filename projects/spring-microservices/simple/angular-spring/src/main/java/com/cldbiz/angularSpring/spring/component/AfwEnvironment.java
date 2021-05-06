package com.cldbiz.angularSpring.spring.component;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import com.cldbiz.angularSpring.common.AfwExecContext;

@PropertySources({
    @PropertySource("file:${cfg.home}/config.properties"),
    @PropertySource("classpath:application.properties")
})
public class AfwEnvironment {
	@Resource
	protected Environment env;
	
	public String getRedisHost() {
		return env.getProperty("spring.redis.host");
	}

	public Integer getRedisPort() {
		return Integer.parseInt(env.getProperty("spring.redis.port"));
	}

	public String getDataSourceDefaultTenant() {
		return env.getProperty("ds.default");
	}
	
	public String getHibernateDialect() {
		return env.getProperty("hibernate.dialect");
	}

	public String getHibernateFormatSql() {
		return env.getProperty("hibernate.format_sql");
	}

	public String getHibernateShowSql() {
		return env.getProperty("hibernate.show_sql");
	}

	public String getHibernateMultiTenantConnectionProvider() {
		return env.getProperty("hibernate.multi_tenant_connection_provider");
	}
	
	public String getHibernateTenantIdentifierResolver() {
		return env.getProperty("hibernate.tenant_identifier_resolver");
	}

	public String getHibernateConnectionAutocommit() {
		return env.getProperty("hibernate.connection.autocommit");
	}

	public String getDataSourceUrl() {
		return env.getProperty(AfwExecContext.getDsKey() + ".url");
	}

	public String getDataSourceDriverClassName() {
		return env.getProperty(AfwExecContext.getDsKey() + ".driver-class-name");
	}

	public String getDataSourceUsername() {
		return env.getProperty(AfwExecContext.getDsKey() + ".username");
	}

	public String getDataSourcePassword() {
		return env.getProperty(AfwExecContext.getDsKey() + ".password");
	}
	
	public Integer getDataSourceMaxActive() {
		return Integer.parseInt(env.getProperty(AfwExecContext.getDsKey() + ".maxActive"));
	}
	
	public Integer getDataSouceMinIdle() {
		return Integer.parseInt(env.getProperty(AfwExecContext.getDsKey() + ".minIdle"));
	}
	
	public Integer getDataSourceMaxWait() {
		return Integer.parseInt(env.getProperty(AfwExecContext.getDsKey() + ".maxWait"));
	}

}
