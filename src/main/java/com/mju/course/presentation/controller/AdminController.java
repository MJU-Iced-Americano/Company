package com.mju.course.presentation.controller;

import com.mju.course.application.AdminServiceImpl;
import com.mju.course.domain.model.other.Result.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
@Tag(name = "1. (운영자 용) 코스, 강의 관리", description = "운영자 용 api 입니다.")
public class AdminController {

    private final AdminServiceImpl adminService;

    // (운영자) 코스 삭제 - 완전 삭제
    @DeleteMapping("/manage/delete/{course_index}")
    public CommonResult deleteCourse(@PathVariable Long course_index){
        return adminService.deleteCourse(course_index);
    }

    // (운영자) 코스 등록
    @GetMapping("/manage/register/{course_index}")
    public CommonResult registerCourse(@PathVariable Long course_index){
        return adminService.registerCourse(course_index);
    }

    // (운영자) 코스 등록 보류
    @PostMapping("/manage/hold/{course_index}")
    public CommonResult holdCourse(@PathVariable Long course_index, String comment){
        return adminService.holdCourse(course_index, comment);
    }
}
