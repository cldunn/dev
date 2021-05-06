package com.cldbiz.afw.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import com.cldbiz.afw.domain.QUserInfo;
import com.cldbiz.afw.domain.Role;
import com.cldbiz.afw.domain.UserAuthentication;
import com.cldbiz.afw.domain.UserInfo;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.RoleDto;
import com.cldbiz.afw.dto.UserInfoDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Component
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {
	@Resource
	private RoleDao roleDao;

	@Override
	public UserInfo findUserInfo(Long userInfoId) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUserInfo.id.eq(userInfoId));
		
		return jpaQueryFactory.selectFrom(qUserInfo)
				.where(builder)
				.fetchOne();
	}

	@Override
	public Boolean isDuplicateUserInfo(UserInfoDto userInfoCriteria) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (userInfoCriteria.getId() != null && userInfoCriteria.getId() > 0) {
			builder.and(qUserInfo.id.ne(userInfoCriteria.getId()));
		}
		builder.and(qUserInfo.emailAddress.eq(userInfoCriteria.getEmailAddress()));
		
		return jpaQueryFactory.from(qUserInfo)
				.where(builder)
				.fetchCount() > 0 ? true : false;
	}

	@Override
	public Long countUserInfos(UserInfoDto userInfoCriteria) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		return jpaQueryFactory.from(qUserInfo)
				.where(findPredicate(userInfoCriteria))
				.fetchCount();
	}

	@Override
	public List<UserInfo> findUserInfos(UserInfoDto userInfoCriteria) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		return jpaQueryFactory.selectFrom(qUserInfo)
				.where(findPredicate(userInfoCriteria))
				.orderBy(sortByUserInfoName())
				.fetch();
	}

	@Override
	public List<UserInfo> searchUserInfos(UserInfoDto userInfoCriteria) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		return jpaQueryFactory.selectFrom(qUserInfo)
				.where(searchPredicate(userInfoCriteria))
				.orderBy(sortByUserInfoName())
				.fetch();
	}

	@Override
	public List<UserInfo> searchUserInfosPage(PageReqDto<UserInfoDto> pageReqDto) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		return jpaQueryFactory.selectFrom(qUserInfo)
				.where(searchPredicate(pageReqDto.getCriteria()))
				.orderBy(sortByUserInfoName())
				.offset(pageReqDto.getStart().intValue())
				.limit(pageReqDto.getLimit().intValue())
				.fetch();
	}

	@Override
	public void deleteUserInfos(List<Long> userInfoIds) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUserInfo.id.in(userInfoIds));
		
		/* Requires a delete trigger on USER_INFO for */
		jpaQueryFactory.delete(qUserInfo)
			.where(builder).execute();
		
		entityManager.flush();
		entityManager.clear();
	}

	public void removeUserInfos(List<Long> userInfoIds) {
		Integer blockSz = 50;
		Integer fromNdx = 0;
		Integer toNdx = 0;
		
		QUserInfo qUserInfo = QUserInfo.userInfo;

		while (fromNdx < userInfoIds.size()) {
			toNdx = Math.min(fromNdx + blockSz, userInfoIds.size());

			BooleanBuilder builder = new BooleanBuilder();
			builder.and(qUserInfo.id.in(userInfoIds.subList(fromNdx, toNdx)));
			remove(jpaQueryFactory.selectFrom(qUserInfo).where(builder).fetch());
			
			fromNdx = Math.min(toNdx, userInfoIds.size());
		}
	}

	@Override
	public UserInfo saveUserInfo(UserInfo userInfo) {
		return save(userInfo);
	}

	@Override
	public List<UserInfo> saveUserInfos(List<UserInfo> userInfos) {
		return save(userInfos);
	}

	public static Predicate findPredicate(UserInfoDto userInfoDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		if (StringUtils.isNotBlank(userInfoDto.getFirstName())) {
			builder.and((Predicate) qUserInfo.firstName.equalsIgnoreCase(userInfoDto.getFirstName()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getLastName())) {
			builder.and((Predicate) qUserInfo.lastName.equalsIgnoreCase(userInfoDto.getLastName()));
		}
		
		if (StringUtils.isNotBlank(userInfoDto.getAddressLine1())) {
			builder.and((Predicate) qUserInfo.addressLine1.equalsIgnoreCase(userInfoDto.getAddressLine1()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getAddressLine2())) {
			builder.and((Predicate) qUserInfo.addressLine2.equalsIgnoreCase(userInfoDto.getAddressLine2()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getCity())) {
			builder.and((Predicate) qUserInfo.city.equalsIgnoreCase(userInfoDto.getCity()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getState())) {
			builder.and((Predicate) qUserInfo.state.equalsIgnoreCase(userInfoDto.getState()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getProvince())) {
			builder.and((Predicate) qUserInfo.province.equalsIgnoreCase(userInfoDto.getProvince()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getZip())) {
			builder.and((Predicate) qUserInfo.zip.equalsIgnoreCase(userInfoDto.getZip()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getCountry())) {
			builder.and((Predicate) qUserInfo.country.equalsIgnoreCase(userInfoDto.getCountry()));
		}
		
		if (StringUtils.isNotBlank(userInfoDto.getPrimaryPhone())) {
			builder.and((Predicate) qUserInfo.primaryPhone.equalsIgnoreCase(userInfoDto.getPrimaryPhone()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getSecondaryPhone())) {
			builder.and((Predicate) qUserInfo.secondaryPhone.equalsIgnoreCase(userInfoDto.getSecondaryPhone()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getFax())) {
			builder.and((Predicate) qUserInfo.fax.equalsIgnoreCase(userInfoDto.getFax()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getEmailAddress())) {
			builder.and((Predicate) qUserInfo.emailAddress.equalsIgnoreCase(userInfoDto.getEmailAddress()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getDateFormat())) {
			builder.and((Predicate) qUserInfo.dateFormat.equalsIgnoreCase(userInfoDto.getDateFormat()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getTimeFormat())) {
			builder.and((Predicate) qUserInfo.timeFormat.equalsIgnoreCase(userInfoDto.getTimeFormat()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getTimeZone())) {
			builder.and((Predicate) qUserInfo.timeZone.equalsIgnoreCase(userInfoDto.getTimeZone()));
		}

		if (userInfoDto.getLocale() != null) {
			if (StringUtils.isNotBlank(userInfoDto.getLocale().getLanguage())) {
				builder.and((Predicate) qUserInfo.localeLanguage.equalsIgnoreCase(userInfoDto.getLocale().getLanguage()));
			}
			if (StringUtils.isNotBlank(userInfoDto.getLocale().getCountry())) {
				builder.and((Predicate) qUserInfo.localeCountry.equalsIgnoreCase(userInfoDto.getLocale().getCountry()));
			}
			if (StringUtils.isNotBlank(userInfoDto.getLocale().getVariant())) {
				builder.and((Predicate) qUserInfo.localeVariant.equalsIgnoreCase(userInfoDto.getLocale().getVariant()));
			}
		}
		
		return builder;
	}

	public static Predicate searchPredicate(UserInfoDto userInfoDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		if (StringUtils.isNotBlank(userInfoDto.getFirstName())) {
			builder.and((Predicate) qUserInfo.firstName.containsIgnoreCase(userInfoDto.getFirstName()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getLastName())) {
			builder.and((Predicate) qUserInfo.lastName.containsIgnoreCase(userInfoDto.getLastName()));
		}
		
		if (StringUtils.isNotBlank(userInfoDto.getAddressLine1())) {
			builder.and((Predicate) qUserInfo.addressLine1.containsIgnoreCase(userInfoDto.getAddressLine1()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getAddressLine2())) {
			builder.and((Predicate) qUserInfo.addressLine2.containsIgnoreCase(userInfoDto.getAddressLine2()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getCity())) {
			builder.and((Predicate) qUserInfo.city.containsIgnoreCase(userInfoDto.getCity()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getState())) {
			builder.and((Predicate) qUserInfo.state.containsIgnoreCase(userInfoDto.getState()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getProvince())) {
			builder.and((Predicate) qUserInfo.province.containsIgnoreCase(userInfoDto.getProvince()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getZip())) {
			builder.and((Predicate) qUserInfo.zip.containsIgnoreCase(userInfoDto.getZip()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getCountry())) {
			builder.and((Predicate) qUserInfo.country.containsIgnoreCase(userInfoDto.getCountry()));
		}
		
		if (StringUtils.isNotBlank(userInfoDto.getPrimaryPhone())) {
			builder.and((Predicate) qUserInfo.primaryPhone.containsIgnoreCase(userInfoDto.getPrimaryPhone()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getSecondaryPhone())) {
			builder.and((Predicate) qUserInfo.secondaryPhone.containsIgnoreCase(userInfoDto.getSecondaryPhone()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getFax())) {
			builder.and((Predicate) qUserInfo.fax.containsIgnoreCase(userInfoDto.getFax()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getEmailAddress())) {
			builder.and((Predicate) qUserInfo.emailAddress.containsIgnoreCase(userInfoDto.getEmailAddress()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getDateFormat())) {
			builder.and((Predicate) qUserInfo.dateFormat.containsIgnoreCase(userInfoDto.getDateFormat()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getTimeFormat())) {
			builder.and((Predicate) qUserInfo.timeFormat.containsIgnoreCase(userInfoDto.getTimeFormat()));
		}

		if (StringUtils.isNotBlank(userInfoDto.getTimeZone())) {
			builder.and((Predicate) qUserInfo.timeZone.containsIgnoreCase(userInfoDto.getTimeZone()));
		}

		if (userInfoDto.getLocale() != null) {
			if (StringUtils.isNotBlank(userInfoDto.getLocale().getLanguage())) {
				builder.and((Predicate) qUserInfo.localeLanguage.containsIgnoreCase(userInfoDto.getLocale().getLanguage()));
			}
			if (StringUtils.isNotBlank(userInfoDto.getLocale().getCountry())) {
				builder.and((Predicate) qUserInfo.localeCountry.containsIgnoreCase(userInfoDto.getLocale().getCountry()));
			}
			if (StringUtils.isNotBlank(userInfoDto.getLocale().getVariant())) {
				builder.and((Predicate) qUserInfo.localeVariant.containsIgnoreCase(userInfoDto.getLocale().getVariant()));
			}
		}
		
		return builder;
	}
	
	private OrderSpecifier[] sortByUserInfoName() {
		return new OrderSpecifier[] {
			QUserInfo.userInfo.lastName.asc(),
			QUserInfo.userInfo.firstName.asc()
		};
	}
	
}
