package com.mju.course.domain.repository.lecture;

import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.domain.model.lecture.LectureQuestion;
import com.mju.course.domain.model.lecture.QLectureQuestion;
import com.mju.course.presentation.dto.response.LectureQuestionReadDto;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;

public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LectureRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<LectureQuestionReadDto> readQuestions(Lecture lecture, Pageable pageable) {
        QLectureQuestion lectureQuestion = QLectureQuestion.lectureQuestion1;

        JPQLQuery<LectureQuestion> query = queryFactory.selectFrom(lectureQuestion)
                .where(lectureQuestion.lecture.eq(lecture))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<LectureQuestion> lectureQuestionList = query.fetch();

        List<LectureQuestionReadDto> content = new ArrayList<>();
        lectureQuestionList.forEach(s->{
            content.add(LectureQuestionReadDto.of(s));
        });

        JPAQuery<LectureQuestion> countQuery = queryFactory.selectFrom(lectureQuestion);

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
    }

}
