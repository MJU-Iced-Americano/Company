package com.mju.course.domain.repository;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    Optional<Curriculum> findByCourseAndChapter(Course course, int chapter);

    List<Curriculum> findByCourse(Course findCourse);

    Optional<Curriculum> findByCourseAndCurriculumTitle(Course findCourse, String curriculumTitle);
}
