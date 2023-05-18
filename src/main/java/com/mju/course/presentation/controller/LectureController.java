package com.mju.course.presentation.controller;

import com.mju.course.application.lecture.LectureService;
import com.mju.course.application.UserServiceImpl;
import com.mju.course.domain.model.enums.UserType;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture-service/lecture")
@Tag(name = "Lecture Controller", description = "강의 관련 api ")
public class LectureController {

    private final LectureService lectureService;
    private final UserServiceImpl userService;

    private void checkUser(){
        userService.checkUser(String.valueOf(UserType.ROLE_USER));
    }

    // 강의 보기
    // (공통) 강의 조회 - tab : 기본(basic), 목차 (curriculum)
    @Operation(summary = "(공통) 강의 조회", description = "tab : 기본(basic), 목차 (curriculum)")
    @GetMapping("/{lecture_index}")
    public CommonResult readBasicLecture(@PathVariable Long lecture_index,
                                         @RequestParam("tab") String tab){
        return lectureService.readLecture(lecture_index, tab);
    }

    // 강의 질문 CRUD
    @Operation(summary = "강의 질문 작성하기", description = "강의 질문 작성하기 API 입니다. ")
    @PostMapping("/{lecture_index}/question")
    public CommonResult createQuestion(@PathVariable Long lecture_index,
                                       @RequestParam(value="image", required=false) List<MultipartFile> images,
                                       @RequestParam(value="question") String question,
                                       @RequestParam Long userId){
        return lectureService.createQuestion(lecture_index, images, question, userId);
    }

    @Operation(summary = "강의 질문 하나 보기", description = "강의 질문 하나 보기 API 입니다. ")
    @GetMapping("/question/{question_index}")
    public CommonResult readQuestion(@PathVariable Long question_index){
        return lectureService.readQuestion(question_index);
    }

    @Operation(summary = "강의 질문 전체 보기", description = "강의 질문 전체 보기 API 입니다. ")
    @GetMapping("/{lecture_index}/question")
    public CommonResult readQuestions(@PathVariable String lecture_index){
        return lectureService.readQuestions(lecture_index);
    }

    @Operation(summary = "강의 질문 수정하기", description = "강의 질문 수정하기 API 입니다. ")
    @PutMapping("/question/{question_index}")
    public CommonResult updateQuestion(@PathVariable Long question_index){
        return lectureService.updateQuestion(question_index);
    }

    @Operation(summary = "강의 질문 삭제하기", description = "강의 질문 삭제하기 API 입니다. ")
    @DeleteMapping("/question/{question_index}")
    public CommonResult deleteQuestion(@PathVariable Long question_index){
        return lectureService.deleteQuestion(question_index);
    }

    // 강의 답변 CRUD

    // 강의 질문 좋아요, 좋아요 취소

}
