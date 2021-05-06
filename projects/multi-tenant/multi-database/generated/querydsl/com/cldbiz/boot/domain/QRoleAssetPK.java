package com.cldbiz.boot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoleAssetPK is a Querydsl query type for RoleAssetPK
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QRoleAssetPK extends BeanPath<RoleAssetPK> {

    private static final long serialVersionUID = -2061387266L;

    public static final QRoleAssetPK roleAssetPK = new QRoleAssetPK("roleAssetPK");

    public final QBaseDomain _super = new QBaseDomain(this);

    public final NumberPath<Long> assetId = createNumber("assetId", Long.class);

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public QRoleAssetPK(String variable) {
        super(RoleAssetPK.class, forVariable(variable));
    }

    public QRoleAssetPK(Path<? extends RoleAssetPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoleAssetPK(PathMetadata metadata) {
        super(RoleAssetPK.class, metadata);
    }

}

