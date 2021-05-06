package com.cldbiz.afw.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cldbiz.afw.domain.QRole;
import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.RoleDto;
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

	@Override
	public Boolean isDuplicateRole(RoleDto roleDto) {
		QRole qRole = QRole.role;

		BooleanBuilder builder = new BooleanBuilder();
		if (roleDto.getId() != null && roleDto.getId() > 0) {
			builder.and(qRole.id.ne(roleDto.getId()));
		}
		builder.and(qRole.name.eq(roleDto.getName()));
		
		return jpaQueryFactory.from(qRole)
				.where(builder)
				.fetchCount() > 0 ? true : false;
	}

	@Override
	public Long countRoles(RoleDto roleCriteria) {
		QRole qRole = QRole.role;
		
		return jpaQueryFactory.from(qRole)
				.where(findPredicate(roleCriteria))
				.fetchCount();
	}

	@Override
	public List<Role> findRoles(RoleDto roleCriteria) {
		QRole qRole = QRole.role;
		
		return jpaQueryFactory.selectFrom(qRole)
				.where(findPredicate(roleCriteria))
				.orderBy(sortByName())
				.fetch();
	}

	@Override
	public List<Role> searchRoles(RoleDto roleCriteria) {
		QRole qRole = QRole.role;
		
		return jpaQueryFactory.selectFrom(qRole)
				.where(searchPredicate(roleCriteria))
				.orderBy(sortByName())
				.fetch();
	}

	@Override
	public List<Role> searchRolesPage(PageReqDto<RoleDto> pageReqDto) {
		QRole qRole = QRole.role;
		
		return jpaQueryFactory.selectFrom(qRole)
				.where(searchPredicate(pageReqDto.getCriteria()))
				.orderBy(sortByName())
				.offset(pageReqDto.getStart().intValue())
				.limit(pageReqDto.getLimit().intValue())
				.fetch();
	}

	@Override
	public void deleteRoles(List<Long> roleIds) {
		QRole qRole = QRole.role;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qRole.id.in(roleIds));
		
		jpaQueryFactory.delete(qRole)
			.where(builder).execute();
	}

	@Override
	public void removeRoles(List<Long> roleIds) {
		Integer blockSz = 50;
		Integer fromNdx = 0;
		Integer toNdx = 0;
		
		QRole qRole = QRole.role;
		
		while (fromNdx < roleIds.size()) {
			toNdx = Math.min(fromNdx + blockSz, roleIds.size());

			BooleanBuilder builder = new BooleanBuilder();
			builder.and(qRole.id.in(roleIds.subList(fromNdx, toNdx)));
			remove(jpaQueryFactory.selectFrom(qRole).where(builder).fetch());
			
			fromNdx = Math.min(toNdx, roleIds.size());
		}
	}

	@Override
	public Role saveRole(Role role) {
		return save(role);
	}

	@Override
	public List<Role> saveRoles(List<Role> roles) {
		return save(roles);
	}

	public static Predicate findPredicate(RoleDto roleDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QRole qRole = QRole.role;
		
		if (StringUtils.isNotBlank(roleDto.getName())) {
			builder.and((Predicate) qRole.name.equalsIgnoreCase(roleDto.getName()));
		}

		if (StringUtils.isNotBlank(roleDto.getDescription())) {
			builder.and((Predicate) qRole.description.equalsIgnoreCase(roleDto.getDescription()));
		}
		
		return builder;
	}

	public static Predicate searchPredicate(RoleDto roleDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QRole qRole = QRole.role;
		
		if (StringUtils.isNotBlank(roleDto.getName())) {
			builder.and((Predicate) qRole.name.containsIgnoreCase(roleDto.getName()));
		}

		if (StringUtils.isNotBlank(roleDto.getDescription())) {
			builder.and((Predicate) qRole.description.containsIgnoreCase(roleDto.getDescription()));
		}
		
		return builder;
	}
	
	private OrderSpecifier[] sortByName() {
		return new OrderSpecifier[] {
			QRole.role.name.asc()
		};
	}

}
