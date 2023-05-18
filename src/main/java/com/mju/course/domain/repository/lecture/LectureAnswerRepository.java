package com.mju.course.domain.repository.lecture;

import com.mju.course.domain.model.lecture.LectureAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureAnswerRepository extends JpaRepository<LectureAnswer, Long> {

}
