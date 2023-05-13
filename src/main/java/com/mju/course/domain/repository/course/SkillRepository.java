package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    ArrayList<String> findByCourse(Course findCourse);
}
