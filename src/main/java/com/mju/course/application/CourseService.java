package com.mju.course.application;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.PostCourseDto;

public interface CourseService {
    CommonResult createCourse(PostCourseDto postCourseDto);

    CommonResult registerCourse(Long course_index);

    CommonResult holdCourse(Long course_index, String comment);

    CommonResult deleteCourse(Long course_index, String comment);
}
