package com.mju.course.domain.model.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureAnswerPhoto is a Querydsl query type for LectureAnswerPhoto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureAnswerPhoto extends EntityPathBase<LectureAnswerPhoto> {

    private static final long serialVersionUID = -1395476987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureAnswerPhoto lectureAnswerPhoto = new QLectureAnswerPhoto("lectureAnswerPhoto");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLectureAnswer lectureAnswer;

    public final StringPath lectureAnswerPhotoKey = createString("lectureAnswerPhotoKey");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLectureAnswerPhoto(String variable) {
        this(LectureAnswerPhoto.class, forVariable(variable), INITS);
    }

    public QLectureAnswerPhoto(Path<? extends LectureAnswerPhoto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureAnswerPhoto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureAnswerPhoto(PathMetadata metadata, PathInits inits) {
        this(LectureAnswerPhoto.class, metadata, inits);
    }

    public QLectureAnswerPhoto(Class<? extends LectureAnswerPhoto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lectureAnswer = inits.isInitialized("lectureAnswer") ? new QLectureAnswer(forProperty("lectureAnswer"), inits.get("lectureAnswer")) : null;
    }

}

