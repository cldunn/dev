package com.cldbiz.afw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.InitializingBean;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class BaseDaoImpl<T> implements BaseDao<T>, InitializingBean {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected JPAQueryFactory jpaQueryFactory;
	
	@Override
	public void afterPropertiesSet() {
		jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	protected Integer remove(List<T> domains) {
		for (int i = 0; i < domains.size(); i++) {
			entityManager.remove(domains.get(i));
		}
		entityManager.flush();
		entityManager.clear();
		
		return domains.size();
	}

	protected T save(T domain) {
		entityManager.persist(domain);
		entityManager.flush();
		
		return domain;
	}
	
	protected List<T> save(List<T> domains) {
		int batchSize = 50;
		for (int i = 0; i < domains.size(); i++) {
			entityManager.persist(domains.get(i));
			 if(i % batchSize == 0) {
				 entityManager.flush();
				 entityManager.clear();
			 }
		}
		entityManager.flush();
		
		return domains;
	}
	
}
