package com.cldbiz.boot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAsset is a Querydsl query type for Asset
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAsset extends EntityPathBase<Asset> {

    private static final long serialVersionUID = 1075128345L;

    public static final QAsset asset = new QAsset("asset");

    public final QEntityDomain _super = new QEntityDomain(this);

    public final BooleanPath active = createBoolean("active");

    public final ListPath<Asset, QAsset> children = this.<Asset, QAsset>createList("children", Asset.class, QAsset.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath keyName = createString("keyName");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.sql.Timestamp> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath type = createString("type");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QAsset(String variable) {
        super(Asset.class, forVariable(variable));
    }

    public QAsset(Path<? extends Asset> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAsset(PathMetadata metadata) {
        super(Asset.class, metadata);
    }

}

