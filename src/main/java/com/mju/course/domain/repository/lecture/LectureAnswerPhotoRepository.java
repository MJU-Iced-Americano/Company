package com.mju.course.domain.repository.lecture;

import com.mju.course.domain.model.lecture.LectureAnswer;
import com.mju.course.domain.model.lecture.LectureAnswerPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureAnswerPhotoRepository extends JpaRepository<LectureAnswerPhoto, Long> {

    List<LectureAnswerPhoto> findByLectureAnswer(LectureAnswer lectureAnswer);
}
