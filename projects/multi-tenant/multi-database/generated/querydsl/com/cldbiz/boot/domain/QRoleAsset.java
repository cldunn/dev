package com.cldbiz.boot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoleAsset is a Querydsl query type for RoleAsset
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoleAsset extends EntityPathBase<RoleAsset> {

    private static final long serialVersionUID = 650368195L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleAsset roleAsset = new QRoleAsset("roleAsset");

    public final QBaseDomain _super = new QBaseDomain(this);

    public final QAsset asset;

    public final BooleanPath isCreatable = createBoolean("isCreatable");

    public final BooleanPath isDeletable = createBoolean("isDeletable");

    public final BooleanPath isReadable = createBoolean("isReadable");

    public final BooleanPath isUpdatable = createBoolean("isUpdatable");

    public final QRoleAssetPK pk;

    public QRoleAsset(String variable) {
        this(RoleAsset.class, forVariable(variable), INITS);
    }

    public QRoleAsset(Path<? extends RoleAsset> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoleAsset(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoleAsset(PathMetadata metadata, PathInits inits) {
        this(RoleAsset.class, metadata, inits);
    }

    public QRoleAsset(Class<? extends RoleAsset> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.asset = inits.isInitialized("asset") ? new QAsset(forProperty("asset")) : null;
        this.pk = inits.isInitialized("pk") ? new QRoleAssetPK(forProperty("pk")) : null;
    }

}

