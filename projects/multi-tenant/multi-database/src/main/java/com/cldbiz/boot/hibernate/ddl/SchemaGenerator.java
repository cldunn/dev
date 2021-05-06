package com.cldbiz.boot.hibernate.ddl;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.cldbiz.boot.config.BootConfig;
import com.cldbiz.boot.config.BootEnvironment;

@Component
public class SchemaGenerator {
	
	@Autowired
	private BootEnvironment bootEnvironment;
	
    private String dialect = BootEnvironment.getProperty("hibernate.dialect");
    
    private String[] entityPackages;
    private String entity;
 
    public SchemaGenerator dialect(String dialect) {
        this.dialect = dialect;
        return this;
    }
 
    public SchemaGenerator entities(String... entityPackages) {
        this.entityPackages = entityPackages;
        return this;
    }
 
    public SchemaGenerator schemaExport(String fileName, String targetDirectory) throws Exception {
        if (entityPackages == null && entityPackages.length == 0) {
            System.out.println("Not packages selected");
            System.exit(0);
        }
        File exportFile = createExportFileAndMakeDirectory(fileName, targetDirectory);
 
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.DIALECT, dialect)
                .build();
 
        MetadataImplementor metadata = (MetadataImplementor) mapAnnotatedClasses(serviceRegistry).buildMetadata();
 
        SchemaExport schemaExport = new SchemaExport(metadata);
        
        schemaExport.setOutputFile(exportFile.getAbsolutePath());
        schemaExport.setDelimiter(";");
        schemaExport.setFormat(true);
        schemaExport.execute(true, false, false, false);
        
        ((StandardServiceRegistryImpl) serviceRegistry).destroy();
 
        System.out.println(exportFile.getAbsolutePath());
 
        return this;
 
    }
 
    private File createExportFileAndMakeDirectory(String fileName, String targetDirectory) {
        File exportFile;
        if (targetDirectory != null) {
            final File directory = new File(targetDirectory);
            directory.mkdirs();
            exportFile = new File(directory, fileName);
        } else {
            exportFile = new File(fileName);
        }
        return exportFile;
    }
 
    private MetadataSources mapAnnotatedClasses(ServiceRegistry serviceRegistry) {
        MetadataSources sources = new MetadataSources(serviceRegistry);
 
        final Reflections reflections = new Reflections((Object) entityPackages);
        for (final Class<?> mappedSuperClass : reflections.getTypesAnnotatedWith(MappedSuperclass.class)) {
        	if (entity == null || mappedSuperClass.getName().endsWith(entity)) {
	            sources.addAnnotatedClass(mappedSuperClass);
	            System.out.println("Mapped = " + mappedSuperClass.getName());
        	}
        }
        for (final Class<?> entityClasses : reflections.getTypesAnnotatedWith(Entity.class)) {
        	if (entity == null || entityClasses.getName().endsWith(entity)) {
	            sources.addAnnotatedClass(entityClasses);
	            System.out.println("Mapped = " + entityClasses.getName());
        	}
        }
        
        return sources;
    }
 
    public static SchemaGenerator instance() {
        return new SchemaGenerator();
    }
    
    public static void main(String[] args) throws Exception {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(BootConfig.class);
    	String entityPackage = BootEnvironment.getProperty("hibernate.gen.entitiy_package");
        String sqlDir = BootEnvironment.getProperty("hibernate.gen.sql_dir");
        String sqlFn = BootEnvironment.getProperty("hibernate.gen.sql_fn");

    	SchemaGenerator exporter = SchemaGenerator.instance();
    	if (args.length > 0) {
    		exporter.entity = args[0];
    	}
    	
    	exporter.entities(entityPackage).schemaExport(sqlFn, sqlDir);
    }
}