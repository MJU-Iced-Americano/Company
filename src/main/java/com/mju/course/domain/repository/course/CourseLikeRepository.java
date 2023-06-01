package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.course.CourseLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseLikeRepository extends JpaRepository<CourseLike, Long> {
    Optional<CourseLike> findByCourseAndUserId(Course findCourse, String userId);
}
