package com.mju.course.domain.repository.course;

import com.mju.course.presentation.dto.response.CoursesReadDto;
import com.mju.course.presentation.dto.response.admin.AdminReadCoursesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseRepositoryCustom {
    Page<AdminReadCoursesDto> readCoursesPageComplex(String state, String order, Pageable pageable);
    Page<CoursesReadDto> readCourseList(String category, String order, List<String> skill, Pageable pageable);
}
