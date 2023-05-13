package com.mju.course.presentation.controller;

import com.mju.course.application.AdminServiceImpl;
import com.mju.course.application.UserServiceImpl;
import com.mju.course.domain.model.enums.UserType;
import com.mju.course.domain.model.other.Result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
@Tag(name = "Admin Controller", description = "운영자 용 코스, 강의 관리 api ")
public class AdminController {

    private final AdminServiceImpl adminService;
    private final UserServiceImpl userService;

    private void checkAdmin(){
        userService.checkUser(String.valueOf(UserType.ROLE_ADMIN));
    }

    // status - all(기본), registration, request, hold, delete
    // order - 생성일(createdAt - 기본값), 코스 번호(courseIndex), 난이도(difficulty), 강사 이름(lecture), 가격(price), 강의 시간(courseTime), 조회수(hit)
    // 카테 고리(category)
    // 페이징 처리 - page=0&size=5
    @Operation(summary = "(공통) 코스 조회", description = "코스 조회 API 입니다. ")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호", required = true),
            @Parameter(name = "size", description = "한 페이지에 표시되는 코스 수", required = true),
            @Parameter(name = "order", description = "생성일(createdAt - 기본값), 코스 번호(courseIndex), 난이도(difficulty), 강사 이름(lecture), 가격(price), 강의 시간(courseTime), 조회수(hit)", required = true),
            @Parameter(name = "status", description = "코스 상태 (all, registration, request, hold, delete)", required = true)
    })
    @GetMapping()
    public CommonResult readCourses(@RequestParam(value = "order", required = false, defaultValue = "createdAt") String order,
                                    @RequestParam(value = "status",required = false, defaultValue = "all") String state,
                                    Pageable pageable){
        checkAdmin();
        return adminService.readCourses(state,order,pageable);
    }

    @Operation(summary = "(운영자) 코스 삭제 - 완전 삭제", description = "courseState : delete ---> 완전 삭제")
    @Parameter(name = "course_index", description = "코스 인덱스")
    @DeleteMapping("/manage/delete/{course_index}")
    public CommonResult deleteCourse(@PathVariable Long course_index){
        checkAdmin();
        return adminService.deleteCourse(course_index);
    }

    @Operation(summary = "(운영자) 코스 등록", description = "courseState : request -> registration")
    @Parameter(name = "course_index", description = "코스 인덱스")
    @PutMapping("/manage/register/{course_index}")
    public CommonResult registerCourse(@PathVariable Long course_index){
        checkAdmin();
        return adminService.registerCourse(course_index);
    }

    @Operation(summary = "(운영자) 코스 등록 보류", description = "courseState : request -> hold")
    @Parameter(name = "course_index", description = "코스 인덱스")
    @PutMapping("/manage/hold/{course_index}")
    public CommonResult holdCourse(@PathVariable Long course_index, @RequestBody String comment){
        checkAdmin();
        return adminService.holdCourse(course_index, comment);
    }

    // 검색 기능
}
