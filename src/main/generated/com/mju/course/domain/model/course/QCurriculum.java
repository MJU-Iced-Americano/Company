package com.mju.course.domain.model.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCurriculum is a Querydsl query type for Curriculum
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCurriculum extends EntityPathBase<Curriculum> {

    private static final long serialVersionUID = 869516911L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCurriculum curriculum = new QCurriculum("curriculum");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    public final NumberPath<Integer> chapter = createNumber("chapter", Integer.class);

    public final QCourse course;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath curriculumTitle = createString("curriculumTitle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.mju.course.domain.model.lecture.Lecture, com.mju.course.domain.model.lecture.QLecture> lectureList = this.<com.mju.course.domain.model.lecture.Lecture, com.mju.course.domain.model.lecture.QLecture>createList("lectureList", com.mju.course.domain.model.lecture.Lecture.class, com.mju.course.domain.model.lecture.QLecture.class, PathInits.DIRECT2);

    public final NumberPath<Integer> lectureSum = createNumber("lectureSum", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCurriculum(String variable) {
        this(Curriculum.class, forVariable(variable), INITS);
    }

    public QCurriculum(Path<? extends Curriculum> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCurriculum(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCurriculum(PathMetadata metadata, PathInits inits) {
        this(Curriculum.class, metadata, inits);
    }

    public QCurriculum(Class<? extends Curriculum> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course")) : null;
    }

}

