package com.cldbiz.afw.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cldbiz.afw.domain.QResource;
import com.cldbiz.afw.domain.Resource;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.ResourceDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Component
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

	@Override
	public Resource findResource(Long resourceId) {
		QResource qResource = QResource.resource;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qResource.id.eq(resourceId));
		
		return jpaQueryFactory.selectFrom(qResource)
				.where(builder)
				.fetchOne();
	}

	@Override
	public Boolean isDuplicateResource(ResourceDto resourceDto) {
		QResource qResource = QResource.resource;

		BooleanBuilder builder = new BooleanBuilder();
		if (resourceDto.getId() != null && resourceDto.getId() > 0) {
			builder.and(qResource.id.ne(resourceDto.getId()));
		}
		builder.and(qResource.refKey.eq(resourceDto.getRefKey()));
		
		return jpaQueryFactory.from(qResource)
				.where(builder)
				.fetchCount() > 0 ? true : false;
	}

	@Override
	public Long countResources(ResourceDto resourceCriteria) {
		QResource qResource = QResource.resource;
		
		return jpaQueryFactory.from(qResource)
				.where(findPredicate(resourceCriteria))
				.fetchCount();
	}

	@Override
	public List<Resource> findResources(ResourceDto resourceCriteria) {
		QResource qResource = QResource.resource;
		
		return jpaQueryFactory.selectFrom(qResource)
				.where(findPredicate(resourceCriteria))
				.orderBy(sortByName())
				.fetch();
	}

	@Override
	public List<Resource> searchResources(ResourceDto resourceCriteria) {
		QResource qResource = QResource.resource;
		
		return jpaQueryFactory.selectFrom(qResource)
				.where(searchPredicate(resourceCriteria))
				.orderBy(sortByName())
				.fetch();
	}

	@Override
	public List<Resource> searchResourcesPage(PageReqDto<ResourceDto> pageReqDto) {
		QResource qResource = QResource.resource;
		
		return jpaQueryFactory.selectFrom(qResource)
				.where(searchPredicate(pageReqDto.getCriteria()))
				.orderBy(sortByName())
				.offset(pageReqDto.getStart().intValue())
				.limit(pageReqDto.getLimit().intValue())
				.fetch();
	}

	@Override
	public void deleteResources(List<Long> resourceIds) {
		QResource qResource = QResource.resource;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qResource.id.in(resourceIds));
		
		jpaQueryFactory.delete(qResource)
			.where(builder).execute();
		
		entityManager.flush();
		entityManager.clear();
	}

	public void removeResources(List<Long> resourceIds) {
		Integer blockSz = 50;
		Integer fromNdx = 0;
		Integer toNdx = 0;
		
		QResource qResource = QResource.resource;
		
		while (fromNdx < resourceIds.size()) {
			toNdx = Math.min(fromNdx + blockSz, resourceIds.size());

			BooleanBuilder builder = new BooleanBuilder();
			builder.and(qResource.id.in(resourceIds.subList(fromNdx, toNdx)));
			remove(jpaQueryFactory.selectFrom(qResource).where(builder).fetch());
			
			fromNdx = Math.min(toNdx, resourceIds.size());
		}
	}
	
	@Override
	public Resource saveResource(Resource resource) {
		return save(resource);
	}

	@Override
	public List<Resource> saveResources(List<Resource> resources) {
		return save(resources);
	}

	public static Predicate findPredicate(ResourceDto resourceCriteria) {
		BooleanBuilder builder = new BooleanBuilder();
		QResource qResource = QResource.resource;
		
		if (StringUtils.isNotBlank(resourceCriteria.getName())) {
			builder.and((Predicate) qResource.name.eq(resourceCriteria.getName()));
		}

		if (StringUtils.isNotBlank(resourceCriteria.getRefKey())) {
			builder.and((Predicate) qResource.refKey.eq(resourceCriteria.getRefKey()));
		}
		
		return builder;
	}

	public static Predicate searchPredicate(ResourceDto resourceCriteria) {
		BooleanBuilder builder = new BooleanBuilder();
		QResource qResource = QResource.resource;

		if (StringUtils.isNotBlank(resourceCriteria.getName())) {
			builder.and((Predicate) qResource.name.contains(resourceCriteria.getName()));
		}

		if (StringUtils.isNotBlank(resourceCriteria.getRefKey())) {
			builder.and((Predicate) qResource.refKey.contains(resourceCriteria.getRefKey()));
		}
		
		return builder;
	}
	
	
	private OrderSpecifier[] sortByName() {
		return new OrderSpecifier[] {
			QResource.resource.name.asc()
		};
	}

}
