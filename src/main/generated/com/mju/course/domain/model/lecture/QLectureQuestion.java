package com.mju.course.domain.model.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureQuestion is a Querydsl query type for LectureQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureQuestion extends EntityPathBase<LectureQuestion> {

    private static final long serialVersionUID = 1188677781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureQuestion lectureQuestion1 = new QLectureQuestion("lectureQuestion1");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLecture lecture;

    public final ListPath<LectureAnswer, QLectureAnswer> lectureAnswerList = this.<LectureAnswer, QLectureAnswer>createList("lectureAnswerList", LectureAnswer.class, QLectureAnswer.class, PathInits.DIRECT2);

    public final StringPath lectureQuestion = createString("lectureQuestion");

    public final ListPath<LectureQuestionPhoto, QLectureQuestionPhoto> lectureQuestionPhotoList = this.<LectureQuestionPhoto, QLectureQuestionPhoto>createList("lectureQuestionPhotoList", LectureQuestionPhoto.class, QLectureQuestionPhoto.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.mju.course.domain.model.QUser user;

    public QLectureQuestion(String variable) {
        this(LectureQuestion.class, forVariable(variable), INITS);
    }

    public QLectureQuestion(Path<? extends LectureQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureQuestion(PathMetadata metadata, PathInits inits) {
        this(LectureQuestion.class, metadata, inits);
    }

    public QLectureQuestion(Class<? extends LectureQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.user = inits.isInitialized("user") ? new com.mju.course.domain.model.QUser(forProperty("user")) : null;
    }

}

