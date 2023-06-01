package com.mju.course.application.course;

import com.mju.course.presentation.dto.request.CourseCreateDto;
import com.mju.course.presentation.dto.request.CourseUpdateDto;
import com.mju.course.presentation.dto.request.CurriculumCreateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseManageService {
    String createCourse(String userId, CourseCreateDto courseCreateDto, MultipartFile titlePhoto) throws IOException;
    String updateCourse(String userId, Long course_index, CourseUpdateDto courseUpdateDto, MultipartFile titlePhoto) throws IOException;
    String deleteCourse(String userId, Long course_index, String comment);
    String requestCourse(String userId, Long course_index);

    String addCurriculum(String userId, Long course_index, CurriculumCreateDto curriculumCreateDto);
    String updateCurriculum(String userId, Long course_index, int chapter, CurriculumCreateDto curriculumCreateDto);
    String deleteCurriculum(String userId, Long course_index, int chapter);
}
