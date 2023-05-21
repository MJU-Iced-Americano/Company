package com.mju.course.domain.repository.lecture;

import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.presentation.dto.response.LectureQuestionReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LectureRepositoryCustom {
    Page<LectureQuestionReadDto> readQuestions(Lecture lecture, Pageable pageable);
}
