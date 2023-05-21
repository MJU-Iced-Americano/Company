package com.mju.course.domain.repository.lecture;

import com.mju.course.domain.model.User;
import com.mju.course.domain.model.lecture.LectureQuestion;
import com.mju.course.domain.model.lecture.LectureQuestionBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureQuestionBookmarkRepository extends JpaRepository<LectureQuestionBookmark, Long>{
    Optional<LectureQuestionBookmark> findByLectureQuestionAndUser(LectureQuestion lectureQuestion, User user);
}
