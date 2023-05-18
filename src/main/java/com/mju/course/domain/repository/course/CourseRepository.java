package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom{
    Optional<Course> findByCourseName(String courseName);

    List<Course> findByCategory(String category);
}
