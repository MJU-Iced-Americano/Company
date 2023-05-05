package com.mju.course.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/lecture")
@Tag(name = "5. 강의", description = "강의 관련 api 입니다. ")
public class LectureController {

    // [Read] (공통) 강의 조회 - 동영상 스트리밍  (단순)

    ///////////////// READ ////////////////////////////
    // [Read] 강의 동영상 + 목차
    // [Read] 강의 동영상 + 강의 질문 전체 보기
    // [Read] 강의 동영상 + 강의 질문 상세 보기
    // [Read] 강의 동영상 + 나의 강의 노트 보기

    //////////////////// 강의 질문 //////////////////////////
    // [Create]
    // [Read]
    // [Update]
    // [Delete]

    // 5월 1주차
    //////////////////// 나의 강의 노트 //////////////////////////
    // [Create]
    // [Read]
    // [Update]
    // [Delete]

}
