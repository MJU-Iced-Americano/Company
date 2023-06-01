package com.mju.course.domain.model.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourseLike is a Querydsl query type for CourseLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourseLike extends EntityPathBase<CourseLike> {

    private static final long serialVersionUID = 49719430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseLike courseLike = new QCourseLike("courseLike");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    public final QCourse course;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userId = createString("userId");

    public QCourseLike(String variable) {
        this(CourseLike.class, forVariable(variable), INITS);
    }

    public QCourseLike(Path<? extends CourseLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourseLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourseLike(PathMetadata metadata, PathInits inits) {
        this(CourseLike.class, metadata, inits);
    }

    public QCourseLike(Class<? extends CourseLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course")) : null;
    }

}

