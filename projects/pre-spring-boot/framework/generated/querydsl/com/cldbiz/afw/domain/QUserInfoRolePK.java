package com.cldbiz.afw.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserInfoRolePK is a Querydsl query type for UserInfoRolePK
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUserInfoRolePK extends BeanPath<UserInfoRolePK> {

    private static final long serialVersionUID = -1575104913L;

    public static final QUserInfoRolePK userInfoRolePK = new QUserInfoRolePK("userInfoRolePK");

    public final QBaseDomain _super = new QBaseDomain(this);

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public final NumberPath<Long> userInfoId = createNumber("userInfoId", Long.class);

    public QUserInfoRolePK(String variable) {
        super(UserInfoRolePK.class, forVariable(variable));
    }

    public QUserInfoRolePK(Path<? extends UserInfoRolePK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserInfoRolePK(PathMetadata metadata) {
        super(UserInfoRolePK.class, metadata);
    }

}

