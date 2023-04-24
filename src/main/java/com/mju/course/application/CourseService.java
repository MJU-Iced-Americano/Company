package com.mju.course.application;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.CourseCreateDto;
import com.mju.course.presentation.dto.request.CourseUpdateDto;
import com.mju.course.presentation.dto.request.LectureCreateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseService {
    CommonResult createCourse(CourseCreateDto courseCreateDto);
    CommonResult readCourse(Long course_index);
    CommonResult updateCourse(Long course_index, CourseUpdateDto courseUpdateDto);
    CommonResult updateCurriculum(Long course_index, int chapter);

    ////////////////////////////////////////////////////////
    CommonResult createLecture(Long course_index, int chapter, int lecture_sequence, LectureCreateDto lectureCreateDto, MultipartFile multipartFile) throws IOException;


    CommonResult requestCourse(Long course_index);

    ////////////////////////////////////////////////////////

    CommonResult registerCourse(Long course_index);

    CommonResult holdCourse(Long course_index, String comment);

    CommonResult deleteCourse(Long course_index, String comment);



}
