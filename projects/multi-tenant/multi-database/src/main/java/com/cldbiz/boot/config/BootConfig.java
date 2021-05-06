package com.cldbiz.boot.config;

import java.util.Properties;

import javax.sql.DataSource;

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

import com.cldbiz.boot.hibernate.BootDataSourceBasedMultiTenantConnectionProviderImpl;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = {
    "com.cldbiz.boot.util.**",
	"com.cldbiz.boot.service.**",
	"com.cldbiz.boot.dao.**"
})
public class BootConfig {
	private BootDataSourceBasedMultiTenantConnectionProviderImpl dsMap = new BootDataSourceBasedMultiTenantConnectionProviderImpl();
	
	public BootConfig() {
		BootExecutionContext.setDsKey(BootEnvironment.getProperty("ds.default"));
	}
	
	@Bean
	public static BootEnvironment bootEnvironment() {
		BootEnvironment props = new BootEnvironment();
		props.setLocations(new ClassPathResource[] {
			new ClassPathResource("configuration.properties")
		});
		props.setIgnoreUnresolvablePlaceholders(true);
		return props;
	}
	
    @Bean
    public DataSource bootDataSource() {
        return dsMap.getCtxDataSource();
    }

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setPersistenceUnitName(BootEnvironment.getProperty("persistenceUnitName"));
		entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
		entityManagerFactoryBean.setPackagesToScan(BootEnvironment.getProperty("packages.domain"));
		entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
		
		entityManagerFactoryBean.setPersistenceUnitPostProcessors(new BootPersistenceUnitPostProcessor());
		
		return entityManagerFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}

	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", BootEnvironment.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.format_sql", BootEnvironment.getProperty("hibernate.format_sql"));
		props.setProperty("hibernate.connection.autocommit", BootEnvironment.getProperty("hibernate.connection.autocommit"));
		
		props.setProperty("hibernate.multiTenancy", MultiTenancyStrategy.DATABASE.toString());
		props.setProperty("hibernate.tenant_identifier_resolver", BootEnvironment.getProperty("hibernate.tenant_identifier_resolver"));
		props.setProperty("hibernate.multi_tenant_connection_provider", BootEnvironment.getProperty("hibernate.multi_tenant_connection_provider"));
		
		return props;
	}

}
