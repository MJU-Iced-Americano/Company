package com.mju.course.presentation.controller;

import com.mju.course.application.CourseService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course-service")
public class CourseController {

    private final CourseService courseService;

    // 코스 등록 후 신청
    @PostMapping("/course")
    public CommonResult createCourse(@RequestBody PostCourseDto postCourseDto){
        return courseService.createCourse(postCourseDto);
    }

    // 코스 정보 수정

    // 코스 동영상 수정

    // 코스 등록 재 신청

    // 코스 등록
    @GetMapping("/course/register/{course_index}")
    public CommonResult registerCourse(@PathVariable Long course_index){
        return courseService.registerCourse(course_index);
    }

    // 코스 등록 보류
    @PostMapping("/course/hold/{course_index}")
    public CommonResult holdCourse(@PathVariable Long course_index, String comment){
        return courseService.holdCourse(course_index, comment);
    }

    // 코스 삭제
    @PostMapping("/course/delete/{course_index}")
    public CommonResult deleteCourse(@PathVariable Long course_index, String comment){
        return courseService.deleteCourse(course_index, comment);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
