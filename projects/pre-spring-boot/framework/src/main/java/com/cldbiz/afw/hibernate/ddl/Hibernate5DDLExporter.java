package com.cldbiz.afw.hibernate.ddl;

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

import com.cldbiz.afw.config.AfwConfig;
import com.cldbiz.afw.config.AfwEnvironment;

@Component
public class Hibernate5DDLExporter {
	
	@Autowired
	private AfwEnvironment afwEnvironment;
	
    private String dialect = AfwEnvironment.getProperty("hibernate.dialect");
    private String[] entityPackages;
    private String entity;
 
    public Hibernate5DDLExporter dialect(String dialect) {
        this.dialect = dialect;
        return this;
    }
 
    public Hibernate5DDLExporter entities(String... entityPackages) {
        this.entityPackages = entityPackages;
        return this;
    }
 
    public Hibernate5DDLExporter schemaExport(String fileName, String targetDirectory) throws Exception {
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
 
    public static Hibernate5DDLExporter instance() {
        return new Hibernate5DDLExporter();
    }
    
    
 
    public static void main(String[] args) throws Exception {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AfwConfig.class);
    	
    	Hibernate5DDLExporter exporter = Hibernate5DDLExporter.instance();
    	if (args.length > 0) {
    		exporter.entity = args[0];
    	}
    	
    	exporter.entities("com.cldbiz.afw.domain").schemaExport("schema.sql", "src/main/sql");
    }
}