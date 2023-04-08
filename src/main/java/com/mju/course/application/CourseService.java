package com.mju.course.application;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.PostCourseDto;

public interface CourseService {
    CommonResult createCourse(PostCourseDto postCourseDto);
}
