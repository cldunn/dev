package com.cldbiz.afw.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoleResourcePK is a Querydsl query type for RoleResourcePK
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QRoleResourcePK extends BeanPath<RoleResourcePK> {

    private static final long serialVersionUID = -885854044L;

    public static final QRoleResourcePK roleResourcePK = new QRoleResourcePK("roleResourcePK");

    public final QBaseDomain _super = new QBaseDomain(this);

    public final NumberPath<Long> resourceId = createNumber("resourceId", Long.class);

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public QRoleResourcePK(String variable) {
        super(RoleResourcePK.class, forVariable(variable));
    }

    public QRoleResourcePK(Path<? extends RoleResourcePK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoleResourcePK(PathMetadata metadata) {
        super(RoleResourcePK.class, metadata);
    }

}

