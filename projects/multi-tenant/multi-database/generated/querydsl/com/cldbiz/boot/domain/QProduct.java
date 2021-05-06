package com.cldbiz.boot.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1493474344L;

    public static final QProduct product = new QProduct("product");

    public final QEntityDomain _super = new QEntityDomain(this);

    public final StringPath code = createString("code");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRanageEnd = createNumber("idRanageEnd", Long.class);

    public final NumberPath<Long> idRangeStart = createNumber("idRangeStart", Long.class);

    public final StringPath keyName = createString("keyName");

    public final StringPath landingPage = createString("landingPage");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.sql.Timestamp> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> sortOrder = createNumber("sortOrder", Long.class);

    public final BooleanPath status = createBoolean("status");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

