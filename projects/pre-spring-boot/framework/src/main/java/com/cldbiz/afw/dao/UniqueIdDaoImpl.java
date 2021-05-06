package com.cldbiz.afw.dao;

import org.springframework.stereotype.Component;

import com.cldbiz.afw.domain.QUniqueId;
import com.cldbiz.afw.domain.UniqueId;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Component
public class UniqueIdDaoImpl extends BaseDaoImpl<UniqueId> implements UniqueIdDao {

	private static final Long DEFAULT_START = 1001L;
	
	@Override
	public UniqueId findUniqueId(String idName) {
		QUniqueId uniqueId = QUniqueId.uniqueId;
		BooleanBuilder builder = new BooleanBuilder();
		builder.and((Predicate) uniqueId.idName.eq(idName));

		UniqueId result =  jpaQueryFactory.selectFrom(uniqueId).where(builder).fetchOne();
		
		if (result == null) {
			result = new UniqueId();
			result.setIdName(idName);
			result.setNextId(DEFAULT_START);
			
			this.save(result);
		}
		
		return result;
	}
}
