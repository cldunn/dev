package com.cldbiz.afw.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cldbiz.afw.domain.QUserAuthentication;
import com.cldbiz.afw.domain.UserAuthentication;
import com.cldbiz.afw.domain.UserInfo;
import com.cldbiz.afw.dto.PageReqDto;
import com.cldbiz.afw.dto.UserAuthenticationDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Component
public class UserAuthenticationDaoImpl extends BaseDaoImpl<UserAuthentication> implements UserAuthenticationDao {
	@Resource
	private UserInfoDao userInfoDao;

	@Override
	public UserAuthentication findUserAuthentication(Long userAuthenticationId) {
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUserAuthentication.id.eq(userAuthenticationId));
		
		return jpaQueryFactory.selectFrom(qUserAuthentication)
				.where(builder)
				.fetchOne();
	}

	@Override
	public Boolean isDuplicateUserAuthentication(UserAuthenticationDto userAuthenticationDto) {
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		BooleanBuilder builder = new BooleanBuilder();
		if (userAuthenticationDto.getId() != null && userAuthenticationDto.getId() > 0) {
			builder.and(qUserAuthentication.id.ne(userAuthenticationDto.getId()));
		}
		builder.and(qUserAuthentication.login.eq(userAuthenticationDto.getLogin()));
		
		return jpaQueryFactory.from(qUserAuthentication)
				.where(builder)
				.fetchCount() > 0 ? true : false;
	}

	@Override
	public Long countUserAuthentications(UserAuthenticationDto userAuthenticationDto) {
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		return jpaQueryFactory.from(qUserAuthentication)
				.where(findPredicate(userAuthenticationDto))
				.fetchCount();
	}

	@Override
	public List<UserAuthentication> findUserAuthentications(UserAuthenticationDto userAuthenticationDto) {
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		return jpaQueryFactory.selectFrom(qUserAuthentication)
				.where(findPredicate(userAuthenticationDto))
				.orderBy(sortByLogin())
				.fetch();
	}

	@Override
	public List<UserAuthentication> searchUserAuthentications(UserAuthenticationDto userAuthenticationDto) {
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		return jpaQueryFactory.selectFrom(qUserAuthentication)
				.where(searchPredicate(userAuthenticationDto))
				.orderBy(sortByLogin())
				.fetch();
	}

	@Override
	public List<UserAuthentication> searchUserAuthenticationsPage(PageReqDto<UserAuthenticationDto> pageReqDto) {
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		return jpaQueryFactory.selectFrom(qUserAuthentication)
				.where(searchPredicate(pageReqDto.getCriteria()))
				.orderBy(sortByLogin())
				.offset(pageReqDto.getStart().intValue())
				.limit(pageReqDto.getLimit().intValue())
				.fetch();
	}

	@Override
	public void deleteUserAuthentications(List<Long> userAuthenticationIds) {
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUserAuthentication.id.in(userAuthenticationIds));
		
		/* Requires a delete trigger on USER_AUTHENTICATION for */
		jpaQueryFactory.delete(qUserAuthentication)
			.where(builder).execute();
		
		entityManager.flush();
		entityManager.clear();
	}

	public void removeUserAuthentications(List<Long> userAuthenticationIds) {
		Integer blockSz = 1;  // 50
		Integer fromNdx = 0;
		Integer toNdx = 0;
		
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;

		while (fromNdx < userAuthenticationIds.size()) {
			toNdx = Math.min(fromNdx + blockSz, userAuthenticationIds.size());

			BooleanBuilder builder = new BooleanBuilder();
			builder.and(qUserAuthentication.id.in(userAuthenticationIds.subList(fromNdx, toNdx)));
			remove(jpaQueryFactory.selectFrom(qUserAuthentication).where(builder).fetch());
			
			fromNdx = Math.min(toNdx, userAuthenticationIds.size());
		}
	}

	@Override
	public UserAuthentication saveUserAuthentication(UserAuthentication userAuthentication) {
		return save(userAuthentication);
	}

	@Override
	public List<UserAuthentication> saveUserAuthentications(List<UserAuthentication> userAuthentications) {
		return save(userAuthentications);
	}

	public static Predicate findPredicate(UserAuthenticationDto userAuthenticationDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		if (StringUtils.isNotBlank(userAuthenticationDto.getLogin())) {
			builder.and((Predicate) qUserAuthentication.login.equalsIgnoreCase(userAuthenticationDto.getLogin()));
		}

		if (userAuthenticationDto.getLoginAttempts() != null) {
			builder.and((Predicate) qUserAuthentication.loginAttempts.eq(userAuthenticationDto.getLoginAttempts()));
		}

		if (userAuthenticationDto.getLastLoginDate() != null) {
			builder.and((Predicate) qUserAuthentication.lastLoginDate.eq(userAuthenticationDto.getLastLoginDate()));
		}
		
		if (StringUtils.isNotBlank(userAuthenticationDto.getPassword())) {
			builder.and((Predicate) qUserAuthentication.password.equalsIgnoreCase(userAuthenticationDto.getPassword()));
		}

		if (userAuthenticationDto.getPasswordModifiedDate() != null) {
			builder.and((Predicate) qUserAuthentication.passwordModifiedDate.eq(userAuthenticationDto.getPasswordModifiedDate()));
		}

		if (userAuthenticationDto.getPasswordExpireDate() != null) {
			builder.and((Predicate) qUserAuthentication.passwordExpireDate.eq(userAuthenticationDto.getPasswordExpireDate()));
		}
		
		if (StringUtils.isNotBlank(userAuthenticationDto.getStatus())) {
			builder.and((Predicate) qUserAuthentication.status.equalsIgnoreCase(userAuthenticationDto.getStatus()));
		}
		
		return builder;
	}

	public static Predicate searchPredicate(UserAuthenticationDto userAuthenticationDto) {
		BooleanBuilder builder = new BooleanBuilder();
		QUserAuthentication qUserAuthentication = QUserAuthentication.userAuthentication;
		
		if (StringUtils.isNotBlank(userAuthenticationDto.getLogin())) {
			builder.and((Predicate) qUserAuthentication.login.containsIgnoreCase(userAuthenticationDto.getLogin()));
		}

		if (userAuthenticationDto.getLoginAttempts() != null) {
			builder.and((Predicate) qUserAuthentication.loginAttempts.eq(userAuthenticationDto.getLoginAttempts()));
		}

		if (userAuthenticationDto.getLastLoginDate() != null) {
			builder.and((Predicate) qUserAuthentication.lastLoginDate.eq(userAuthenticationDto.getLastLoginDate()));
		}
		
		if (StringUtils.isNotBlank(userAuthenticationDto.getPassword())) {
			builder.and((Predicate) qUserAuthentication.password.containsIgnoreCase(userAuthenticationDto.getPassword()));
		}

		if (userAuthenticationDto.getPasswordModifiedDate() != null) {
			builder.and((Predicate) qUserAuthentication.passwordModifiedDate.eq(userAuthenticationDto.getPasswordModifiedDate()));
		}

		if (userAuthenticationDto.getPasswordExpireDate() != null) {
			builder.and((Predicate) qUserAuthentication.passwordExpireDate.eq(userAuthenticationDto.getPasswordExpireDate()));
		}
		
		if (StringUtils.isNotBlank(userAuthenticationDto.getStatus())) {
			builder.and((Predicate) qUserAuthentication.status.eq(userAuthenticationDto.getStatus()));
		}
		
		return builder;
	}
	
	private OrderSpecifier[] sortByLogin() {
		return new OrderSpecifier[] {
			QUserAuthentication.userAuthentication.login.asc()
		};
	}

}
