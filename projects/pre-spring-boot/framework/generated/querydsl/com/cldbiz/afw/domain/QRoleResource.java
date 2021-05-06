package com.cldbiz.afw.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoleResource is a Querydsl query type for RoleResource
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoleResource extends EntityPathBase<RoleResource> {

    private static final long serialVersionUID = -1998684951L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleResource roleResource = new QRoleResource("roleResource");

    public final QBaseDomain _super = new QBaseDomain(this);

    public final BooleanPath isCreatable = createBoolean("isCreatable");

    public final BooleanPath isDeletable = createBoolean("isDeletable");

    public final BooleanPath isReadable = createBoolean("isReadable");

    public final BooleanPath isUpdatable = createBoolean("isUpdatable");

    public final QRoleResourcePK pk;

    public final QResource resource;

    public QRoleResource(String variable) {
        this(RoleResource.class, forVariable(variable), INITS);
    }

    public QRoleResource(Path<? extends RoleResource> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoleResource(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoleResource(PathMetadata metadata, PathInits inits) {
        this(RoleResource.class, metadata, inits);
    }

    public QRoleResource(Class<? extends RoleResource> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new QRoleResourcePK(forProperty("pk")) : null;
        this.resource = inits.isInitialized("resource") ? new QResource(forProperty("resource")) : null;
    }

}

