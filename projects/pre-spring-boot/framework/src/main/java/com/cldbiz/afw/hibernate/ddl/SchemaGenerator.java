package com.cldbiz.afw.hibernate.ddl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.EntityType;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import com.cldbiz.afw.config.AfwConfig;
import com.cldbiz.afw.config.AfwEnvironment;

@Component
public class SchemaGenerator {

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManager;
	
	@Autowired
	private AfwEnvironment afwEnvironment;
	
	
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AfwConfig.class);
		SchemaGenerator schemaGenerator = (SchemaGenerator) context.getBean("schemaGenerator");
		
		File rootDir = new File("src/main/sql");
		if (!rootDir.exists()) {
			rootDir.mkdir();
		}
		
		String[] names = Arrays.copyOfRange(args, 1, args.length);
		List<Class> clazzes = schemaGenerator.getClasses(schemaGenerator.entityManager, names);
		
		if ("generate".equalsIgnoreCase(args[0])) {
			schemaGenerator.generateSchema(clazzes);
		}
		else if ("export".equalsIgnoreCase(args[0])) {
			schemaGenerator.exportSchema(clazzes);
		}
	}
	
	private List<Class> getClasses(LocalContainerEntityManagerFactoryBean entityManger, String[] names) {
		List<Class> clazzes = new ArrayList<Class>();
		List<String> clazzNames = Arrays.asList(names);
		Set<EntityType<?>> entityTypes = entityManager.getNativeEntityManagerFactory().getMetamodel().getEntities();
		for (EntityType<?> entityType : entityTypes) {
			if (clazzNames.size() == 0 || clazzNames.contains(entityType.getName())) {
				System.out.println("Entity name is: " + entityType.getName());
				clazzes.add(entityType.getJavaType());
			}
		}
		return clazzes;
	}
	
	private void generateSchemaClass(Class clazz) {
		Configuration config = new Configuration();
		config.setProperty("hibernate.dialect", AfwEnvironment.getProperty("hibernate.dialect"));

		config.addAnnotatedClass(clazz);
		/*
		SchemaExport schemaExport = new SchemaExport(config);
		
		schemaExport.setFormat(true);
		schemaExport.setDelimiter(";");
		schemaExport.setOutputFile("src/main/sql/" +  clazz.getSimpleName() + ".sql");
		schemaExport.execute(true, false, false, false);
		*/
	}

	private void generateSchema(List<Class> clazzes) {
		Configuration config = new Configuration();
		config.setProperty("hibernate.dialect", AfwEnvironment.getProperty("hibernate.dialect"));

		for (Class<Object> clazz: clazzes) {
			config.addAnnotatedClass(clazz);
		}
		/*
		SchemaExport schemaExport = new SchemaExport(config);
        
		schemaExport.setFormat(true);
		schemaExport.setDelimiter(";");
		schemaExport.setOutputFile("src/main/sql/schemaStage.sql");
		schemaExport.execute(true, false, false, false);
		*/
	}

	private void exportSchema(List<Class> clazzes) {
		Configuration config = new Configuration();
		
		config.setProperty("hibernate.hbm2ddl.auto", "create");
		config.setProperty("hibernate.dialect", AfwEnvironment.getProperty("hibernate.dialect"));
		config.setProperty("hibernate.showsql", AfwEnvironment.getProperty("hibernate.show_sql"));
		config.setProperty("hibernate.connection.url", AfwEnvironment.getProperty("ds.test.url"));
		config.setProperty("hibernate.connection.username", AfwEnvironment.getProperty("ds.test.username"));
		config.setProperty("hibernate.connection.password", AfwEnvironment.getProperty("ds.test.password"));
		config.setProperty("hibernate.connection.driverClassName", AfwEnvironment.getProperty("ds.test.driverClassName"));

		for (Class<Object> clazz: clazzes) {
			config.addAnnotatedClass(clazz);
		}
		
		/*
		SchemaExport schemaExport = new SchemaExport(config);
		
		schemaExport.setFormat(true);
		schemaExport.setDelimiter(";");
		schemaExport.setOutputFile("src/main/sql/schema.sql");
		
		schemaExport.execute(true, true, false, true);
		*/
	}
}
