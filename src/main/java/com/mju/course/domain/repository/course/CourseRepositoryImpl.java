package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.QCourse;
import com.mju.course.domain.model.enums.CourseState;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import com.mju.course.presentation.dto.response.admin.AdminReadCoursesDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class CourseRepositoryImpl implements CourseRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CourseRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public static OrderSpecifier<?> getOrderByExpression(String orderTarget, QCourse course) {
        PathBuilder<?> orderPath = new PathBuilder<>(course.getType(), course.getMetadata());
        return new OrderSpecifier(Order.DESC, orderPath.get(orderTarget));
    }

    public List<AdminReadCoursesDto> readCourses(String state, String orderTarget) {
        QCourse course = QCourse.course;
        BooleanBuilder builder = new BooleanBuilder();

        if (!state.equals("all")) {
            CourseState courseState = Enum.valueOf(CourseState.class, state.toLowerCase());
            builder.and(course.status.eq(courseState));
        }
        OrderSpecifier<?> orderSpecifier = getOrderByExpression(orderTarget, course);
        return queryFactory
                .select(Projections.constructor(
                        AdminReadCoursesDto.class,
                        course.id, course.status, course.category,
                        course.difficulty, course.courseName, course.skill,
                        course.price, course.courseTime, course.createdAt, course.hits))
                .from(course)
                .where(builder)
                .orderBy(orderSpecifier)
                .fetch();
    }

    public Page<AdminReadCoursesDto> readCoursesPageSimple(String state, String order, Pageable pageable) {
        QCourse course = QCourse.course;
        BooleanBuilder builder = new BooleanBuilder();

        if (!state.equals("all")) {
            CourseState courseState = Enum.valueOf(CourseState.class, state.toLowerCase());
            builder.and(course.status.eq(courseState));
        }
        OrderSpecifier<?> orderSpecifier = getOrderByExpression(order, course);

        QueryResults<AdminReadCoursesDto> results = queryFactory
                .select(Projections.constructor(
                        AdminReadCoursesDto.class,
                        course.id, course.status, course.category,
                        course.difficulty, course.courseName, course.skill,
                        course.price, course.courseTime, course.createdAt, course.hits))
                .from(course)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())  // 몇번 부터 시작할 것인가
                .limit(pageable.getPageSize()) // 한번 조회할 때 몇개 까지 조회할 것인가
                .fetchResults();

        List<AdminReadCoursesDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<AdminReadCoursesDto> readCoursesPageComplex(String state, String order, Pageable pageable) {
        QCourse course = QCourse.course;
        BooleanBuilder builder = new BooleanBuilder();

        if (!state.equals("all")) {
            CourseState courseState = Enum.valueOf(CourseState.class, state.toLowerCase());
            builder.and(course.status.eq(courseState));
        }
        OrderSpecifier<?> orderSpecifier = getOrderByExpression(order, course);

        List<AdminReadCoursesDto> content = queryFactory
                .select(Projections.constructor(
                        AdminReadCoursesDto.class,
                        course.id, course.status, course.category,
                        course.difficulty, course.courseName, course.skill,
                        course.price, course.courseTime, course.createdAt, course.hits))
                .from(course)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())  // 몇번 부터 시작할 것인가
                .limit(pageable.getPageSize()) // 한번 조회할 때 몇개 까지 조회할 것인가
                .fetch();

        JPAQuery<AdminReadCoursesDto> countQuery = queryFactory.select(Projections.constructor(
                        AdminReadCoursesDto.class,
                        course.id, course.status, course.category,
                        course.difficulty, course.courseName, course.skill,
                        course.price, course.courseTime, course.createdAt, course.hits))
                .from(course)
                .where(builder)
                .orderBy(orderSpecifier);

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());

//        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<CoursesReadDto> readCourseList(String order, String skill, Pageable pageable) {
        QCourse course = QCourse.course;

        // if skill이 존재한다면
        // - 우선 skill을 분리해야 함

        // if order이 존재한다면
//        List<CoursesReadDto> content = queryFactory
//                .select(course.id, course.courseName, course.price, course.difficulty, course.courseTitlePhotoKey)
//                .from(course)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();

        return null;
    }
}
