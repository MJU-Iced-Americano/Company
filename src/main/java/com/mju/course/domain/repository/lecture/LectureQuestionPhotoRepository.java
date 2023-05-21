package com.mju.course.domain.repository.lecture;

import com.mju.course.domain.model.lecture.LectureQuestion;
import com.mju.course.domain.model.lecture.LectureQuestionPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureQuestionPhotoRepository extends JpaRepository<LectureQuestionPhoto, Long> {

    List<LectureQuestionPhoto> findByLectureQuestion(LectureQuestion lectureQuestion);
}
