package com.cldbiz.boot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUniqueId is a Querydsl query type for UniqueId
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUniqueId extends EntityPathBase<UniqueId> {

    private static final long serialVersionUID = 1656236803L;

    public static final QUniqueId uniqueId = new QUniqueId("uniqueId");

    public final StringPath idName = createString("idName");

    public final NumberPath<Long> nextId = createNumber("nextId", Long.class);

    public QUniqueId(String variable) {
        super(UniqueId.class, forVariable(variable));
    }

    public QUniqueId(Path<? extends UniqueId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUniqueId(PathMetadata metadata) {
        super(UniqueId.class, metadata);
    }

}

