package com.mju.course.presentation.controller;

import com.mju.course.application.RequestServiceImpl;
import com.mju.course.domain.model.other.Result.ListResult;
import com.mju.course.domain.model.other.Result.SingleResult;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/request")
@Tag(name = "MSA 통신", description = "MSA 통신 관련 api")
public class RequestController {

    private final RequestServiceImpl requestService;
    private final ResponseService responseService;

    @Operation(summary = "수강 중인 목록 보기", description = "수강 중인 목록 보기 API 입니다. ")
    @GetMapping("/user/course-list")
    public ListResult readCourseList(@RequestParam Long userId){
        return responseService.getListResult(requestService.requestCourseList(userId));
    }

    @Operation(summary = "좋아요 한 강의", description = "좋아요 한 강의 API 입니다. ")
    @GetMapping("/user/course-like")
    public ListResult readCourseLike(@RequestParam Long userId){
        return responseService.getListResult(requestService.requestCourseLike(userId));
    }

}
