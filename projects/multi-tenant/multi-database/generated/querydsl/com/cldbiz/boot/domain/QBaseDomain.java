package com.cldbiz.boot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseDomain is a Querydsl query type for BaseDomain
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseDomain extends EntityPathBase<BaseDomain> {

    private static final long serialVersionUID = 762133356L;

    public static final QBaseDomain baseDomain = new QBaseDomain("baseDomain");

    public QBaseDomain(String variable) {
        super(BaseDomain.class, forVariable(variable));
    }

    public QBaseDomain(Path<? extends BaseDomain> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseDomain(PathMetadata metadata) {
        super(BaseDomain.class, metadata);
    }

}

