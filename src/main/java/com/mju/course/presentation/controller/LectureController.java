package com.mju.course.presentation.controller;

import com.mju.course.application.CourseService;
import com.mju.course.application.LectureService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.LectureCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    // [Crate] (강사) 강의 등록
    @PostMapping("/manage/new-lecture/{course_index}/{chapter}/{lecture_sequence}")
    public CommonResult createLecture(@PathVariable Long course_index,
                                      @PathVariable int chapter,
                                      @PathVariable int lecture_sequence,
                                      @RequestPart("postLectureDto") LectureCreateDto lectureCreateDto,
                                      @RequestPart("video") MultipartFile multipartFile) throws IOException {
        return lectureService.createLecture(course_index,chapter,lecture_sequence, lectureCreateDto,multipartFile);
    }

    // [Crate] (강사) 대용량 파일 업로드

    // [Read] (공통) 강의 조회 - 동영상 스트리밍  (단순)

    // [Update] (강사) 강의 수정

    // [Delete] (운영자) 강의 삭제


    // 5월 이후 개발 진행
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

    //////////////////// 나의 강의 노트 //////////////////////////
    // [Create]
    // [Read]
    // [Update]
    // [Delete]

}
