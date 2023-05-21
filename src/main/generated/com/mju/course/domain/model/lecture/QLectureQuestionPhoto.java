package com.mju.course.domain.model.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureQuestionPhoto is a Querydsl query type for LectureQuestionPhoto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureQuestionPhoto extends EntityPathBase<LectureQuestionPhoto> {

    private static final long serialVersionUID = 1692244637L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureQuestionPhoto lectureQuestionPhoto = new QLectureQuestionPhoto("lectureQuestionPhoto");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLectureQuestion lectureQuestion;

    public final StringPath lectureQuestionPhotoKey = createString("lectureQuestionPhotoKey");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLectureQuestionPhoto(String variable) {
        this(LectureQuestionPhoto.class, forVariable(variable), INITS);
    }

    public QLectureQuestionPhoto(Path<? extends LectureQuestionPhoto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureQuestionPhoto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureQuestionPhoto(PathMetadata metadata, PathInits inits) {
        this(LectureQuestionPhoto.class, metadata, inits);
    }

    public QLectureQuestionPhoto(Class<? extends LectureQuestionPhoto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lectureQuestion = inits.isInitialized("lectureQuestion") ? new QLectureQuestion(forProperty("lectureQuestion"), inits.get("lectureQuestion")) : null;
    }

}

