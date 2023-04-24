package com.mju.course.presentation.controller;

import com.mju.course.application.CourseService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.CourseCreateDto;
import com.mju.course.presentation.dto.request.CourseUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    // (강사) 코스 등록 - 이미지 어떻게 처리 ??
    @PostMapping("/manage/new-course")
    public CommonResult createCourse(@RequestBody CourseCreateDto courseCreateDto){
        return courseService.createCourse(courseCreateDto);
    }

    // (공통) 코스 조회
    @GetMapping("/{course_index}")
    public CommonResult readCourse(@PathVariable Long course_index){
        return courseService.readCourse(course_index);
    }

    // (강사) 코스 수정
    @PutMapping("/manage/edit/{course_index}")
    public CommonResult updateCourse(@PathVariable Long course_index,
                                     @RequestBody CourseUpdateDto courseUpdateDto){
        return courseService.updateCourse(course_index, courseUpdateDto);
    }

    // 커리 큘럼 추가는 어디서...?

    // (강사) 커리 큘럼 수정  -- 구현 필요
    @PostMapping("/manage/edit/{course_index}/{chapter}")
    public CommonResult updateCurriculum(@PathVariable Long course_index,
                                         @PathVariable int chapter){
        return courseService.updateCurriculum(course_index, chapter);
    }

    // (운영자) 코스 삭제
    @PostMapping("/manage/delete/{course_index}")
    public CommonResult deleteCourse(@PathVariable Long course_index, String comment){
        return courseService.deleteCourse(course_index, comment);
    }

    //////////////////////////////////////////////////////

    // (강사) 코스 신청
    @PostMapping("/manage/{course_index}")
    public CommonResult requestCourse(@PathVariable Long course_index){
        return courseService.requestCourse(course_index);
    }

    // (운영자) 코스 등록
    @GetMapping("/manage/register/{course_index}")
    public CommonResult registerCourse(@PathVariable Long course_index){
        return courseService.registerCourse(course_index);
    }

    // (운영자) 코스 등록 보류
    @PostMapping("/manage/hold/{course_index}")
    public CommonResult holdCourse(@PathVariable Long course_index, String comment){
        return courseService.holdCourse(course_index, comment);
    }

}
