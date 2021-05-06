package com.cldbiz.boot.dao;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cldbiz.boot.domain.QRole;
import com.cldbiz.boot.domain.Role;
import com.cldbiz.boot.dto.RoleDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Component
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public Role findRole(Long roleId) {
		QRole qRole = QRole.role;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qRole.id.eq(roleId));
		
		return jpaQueryFactory.selectFrom(qRole)
				.where(builder)
				.fetchOne();
	}

	public List<Role> findRoles(RoleDto roleDto) {
		QRole qRole = QRole.role;
		
		return jpaQueryFactory.selectFrom(qRole)
				.where(findPredicate(roleDto))
				.orderBy(sortByName())
				.fetch();

	}
	
	protected Predicate findPredicate(RoleDto roleDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QRole qRole = QRole.role;
		
		if (StringUtils.isNotBlank(roleDto.getName())) {
			builder.and((Predicate) qRole.name.equalsIgnoreCase(roleDto.getName()));
		}

		if (StringUtils.isNotBlank(roleDto.getDescription())) {
			builder.and((Predicate) qRole.description.equalsIgnoreCase(roleDto.getDescription()));
		}
		
		if (roleDto.getProductId() != null) {
			builder.and((Predicate) qRole.productId.eq(roleDto.getProductId()));
		}
		
		return builder;
	}

	protected Predicate searchPredicate(RoleDto roleDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QRole qRole = QRole.role;
		
		if (StringUtils.isNotBlank(roleDto.getName())) {
			builder.and((Predicate) qRole.name.containsIgnoreCase(roleDto.getName()));
		}

		if (StringUtils.isNotBlank(roleDto.getDescription())) {
			builder.and((Predicate) qRole.description.containsIgnoreCase(roleDto.getDescription()));
		}
		
		if (roleDto.getProductId() != null) {
			builder.and((Predicate) qRole.productId.eq(roleDto.getProductId()));
		}
		
		return builder;
	}

	private OrderSpecifier[] sortByName() {
		return new OrderSpecifier[] {
			QRole.role.name.asc()
		};
	}

}
