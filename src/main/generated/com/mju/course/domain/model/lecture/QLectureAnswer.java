package com.mju.course.domain.model.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureAnswer is a Querydsl query type for LectureAnswer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureAnswer extends EntityPathBase<LectureAnswer> {

    private static final long serialVersionUID = -324326355L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureAnswer lectureAnswer1 = new QLectureAnswer("lectureAnswer1");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lectureAnswer = createString("lectureAnswer");

    public final ListPath<LectureAnswerPhoto, QLectureAnswerPhoto> lectureAnswerPhotoList = this.<LectureAnswerPhoto, QLectureAnswerPhoto>createList("lectureAnswerPhotoList", LectureAnswerPhoto.class, QLectureAnswerPhoto.class, PathInits.DIRECT2);

    public final QLectureQuestion lectureQuestion;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.mju.course.domain.model.QUser user;

    public QLectureAnswer(String variable) {
        this(LectureAnswer.class, forVariable(variable), INITS);
    }

    public QLectureAnswer(Path<? extends LectureAnswer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureAnswer(PathMetadata metadata, PathInits inits) {
        this(LectureAnswer.class, metadata, inits);
    }

    public QLectureAnswer(Class<? extends LectureAnswer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lectureQuestion = inits.isInitialized("lectureQuestion") ? new QLectureQuestion(forProperty("lectureQuestion"), inits.get("lectureQuestion")) : null;
        this.user = inits.isInitialized("user") ? new com.mju.course.domain.model.QUser(forProperty("user")) : null;
    }

}

