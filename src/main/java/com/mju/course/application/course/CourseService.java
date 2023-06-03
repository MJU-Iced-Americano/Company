package com.mju.course.application.course;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.response.CourseReadDto;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Set<String> readSkills();
    Page<CoursesReadDto> readCourseList(String category, String order, List<String> skill, Pageable pageable, String search, String userId);
    CourseReadDto readCourse(Long course_index, String userId);

    CommonResult readSearch(String userId);
    CommonResult deleteSearch(Long search_index, String userId);
    CommonResult deleteSearchList(String userId);

    CommonResult addCart(String userId, Long course_index);
    CommonResult deleteCart(String userId, Long course_index);
    CommonResult courseLike(String userId, Long course_index);

    CommonResult applyCourse(String userId, Long course_index);
    CommonResult cancelCourse(String userId, Long user_course_index);
}
