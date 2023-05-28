package com.mju.course.domain.model.course;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = -1782889393L;

    public static final QCourse course = new QCourse("course");

    public final com.mju.course.domain.model.QBaseTimeEntity _super = new com.mju.course.domain.model.QBaseTimeEntity(this);

    public final ListPath<Cart, QCart> cartList = this.<Cart, QCart>createList("cartList", Cart.class, QCart.class, PathInits.DIRECT2);

    public final StringPath category = createString("category");

    public final StringPath comment = createString("comment");

    public final StringPath courseDescription = createString("courseDescription");

    public final ListPath<CourseLike, QCourseLike> courseLikeList = this.<CourseLike, QCourseLike>createList("courseLikeList", CourseLike.class, QCourseLike.class, PathInits.DIRECT2);

    public final StringPath courseName = createString("courseName");

    public final NumberPath<Integer> courseTime = createNumber("courseTime", Integer.class);

    public final StringPath courseTitlePhotoKey = createString("courseTitlePhotoKey");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<Curriculum, QCurriculum> curriculumList = this.<Curriculum, QCurriculum>createList("curriculumList", Curriculum.class, QCurriculum.class, PathInits.DIRECT2);

    public final NumberPath<Integer> difficulty = createNumber("difficulty", Integer.class);

    public final NumberPath<Long> hits = createNumber("hits", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final ListPath<Skill, QSkill> skillList = this.<Skill, QSkill>createList("skillList", Skill.class, QSkill.class, PathInits.DIRECT2);

    public final EnumPath<com.mju.course.domain.model.enums.CourseState> status = createEnum("status", com.mju.course.domain.model.enums.CourseState.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<UserCourse, QUserCourse> userCourseList = this.<UserCourse, QUserCourse>createList("userCourseList", UserCourse.class, QUserCourse.class, PathInits.DIRECT2);

    public QCourse(String variable) {
        super(Course.class, forVariable(variable));
    }

    public QCourse(Path<? extends Course> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourse(PathMetadata metadata) {
        super(Course.class, metadata);
    }

}

