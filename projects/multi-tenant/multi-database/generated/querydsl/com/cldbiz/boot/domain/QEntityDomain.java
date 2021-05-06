package com.cldbiz.boot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEntityDomain is a Querydsl query type for EntityDomain
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QEntityDomain extends EntityPathBase<EntityDomain> {

    private static final long serialVersionUID = -1189068482L;

    public static final QEntityDomain entityDomain = new QEntityDomain("entityDomain");

    public final QBaseDomain _super = new QBaseDomain(this);

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public final DateTimePath<java.sql.Timestamp> modifiedDate = createDateTime("modifiedDate", java.sql.Timestamp.class);

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QEntityDomain(String variable) {
        super(EntityDomain.class, forVariable(variable));
    }

    public QEntityDomain(Path<? extends EntityDomain> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntityDomain(PathMetadata metadata) {
        super(EntityDomain.class, metadata);
    }

}

