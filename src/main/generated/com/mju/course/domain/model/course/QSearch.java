package com.mju.course.domain.model.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSearch is a Querydsl query type for Search
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSearch extends EntityPathBase<Search> {

    private static final long serialVersionUID = -1334654500L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSearch search1 = new QSearch("search1");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath search = createString("search");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.mju.course.domain.model.QUser user;

    public QSearch(String variable) {
        this(Search.class, forVariable(variable), INITS);
    }

    public QSearch(Path<? extends Search> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSearch(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSearch(PathMetadata metadata, PathInits inits) {
        this(Search.class, metadata, inits);
    }

    public QSearch(Class<? extends Search> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.mju.course.domain.model.QUser(forProperty("user")) : null;
    }

}

