package com.mju.course.domain.model.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCourse is a Querydsl query type for UserCourse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCourse extends EntityPathBase<UserCourse> {

    private static final long serialVersionUID = -1311334918L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCourse userCourse = new QUserCourse("userCourse");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    public final QCourse course;

    public final BooleanPath courseStatus = createBoolean("courseStatus");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userId = createString("userId");

    public QUserCourse(String variable) {
        this(UserCourse.class, forVariable(variable), INITS);
    }

    public QUserCourse(Path<? extends UserCourse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCourse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCourse(PathMetadata metadata, PathInits inits) {
        this(UserCourse.class, metadata, inits);
    }

    public QUserCourse(Class<? extends UserCourse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course")) : null;
    }

}

