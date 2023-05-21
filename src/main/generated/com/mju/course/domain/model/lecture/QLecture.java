package com.mju.course.domain.model.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLecture is a Querydsl query type for Lecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLecture extends EntityPathBase<Lecture> {

    private static final long serialVersionUID = 1129981839L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLecture lecture = new QLecture("lecture");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.mju.course.domain.model.course.QCurriculum curriculum;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lectureDescription = createString("lectureDescription");

    public final StringPath lectureInputKey = createString("lectureInputKey");

    public final StringPath lectureKey = createString("lectureKey");

    public final ListPath<LectureQuestion, QLectureQuestion> lectureQuestionList = this.<LectureQuestion, QLectureQuestion>createList("lectureQuestionList", LectureQuestion.class, QLectureQuestion.class, PathInits.DIRECT2);

    public final NumberPath<Integer> lectureSequence = createNumber("lectureSequence", Integer.class);

    public final NumberPath<Integer> lectureTime = createNumber("lectureTime", Integer.class);

    public final StringPath lectureTitle = createString("lectureTitle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLecture(String variable) {
        this(Lecture.class, forVariable(variable), INITS);
    }

    public QLecture(Path<? extends Lecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLecture(PathMetadata metadata, PathInits inits) {
        this(Lecture.class, metadata, inits);
    }

    public QLecture(Class<? extends Lecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new com.mju.course.domain.model.course.QCurriculum(forProperty("curriculum"), inits.get("curriculum")) : null;
    }

}

