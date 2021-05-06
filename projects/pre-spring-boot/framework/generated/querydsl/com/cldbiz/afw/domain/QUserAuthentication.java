package com.cldbiz.afw.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAuthentication is a Querydsl query type for UserAuthentication
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserAuthentication extends EntityPathBase<UserAuthentication> {

    private static final long serialVersionUID = 980008360L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAuthentication userAuthentication = new QUserAuthentication("userAuthentication");

    public final QEntityDomain _super = new QEntityDomain(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath isLocked = createBoolean("isLocked");

    public final DateTimePath<java.util.Date> lastLoginDate = createDateTime("lastLoginDate", java.util.Date.class);

    public final StringPath login = createString("login");

    public final NumberPath<Integer> loginAttempts = createNumber("loginAttempts", Integer.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.sql.Timestamp> modifiedDate = _super.modifiedDate;

    public final StringPath password = createString("password");

    public final DatePath<java.util.Date> passwordExpireDate = createDate("passwordExpireDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> passwordModifiedDate = createDateTime("passwordModifiedDate", java.util.Date.class);

    public final StringPath status = createString("status");

    public final QUserInfo userInfo;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QUserAuthentication(String variable) {
        this(UserAuthentication.class, forVariable(variable), INITS);
    }

    public QUserAuthentication(Path<? extends UserAuthentication> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAuthentication(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAuthentication(PathMetadata metadata, PathInits inits) {
        this(UserAuthentication.class, metadata, inits);
    }

    public QUserAuthentication(Class<? extends UserAuthentication> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}

