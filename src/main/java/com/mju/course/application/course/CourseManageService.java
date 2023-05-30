package com.mju.course.application.course;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.CourseCreateDto;
import com.mju.course.presentation.dto.request.CourseUpdateDto;
import com.mju.course.presentation.dto.request.CurriculumCreateDto;
import com.mju.course.presentation.dto.response.UserInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseManageService {
    String createCourse(UserInfoDto userInfo, CourseCreateDto courseCreateDto, MultipartFile titlePhoto) throws IOException;
    CommonResult updateCourse(UserInfoDto userInfo, Long course_index, CourseUpdateDto courseUpdateDto, MultipartFile titlePhoto) throws IOException;
    CommonResult deleteCourse(UserInfoDto userInfo, Long course_index, String comment);
    CommonResult requestCourse(UserInfoDto userInfo, Long course_index);

    CommonResult addCurriculum(UserInfoDto userInfo, Long course_index, CurriculumCreateDto curriculumCreateDto);
    CommonResult updateCurriculum(UserInfoDto userInfo, Long course_index, int chapter, CurriculumCreateDto curriculumCreateDto);
    CommonResult deleteCurriculum(UserInfoDto userInfo, Long course_index, int chapter);
}
