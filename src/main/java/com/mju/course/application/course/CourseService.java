package com.mju.course.application.course;

import com.mju.course.presentation.dto.response.ClientReadCourseDto;
import com.mju.course.presentation.dto.response.CourseReadDto;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import com.mju.course.presentation.dto.response.SearchReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Set<String> readSkills();
    Page<CoursesReadDto> readCourseList(String category, String order, List<String> skill, Pageable pageable, String search, String userId);
    CourseReadDto readCourse(Long course_index, String userId);

    List<SearchReadDto> readSearch(String userId);
    String deleteSearch(Long search_index, String userId);
    String deleteSearchList(String userId);

    String addCart(String userId, Long course_index);
    String deleteCart(String userId, Long course_index);
    void courseLike(String userId, Long course_index);

    String applyCourse(String userId, Long course_index);
    String cancelCourse(String userId, Long user_course_index);

    ClientReadCourseDto returnCourse(Long course_index);
    List<String> returnCourseUsers(Long course_index);
}
