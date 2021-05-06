package com.cldbiz.boot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

    private static final long serialVersionUID = 1683893584L;

    public static final QUserInfo userInfo = new QUserInfo("userInfo");

    public final QEntityDomain _super = new QEntityDomain(this);

    public final StringPath addressLine1 = createString("addressLine1");

    public final StringPath addressLine2 = createString("addressLine2");

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    public final StringPath dateFormat = createString("dateFormat");

    public final StringPath emailAddress = createString("emailAddress");

    public final StringPath fax = createString("fax");

    public final StringPath firstName = createString("firstName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath isLocked = createBoolean("isLocked");

    public final DateTimePath<java.util.Date> lastLoginDate = createDateTime("lastLoginDate", java.util.Date.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath localeCountry = createString("localeCountry");

    public final StringPath localeLanguage = createString("localeLanguage");

    public final StringPath localeVariant = createString("localeVariant");

    public final StringPath login = createString("login");

    public final NumberPath<Integer> loginAttempts = createNumber("loginAttempts", Integer.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.sql.Timestamp> modifiedDate = _super.modifiedDate;

    public final StringPath password = createString("password");

    public final DatePath<java.util.Date> passwordExpireDate = createDate("passwordExpireDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> passwordModifiedDate = createDateTime("passwordModifiedDate", java.util.Date.class);

    public final StringPath primaryPhone = createString("primaryPhone");

    public final StringPath province = createString("province");

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath secondaryPhone = createString("secondaryPhone");

    public final StringPath state = createString("state");

    public final StringPath status = createString("status");

    public final StringPath timeFormat = createString("timeFormat");

    public final StringPath timeZone = createString("timeZone");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final StringPath zip = createString("zip");

    public QUserInfo(String variable) {
        super(UserInfo.class, forVariable(variable));
    }

    public QUserInfo(Path<? extends UserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserInfo(PathMetadata metadata) {
        super(UserInfo.class, metadata);
    }

}

