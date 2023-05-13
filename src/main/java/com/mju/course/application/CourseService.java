package com.mju.course.application;

import com.mju.course.domain.model.other.Result.CommonResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    CommonResult readCourseList(String category, String order, List<String> skill, Pageable pageable);
    CommonResult readCourse(Long course_index);
}
