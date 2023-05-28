package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.User;
import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.course.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    Optional<UserCourse> findByUserAndCourse(User user, Course course);
}
