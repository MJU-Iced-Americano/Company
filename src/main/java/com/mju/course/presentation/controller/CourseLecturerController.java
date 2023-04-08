package com.mju.course.presentation.controller;

import com.mju.course.application.CourseService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course-service")
public class CourseLecturerController {

    private final CourseService courseService;

    @PostMapping("/course")
    public CommonResult createCourse(@RequestBody PostCourseDto postCourseDto){
        return courseService.createCourse(postCourseDto);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
