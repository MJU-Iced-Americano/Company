package com.mju.course.domain.model.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureQuestionBookmark is a Querydsl query type for LectureQuestionBookmark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureQuestionBookmark extends EntityPathBase<LectureQuestionBookmark> {

    private static final long serialVersionUID = -1681363733L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureQuestionBookmark lectureQuestionBookmark = new QLectureQuestionBookmark("lectureQuestionBookmark");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLectureQuestion lectureQuestion;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userId = createString("userId");

    public QLectureQuestionBookmark(String variable) {
        this(LectureQuestionBookmark.class, forVariable(variable), INITS);
    }

    public QLectureQuestionBookmark(Path<? extends LectureQuestionBookmark> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureQuestionBookmark(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureQuestionBookmark(PathMetadata metadata, PathInits inits) {
        this(LectureQuestionBookmark.class, metadata, inits);
    }

    public QLectureQuestionBookmark(Class<? extends LectureQuestionBookmark> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lectureQuestion = inits.isInitialized("lectureQuestion") ? new QLectureQuestion(forProperty("lectureQuestion"), inits.get("lectureQuestion")) : null;
    }

}

