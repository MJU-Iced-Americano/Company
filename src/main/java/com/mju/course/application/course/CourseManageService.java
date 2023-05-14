package com.mju.course.application.course;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.CourseCreateDto;
import com.mju.course.presentation.dto.request.CourseUpdateDto;
import com.mju.course.presentation.dto.request.CurriculumCreateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseManageService {
    CommonResult createCourse(CourseCreateDto courseCreateDto, MultipartFile titlePhoto) throws IOException;
    CommonResult updateCourse(Long course_index, CourseUpdateDto courseUpdateDto, MultipartFile titlePhoto) throws IOException;
    CommonResult deleteCourse(Long course_index, String comment);
    CommonResult requestCourse(Long course_index);

    CommonResult addCurriculum(Long course_index, CurriculumCreateDto curriculumCreateDto);
    CommonResult updateCurriculum(Long course_index, int chapter, CurriculumCreateDto curriculumCreateDto);
    CommonResult deleteCurriculum(Long course_index, int chapter);
}
