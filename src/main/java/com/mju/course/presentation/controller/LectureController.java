package com.mju.course.presentation.controller;

import com.mju.course.application.lecture.LectureService;
import com.mju.course.application.UserServiceImpl;
import com.mju.course.domain.model.enums.UserType;
import com.mju.course.domain.model.other.Result.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/lecture")
@Tag(name = "Lecture Controller", description = "강의 관련 api ")
public class LectureController {

    private final LectureService lectureService;
    private final UserServiceImpl userService;

    private void checkUser(){
        userService.checkUser(String.valueOf(UserType.ROLE_USER));
    }

    // 강의 보기
    // (공통) 강의 조회 - tab : 기본(basic), 목차 (curriculum)
    @GetMapping("/{lecture_index}")
    public CommonResult readBasicLecture(@PathVariable Long lecture_index,
                                         @RequestParam("tab") String tab){
        return lectureService.readLecture(lecture_index, tab);
    }

    // 강의 질문

}
