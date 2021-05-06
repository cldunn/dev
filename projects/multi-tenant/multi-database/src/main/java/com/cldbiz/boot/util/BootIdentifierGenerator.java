package com.cldbiz.boot.util;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.cldbiz.boot.util.BootApplicationContext;
import com.cldbiz.boot.util.UniqueIdUtil;

public class BootIdentifierGenerator implements IdentifierGenerator, Configurable { 
	private  Properties params;
	
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		this.params = params;
	}

	
	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		UniqueIdUtil uniqueIdUtil = BootApplicationContext.getApplicationContext().getBean(UniqueIdUtil.class);
		String idName = params.getProperty("target_table").toString();
		return uniqueIdUtil.getNextId(idName);
	}

}
