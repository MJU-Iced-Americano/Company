package com.mju.course.presentation.controller;

import com.mju.course.application.lecture.LectureServiceImpl;
import com.mju.course.presentation.dto.response.LectureCurriculumReadDto;
import com.mju.course.presentation.dto.response.LectureReadDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/view/lecture")
@Tag(name = "++ 강의", description = "강의 관련 view api 입니다. ")
public class LectureViewController {

    private final LectureServiceImpl lectureService;

    // [Read] (공통) 강의 조회
    @GetMapping(value = "/lecture_index/basic")
    public String readBasicLecture(@PathVariable Long lecture_index,
                              Model model){
        LectureReadDto lectureReadDto = lectureService.readBasicLecture(lecture_index);

        log.info(lectureReadDto.getLectureUrl());

        model.addAttribute("lectureReadDto",lectureReadDto);
        model.addAttribute("url", lectureReadDto.getLectureUrl());
        return "video";
    }

    // [Read] 강의 동영상 + 목차
    @GetMapping(value = "/lecture_index/curriculum")
    public String readCurriculumLecture(@PathVariable Long lecture_index,
                                   Model model){

        LectureCurriculumReadDto curriculumReadDtos = lectureService.readCurriculumLecture(lecture_index);

        model.addAttribute("lectureReadDto", curriculumReadDtos.getLectureReadDto());
        model.addAttribute("curriculumReadDtos", curriculumReadDtos.getCurriculumReadDtos());

        return "video";
    }

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
