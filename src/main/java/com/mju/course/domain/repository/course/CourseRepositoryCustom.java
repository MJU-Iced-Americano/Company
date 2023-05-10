package com.mju.course.domain.repository.course;

import com.mju.course.presentation.dto.response.admin.AdminReadCoursesDto;

import java.util.List;

public interface CourseRepositoryCustom {
    List<AdminReadCoursesDto> readCourses(String state);
}
