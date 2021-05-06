package com.cldbiz.afw.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cldbiz.afw.domain.QRoleResource;
import com.cldbiz.afw.domain.RoleResource;
import com.cldbiz.afw.domain.RoleResourcePK;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.RoleResourceDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Component
public class RoleResourceDaoImpl extends BaseDaoImpl<RoleResource> implements RoleResourceDao {

	@Override
	public RoleResource findRoleResource(RoleResourcePK roleResourcePK) {
		QRoleResource qRoleResource = QRoleResource.roleResource;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qRoleResource.pk.eq(roleResourcePK));
		
		return jpaQueryFactory.selectFrom(qRoleResource)
				.where(builder)
				.fetchOne();
	}

	@Override
	public Long countRoleResources(RoleResourceDto roleResourceCriteria) {
		QRoleResource qRoleResource = QRoleResource.roleResource;
		
		return jpaQueryFactory.from(qRoleResource)
				.where(findPredicate(roleResourceCriteria))
				.fetchCount();
	}

	@Override
	public List<RoleResource> findRoleResources(RoleResourceDto roleResourceCriteria) {
		QRoleResource qRoleResource = QRoleResource.roleResource;
		
		return jpaQueryFactory.selectFrom(qRoleResource)
				.where(findPredicate(roleResourceCriteria))
				.fetch();
	}

	@Override
	public List<RoleResource> findRoleResourcesPage(PageReqDto<RoleResourceDto> pageReqDto) {
		QRoleResource qRoleResource = QRoleResource.roleResource;
		
		return jpaQueryFactory.selectFrom(qRoleResource)
				.where(findPredicate(pageReqDto.getCriteria()))
				.offset(pageReqDto.getStart().intValue())
				.limit(pageReqDto.getLimit().intValue())
				.fetch();
	}

	@Override
	public void deleteRoleResources(List<RoleResourcePK> roleResourcePKs) {
		QRoleResource qRoleResource = QRoleResource.roleResource;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qRoleResource.pk.in(roleResourcePKs));
		
		jpaQueryFactory.delete(qRoleResource)
			.where(builder).execute();
	}

	@Override
	public void removeRoleResources(List<RoleResourcePK> roleResourcePKs) {
		Integer blockSz = 50;
		Integer fromNdx = 0;
		Integer toNdx = 0;
		
		QRoleResource qRoleResource = QRoleResource.roleResource;
		
		while (fromNdx < roleResourcePKs.size()) {
			toNdx = Math.min(fromNdx + blockSz, roleResourcePKs.size());

			BooleanBuilder builder = new BooleanBuilder();
			builder.and(qRoleResource.pk.in(roleResourcePKs.subList(fromNdx, toNdx)));
			remove(jpaQueryFactory.selectFrom(qRoleResource).where(builder).fetch());
			
			fromNdx = Math.min(toNdx, roleResourcePKs.size());
		}
	}

	@Override
	public RoleResource saveRoleResource(RoleResourceDto roleResourceDto) {
		return save(new RoleResource(roleResourceDto));
	}

	@Override
	public List<RoleResource> saveRoleResources(List<RoleResourceDto> roleResourceDtos) {
		List<RoleResource> roleResources = new ArrayList<RoleResource>();
		for (RoleResourceDto roleResourceDto: roleResourceDtos) {
			roleResources.add(new RoleResource(roleResourceDto));
		}
		return save(roleResources);
	}

	public static Predicate findPredicate(RoleResourceDto roleResourceCriteria) {
		BooleanBuilder builder = new BooleanBuilder();
		QRoleResource qRoleResource = QRoleResource.roleResource;
		
		if (roleResourceCriteria.getPk() != null && roleResourceCriteria.getPk().getRoleId() != null) {
			builder.and((Predicate) qRoleResource.pk.roleId.eq(roleResourceCriteria.getPk().getRoleId()));
		}

		if (roleResourceCriteria.getPk() != null && roleResourceCriteria.getPk().getResourceId() != null) {
			builder.and((Predicate) qRoleResource.pk.resourceId.eq(roleResourceCriteria.getPk().getResourceId()));
		}

		if (roleResourceCriteria.getIsCreatable() != null) {
			builder.and((Predicate) qRoleResource.isCreatable.eq(roleResourceCriteria.getIsCreatable()));
		}
		
		if (roleResourceCriteria.getIsDeletable() != null) {
			builder.and((Predicate) qRoleResource.isDeletable.eq(roleResourceCriteria.getIsDeletable()));
		}

		if (roleResourceCriteria.getIsReadable() != null) {
			builder.and((Predicate) qRoleResource.isReadable.eq(roleResourceCriteria.getIsReadable()));
		}

		if (roleResourceCriteria.getIsUpdatable() != null) {
			builder.and((Predicate) qRoleResource.isUpdatable.eq(roleResourceCriteria.getIsUpdatable()));
		}

		return builder;
	}
}
