package com.cldbiz.boot.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cldbiz.boot.domain.QUserInfo;
import com.cldbiz.boot.domain.UserInfo;
import com.cldbiz.boot.dto.PageReqDto;
import com.cldbiz.boot.dto.UserProfileDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Component
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo>  implements UserInfoDao {

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
	public Boolean isDuplicateUserInfo(UserProfileDto userProfileDto) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (userProfileDto.getId() != null && userProfileDto.getId() > 0) {
			builder.and(qUserInfo.id.ne(userProfileDto.getId()));
		}
		
		BooleanBuilder builder2 = new BooleanBuilder();
		if (StringUtils.isNotBlank(userProfileDto.getEmailAddress())) {
			builder2.or(qUserInfo.emailAddress.eq(userProfileDto.getEmailAddress()));
		}
		if (StringUtils.isNotBlank(userProfileDto.getLogin())) {
			builder2.or(qUserInfo.login.eq(userProfileDto.getLogin()));
		}
		
		return jpaQueryFactory.from(qUserInfo)
				.where(builder.and(builder2))
				.fetchCount() > 0 ? true : false;
		
	}
	
	@Override
	public Long countUserInfos(UserProfileDto userProfileDto) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		return jpaQueryFactory.from(qUserInfo)
				.where(findPredicate(userProfileDto))
				.fetchCount();
		
	}
	
	@Override
	public List<UserInfo> findUserInfos(UserProfileDto userProfileDto) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		return jpaQueryFactory.selectFrom(qUserInfo)
				.where(findPredicate(userProfileDto))
				.orderBy(sortByUserInfoName())
				.fetch();
	}
	
	@Override
	public List<UserInfo> searchUserInfos(UserProfileDto userInfoCriteria) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		return jpaQueryFactory.selectFrom(qUserInfo)
				.where(searchPredicate(userInfoCriteria))
				.orderBy(sortByUserInfoName())
				.fetch();
	}

	@Override
	public List<UserInfo> searchUserInfosPage(PageReqDto<UserProfileDto> pageReqDto) {
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		return jpaQueryFactory.selectFrom(qUserInfo)
				.where(searchPredicate(pageReqDto.getCriteria()))
				.orderBy(sortByUserInfoName())
				.offset(pageReqDto.getStart().intValue())
				.limit(pageReqDto.getLimit().intValue())
				.fetch();
	}
	
	@Override
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

	protected Predicate findPredicate(UserProfileDto userProfileDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		if (StringUtils.isNotBlank(userProfileDto.getFirstName())) {
			builder.and((Predicate) qUserInfo.firstName.equalsIgnoreCase(userProfileDto.getFirstName()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getLastName())) {
			builder.and((Predicate) qUserInfo.lastName.equalsIgnoreCase(userProfileDto.getLastName()));
		}
		
		if (StringUtils.isNotBlank(userProfileDto.getAddressLine1())) {
			builder.and((Predicate) qUserInfo.addressLine1.equalsIgnoreCase(userProfileDto.getAddressLine1()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getAddressLine2())) {
			builder.and((Predicate) qUserInfo.addressLine2.equalsIgnoreCase(userProfileDto.getAddressLine2()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getCity())) {
			builder.and((Predicate) qUserInfo.city.equalsIgnoreCase(userProfileDto.getCity()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getState())) {
			builder.and((Predicate) qUserInfo.state.equalsIgnoreCase(userProfileDto.getState()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getProvince())) {
			builder.and((Predicate) qUserInfo.province.equalsIgnoreCase(userProfileDto.getProvince()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getZip())) {
			builder.and((Predicate) qUserInfo.zip.equalsIgnoreCase(userProfileDto.getZip()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getCountry())) {
			builder.and((Predicate) qUserInfo.country.equalsIgnoreCase(userProfileDto.getCountry()));
		}
		
		if (StringUtils.isNotBlank(userProfileDto.getPrimaryPhone())) {
			builder.and((Predicate) qUserInfo.primaryPhone.equalsIgnoreCase(userProfileDto.getPrimaryPhone()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getSecondaryPhone())) {
			builder.and((Predicate) qUserInfo.secondaryPhone.equalsIgnoreCase(userProfileDto.getSecondaryPhone()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getFax())) {
			builder.and((Predicate) qUserInfo.fax.equalsIgnoreCase(userProfileDto.getFax()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getEmailAddress())) {
			builder.and((Predicate) qUserInfo.emailAddress.equalsIgnoreCase(userProfileDto.getEmailAddress()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getDateFormat())) {
			builder.and((Predicate) qUserInfo.dateFormat.equalsIgnoreCase(userProfileDto.getDateFormat()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getTimeFormat())) {
			builder.and((Predicate) qUserInfo.timeFormat.equalsIgnoreCase(userProfileDto.getTimeFormat()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getTimeZone())) {
			builder.and((Predicate) qUserInfo.timeZone.equalsIgnoreCase(userProfileDto.getTimeZone()));
		}

		if (userProfileDto.getLocale() != null) {
			if (StringUtils.isNotBlank(userProfileDto.getLocale().getLanguage())) {
				builder.and((Predicate) qUserInfo.localeLanguage.equalsIgnoreCase(userProfileDto.getLocale().getLanguage()));
			}
			if (StringUtils.isNotBlank(userProfileDto.getLocale().getCountry())) {
				builder.and((Predicate) qUserInfo.localeCountry.equalsIgnoreCase(userProfileDto.getLocale().getCountry()));
			}
			if (StringUtils.isNotBlank(userProfileDto.getLocale().getVariant())) {
				builder.and((Predicate) qUserInfo.localeVariant.equalsIgnoreCase(userProfileDto.getLocale().getVariant()));
			}
		}
		
		if (StringUtils.isNotBlank(userProfileDto.getLogin())) {
			builder.and((Predicate) qUserInfo.login.equalsIgnoreCase(userProfileDto.getLogin()));
		}

		return builder;
	}

	protected Predicate searchPredicate(UserProfileDto userProfileDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QUserInfo qUserInfo = QUserInfo.userInfo;
		
		if (StringUtils.isNotBlank(userProfileDto.getFirstName())) {
			builder.and((Predicate) qUserInfo.firstName.containsIgnoreCase(userProfileDto.getFirstName()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getLastName())) {
			builder.and((Predicate) qUserInfo.lastName.containsIgnoreCase(userProfileDto.getLastName()));
		}
		
		if (StringUtils.isNotBlank(userProfileDto.getAddressLine1())) {
			builder.and((Predicate) qUserInfo.addressLine1.containsIgnoreCase(userProfileDto.getAddressLine1()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getAddressLine2())) {
			builder.and((Predicate) qUserInfo.addressLine2.containsIgnoreCase(userProfileDto.getAddressLine2()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getCity())) {
			builder.and((Predicate) qUserInfo.city.containsIgnoreCase(userProfileDto.getCity()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getState())) {
			builder.and((Predicate) qUserInfo.state.containsIgnoreCase(userProfileDto.getState()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getProvince())) {
			builder.and((Predicate) qUserInfo.province.containsIgnoreCase(userProfileDto.getProvince()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getZip())) {
			builder.and((Predicate) qUserInfo.zip.containsIgnoreCase(userProfileDto.getZip()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getCountry())) {
			builder.and((Predicate) qUserInfo.country.containsIgnoreCase(userProfileDto.getCountry()));
		}
		
		if (StringUtils.isNotBlank(userProfileDto.getPrimaryPhone())) {
			builder.and((Predicate) qUserInfo.primaryPhone.containsIgnoreCase(userProfileDto.getPrimaryPhone()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getSecondaryPhone())) {
			builder.and((Predicate) qUserInfo.secondaryPhone.containsIgnoreCase(userProfileDto.getSecondaryPhone()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getFax())) {
			builder.and((Predicate) qUserInfo.fax.containsIgnoreCase(userProfileDto.getFax()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getEmailAddress())) {
			builder.and((Predicate) qUserInfo.emailAddress.containsIgnoreCase(userProfileDto.getEmailAddress()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getDateFormat())) {
			builder.and((Predicate) qUserInfo.dateFormat.containsIgnoreCase(userProfileDto.getDateFormat()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getTimeFormat())) {
			builder.and((Predicate) qUserInfo.timeFormat.containsIgnoreCase(userProfileDto.getTimeFormat()));
		}

		if (StringUtils.isNotBlank(userProfileDto.getTimeZone())) {
			builder.and((Predicate) qUserInfo.timeZone.containsIgnoreCase(userProfileDto.getTimeZone()));
		}

		if (userProfileDto.getLocale() != null) {
			if (StringUtils.isNotBlank(userProfileDto.getLocale().getLanguage())) {
				builder.and((Predicate) qUserInfo.localeLanguage.containsIgnoreCase(userProfileDto.getLocale().getLanguage()));
			}
			if (StringUtils.isNotBlank(userProfileDto.getLocale().getCountry())) {
				builder.and((Predicate) qUserInfo.localeCountry.containsIgnoreCase(userProfileDto.getLocale().getCountry()));
			}
			if (StringUtils.isNotBlank(userProfileDto.getLocale().getVariant())) {
				builder.and((Predicate) qUserInfo.localeVariant.containsIgnoreCase(userProfileDto.getLocale().getVariant()));
			}
		}

		if (StringUtils.isNotBlank(userProfileDto.getLogin())) {
			builder.and((Predicate) qUserInfo.login.containsIgnoreCase(userProfileDto.getLogin()));
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
