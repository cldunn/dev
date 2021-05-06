package com.cldbiz.afw.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserInfoRole is a Querydsl query type for UserInfoRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserInfoRole extends EntityPathBase<UserInfoRole> {

    private static final long serialVersionUID = -336834188L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserInfoRole userInfoRole = new QUserInfoRole("userInfoRole");

    public final QBaseDomain _super = new QBaseDomain(this);

    public final QUserInfoRolePK pk;

    public QUserInfoRole(String variable) {
        this(UserInfoRole.class, forVariable(variable), INITS);
    }

    public QUserInfoRole(Path<? extends UserInfoRole> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserInfoRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserInfoRole(PathMetadata metadata, PathInits inits) {
        this(UserInfoRole.class, metadata, inits);
    }

    public QUserInfoRole(Class<? extends UserInfoRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new QUserInfoRolePK(forProperty("pk")) : null;
    }

}

