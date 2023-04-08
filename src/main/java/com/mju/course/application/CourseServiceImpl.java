package com.mju.course.application;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.CourseRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.PostCourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final ResponseService responseService;

    @Override
    public CommonResult createCourse(PostCourseDto postCourseDto) {
        return responseService.getSuccessfulResult();
    }
}
