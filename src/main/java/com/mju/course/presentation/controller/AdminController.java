package com.mju.course.presentation.controller;

import com.mju.course.application.AdminServiceImpl;
import com.mju.course.application.UserServiceImpl;
import com.mju.course.domain.model.enums.UserType;
import com.mju.course.domain.model.other.Result.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
@Tag(name = "1. (운영자 용) 코스, 강의 관리", description = "운영자 용 api 입니다.")
public class AdminController {

    // 코스 검색 기능

    private final AdminServiceImpl adminService;
    private final UserServiceImpl userService;

    private void checkAdmin(){
        userService.checkUser(String.valueOf(UserType.ROLE_ADMIN));
    }

    // status - registration, hold, delete
    // order - 코스 번호(courseIndex), 난이도(difficulty), 강사 이름(lecture), 가격(price), 강의 시간(courseTime), 생성일, 조회수(hit)
    // 카테 고리(category)
    @GetMapping()
    public CommonResult readCourses(@RequestParam("order") String order,
                                    @RequestParam("status") String state){
        checkAdmin();
        return adminService.readCourses(state);
    }

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
