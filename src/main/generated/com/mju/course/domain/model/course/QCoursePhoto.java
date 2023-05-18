package com.mju.course.domain.model.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoursePhoto is a Querydsl query type for CoursePhoto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoursePhoto extends EntityPathBase<CoursePhoto> {

    private static final long serialVersionUID = 1544971043L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoursePhoto coursePhoto = new QCoursePhoto("coursePhoto");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    public final QCourse course;

    public final StringPath coursePhotoKey = createString("coursePhotoKey");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCoursePhoto(String variable) {
        this(CoursePhoto.class, forVariable(variable), INITS);
    }

    public QCoursePhoto(Path<? extends CoursePhoto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoursePhoto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoursePhoto(PathMetadata metadata, PathInits inits) {
        this(CoursePhoto.class, metadata, inits);
    }

    public QCoursePhoto(Class<? extends CoursePhoto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course")) : null;
    }

}

