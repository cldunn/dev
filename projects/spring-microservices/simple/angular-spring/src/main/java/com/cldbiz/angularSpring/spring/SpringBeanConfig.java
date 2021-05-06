package com.cldbiz.angularSpring.spring;

import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.cldbiz.angularSpring.spring.bean.AfwMessageSource;
import com.cldbiz.angularSpring.spring.component.AfwDataSource;
import com.cldbiz.angularSpring.spring.component.AfwPersistenceUnitPostProcessor;
import com.cldbiz.angularSpring.spring.component.AppEnvironment;

@Configuration
public class SpringBeanConfig {

	@Autowired
	AppEnvironment env;
	
	@Autowired
	AfwDataSource dataSource;
	
	@Autowired
	AfwPersistenceUnitPostProcessor afwPersistenceUnitPostProcessor;
	
	@Bean
	public AfwMessageSource messageSource() {
		AfwMessageSource messageSource = new AfwMessageSource();
		messageSource.setBasenames("classpath:messages");
		return messageSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPersistenceUnitName("angularSpringPersistenceUnit");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	
		// set hibernate jpa properties
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", env.getHibernateDialect());
		hibernateProperties.put("hibernate.format_sql", env.getHibernateFormatSql());
		hibernateProperties.put("hibernate.show_sql", env.getHibernateShowSql());
		hibernateProperties.setProperty("hibernate.connection.autocommit", env.getHibernateConnectionAutocommit());
		
		// TODO: is this relevant since I use AfwDataSource
		// hibernateProperties.setProperty("hibernate.multiTenancy", MultiTenancyStrategy.DATABASE.toString());
		// hibernateProperties.setProperty("hibernate.tenant_identifier_resolver", env.getHibernateTenantIdentifierResolver());
		// hibernateProperties.setProperty("hibernate.multi_tenant_connection_provider", env.getHibernateMultiTenantConnectionProvider());

		entityManagerFactoryBean.setJpaProperties(hibernateProperties);
		entityManagerFactoryBean.setPackagesToScan("com.cldbiz.*.domain");
		
		// set datasource
		entityManagerFactoryBean.setPersistenceUnitPostProcessors(afwPersistenceUnitPostProcessor);
		
		return entityManagerFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManger = new JpaTransactionManager();
		jpaTransactionManger.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return jpaTransactionManger;
	}
	
	/*
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
	  SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	  // factoryBean.setTransactionFactory(transactionFactory);
	  // factoryBean.setDatabaseIdProvider(databaseIdProvider);
	  // factoryBean.setConfigLocation(configLocation);
	  factoryBean.setDataSource(dataSource);
	  return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
	  return new SqlSessionTemplate(sqlSessionFactory());
	}

	private Properties getHibernateProperties() {
		Properties hibernateProperties = new Properties();
		
		hibernateProperties.put("hibernate.dialect", env.getHibernateDialect());
		hibernateProperties.put("hibernate.format_sql", env.getHibernateFormatSql());
		hibernateProperties.put("hibernate.show_sql", env.getHibernateShowSql());
		hibernateProperties.setProperty("hibernate.connection.autocommit", env.getHibernateConnectionAutocommit());
		
		hibernateProperties.setProperty("hibernate.multiTenancy", MultiTenancyStrategy.DATABASE.toString());
		hibernateProperties.setProperty("hibernate.tenant_identifier_resolver", env.getHibernateTenantIdentifierResolver());
		hibernateProperties.setProperty("hibernate.multi_tenant_connection_provider", env.getHibernateMultiTenantConnectionProvider());

		return hibernateProperties;
				
	}
	*/

}
