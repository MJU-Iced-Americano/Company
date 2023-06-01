package com.mju.course.presentation.controller;

import com.mju.course.application.RequestServiceImpl;
import com.mju.course.application.UserServiceImpl;
import com.mju.course.domain.model.other.Result.ListResult;
import com.mju.course.domain.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/request")
@Tag(name = "MSA 통신", description = "MSA 통신 관련 api")
public class RequestController {

    private final RequestServiceImpl requestService;
    private final ResponseService responseService;

    private final UserServiceImpl userService;

    @Operation(summary = "수강 중인 목록 보기", description = "수강 중인 목록 보기 API 입니다. ")
    @GetMapping("/user/course-list")
    public ListResult readCourseList(HttpServletRequest request){
        return responseService.getListResult(requestService.requestCourseList(userService.getUserId(request)));
    }

    @Operation(summary = "좋아요 한 강의", description = "좋아요 한 강의 API 입니다. ")
    @GetMapping("/user/course-like")
    public ListResult readCourseLike(HttpServletRequest request){
        return responseService.getListResult(requestService.requestCourseLike(userService.getUserId(request)));
    }

}
