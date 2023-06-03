package com.mju.course.application.course;

import com.mju.course.presentation.dto.request.CourseCreateDto;
import com.mju.course.presentation.dto.request.CourseUpdateDto;
import com.mju.course.presentation.dto.request.CurriculumCreateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseManageService {
    String createCourse(Long lecturerId, CourseCreateDto courseCreateDto, MultipartFile titlePhoto) throws IOException;
    String updateCourse(Long lecturerId, Long course_index, CourseUpdateDto courseUpdateDto, MultipartFile titlePhoto) throws IOException;
    String deleteCourse(Long lecturerId, Long course_index, String comment);
    String requestCourse(Long lecturerId, Long course_index);

    String addCurriculum(Long lecturerId, Long course_index, CurriculumCreateDto curriculumCreateDto);
    String updateCurriculum(Long lecturerId, Long course_index, int chapter, CurriculumCreateDto curriculumCreateDto);
    String deleteCurriculum(Long lecturerId, Long course_index, int chapter);
}
