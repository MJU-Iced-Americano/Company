package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.QCourse;
import com.mju.course.domain.model.enums.CourseState;
import com.mju.course.presentation.dto.response.admin.AdminReadCoursesDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseRepositoryImpl implements CourseRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CourseRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AdminReadCoursesDto> readCourses(String state) {
        QCourse course = QCourse.course;
        if(state.equals("seq")){
            return queryFactory.select(Projections.constructor(AdminReadCoursesDto.class,
                            course.id, course.status, course.category,
                            course.difficulty, course.courseName, course.skill,
                            course.price, course.courseTime, course.createdAt, course.hits))
                    .from(course)
                    .fetch();
        }else{
            CourseState courseState = Enum.valueOf(CourseState.class, state.toLowerCase());
            return queryFactory.select(Projections.constructor(AdminReadCoursesDto.class,
                            course.id, course.status, course.category,
                            course.difficulty, course.courseName, course.skill,
                            course.price, course.courseTime, course.createdAt, course.hits))
                    .from(course)
                    .where(course.status.eq(courseState))
                    .fetch();
        }

    }
}
