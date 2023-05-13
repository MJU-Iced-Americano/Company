package com.mju.course.application;

import com.mju.course.domain.model.other.Result.CommonResult;

public interface CourseService {
    CommonResult readCourse(Long course_index);
}
