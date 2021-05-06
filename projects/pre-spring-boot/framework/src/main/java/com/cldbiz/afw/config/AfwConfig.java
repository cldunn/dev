package com.cldbiz.afw.config;

import java.util.Properties;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = {
	"com.cldbiz.afw.util.**",
	"com.cldbiz.afw.service.**",
	"com.cldbiz.afw.dao.**",
	"com.cldbiz.afw.hibernate.ddl.**"
})
public class AfwConfig {
	
	public AfwConfig() {
		AfwExecutionContext.setDsKey(AfwEnvironment.getProperty("ds.default"));
	}
	
	@Bean
	public static AfwEnvironment afwEnvironment() {
		AfwEnvironment props = new AfwEnvironment();
		props.setLocations(new ClassPathResource[] {
			new ClassPathResource("configuration.properties")
		});
		props.setIgnoreUnresolvablePlaceholders(true);
		return props;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setPersistenceUnitName(AfwEnvironment.getProperty("persistenceUnitName"));
		// entityManagerFactoryBean.setPersistenceProviderClass(PersistenceProvider.class);
		entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
		entityManagerFactoryBean.setPackagesToScan(AfwEnvironment.getProperty("packages.domain"));
		entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
		
		entityManagerFactoryBean.setPersistenceUnitPostProcessors(new AfwPersistenceUnitPostProcessor());
		
		return entityManagerFactoryBean;
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}
	
	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", AfwEnvironment.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.format_sql", AfwEnvironment.getProperty("hibernate.format_sql"));
		props.setProperty("hibernate.connection.autocommit", AfwEnvironment.getProperty("hibernate.connection.autocommit"));
		
		props.setProperty("hibernate.multiTenancy", MultiTenancyStrategy.DATABASE.toString());
		props.setProperty("hibernate.tenant_identifier_resolver", AfwEnvironment.getProperty("hibernate.tenant_identifier_resolver"));
		props.setProperty("hibernate.multi_tenant_connection_provider", AfwEnvironment.getProperty("hibernate.multi_tenant_connection_provider"));
		
		return props;
	}
}
