package com.mju.course.presentation.controller;

import com.mju.course.application.lecture.LectureService;
import com.mju.course.application.UserServiceImpl;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
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

    private final ResponseService responseService;

    @Operation(summary = "(공통) 강의 보기", description = "tab : 기본(basic), 목차 (curriculum)")
    @GetMapping("/{lecture_index}")
    public CommonResult readBasicLecture(@PathVariable Long lecture_index,
                                         @RequestParam("tab") String tab){
        return lectureService.readLecture(lecture_index, tab);
    }

    // 강의 질문-답변 read
    @Operation(summary = "강의 질문 하나 보기 (+답변) ", description = "강의 질문 하나 보기 API 입니다. ")
    @GetMapping("/question/{question_index}")
    public CommonResult readQAndA(@PathVariable Long question_index){
        return lectureService.readQAndA(question_index);
    }

    @Operation(summary = "강의 질문 리스트 보기 (페이징 처리)", description = "강의 질문 리스트 보기 API 입니다. ")
    @GetMapping("/{lecture_index}/question")
    public CommonResult readQuestions(@PathVariable Long lecture_index,
                                      Pageable pageable){
        return lectureService.readQuestions(lecture_index, pageable);
    }

    @Operation(summary = "강의 답변 하나보기", description = "강의 답변 하나보기 API 입니다. ")
    @GetMapping("/answer/{lecture_answer_index}")
    public CommonResult readAnswer(@PathVariable Long lecture_answer_index){
        return lectureService.readAnswer(lecture_answer_index);
    }

    // 강의 질문 CD
    @Operation(summary = "강의 질문 작성하기", description = "강의 질문 작성하기 API 입니다. ")
    @PostMapping("/{lecture_index}/question")
    public CommonResult createQuestion(@PathVariable Long lecture_index,
                                       @RequestPart(value="images", required=false) List<MultipartFile> images,
                                       @RequestPart(value="lectureQuestionDto") @Validated LectureQuestionCreateDto lectureQuestionCreateDto,
                                       HttpServletRequest request){
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        return lectureService.createQuestion(userId, lecture_index, images, lectureQuestionCreateDto);
    }

//    @Operation(summary = "강의 질문 수정하기", description = "강의 질문 수정하기 API 입니다. ")
//    @PutMapping("/question/{question_index}")
//    public CommonResult updateQuestion(@PathVariable Long question_index){
//        return lectureService.updateQuestion(question_index);
//    }

    @Operation(summary = "강의 질문 삭제하기", description = "강의 질문 삭제하기 API 입니다. ")
    @DeleteMapping("/question/{question_index}")
    public CommonResult deleteQuestion(@PathVariable Long question_index,
                                       HttpServletRequest request){
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        return lectureService.deleteQuestion(userId, question_index);
    }

    // 강의 질문 북마크, 북마크 취소
    @Operation(summary = "강의 질문 북마크, 북마크 취소", description = "강의 질문 북마크, 북마크 취소 API 입니다. ")
    @GetMapping("/{question_index}/bookmark")
    public CommonResult lectureQuestionBookmark(@PathVariable Long question_index,
                                                HttpServletRequest request){
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        return lectureService.lectureQuestionBookmark(question_index,userId);
    }

    // 강의 답변 CD
    @Operation(summary = "강의 답변 작성하기", description = "강의 답변 작성하기 API 입니다. ")
    @PostMapping("/{question_index}/answer")
    public CommonResult createAnswer(@PathVariable Long question_index,
                                     @RequestPart(value="images", required=false) List<MultipartFile> images,
                                     @RequestPart(value="answerCreateDto") AnswerCreateDto answerCreateDto,
                                     HttpServletRequest request){
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        return lectureService.createAnswer(userId, question_index, images, answerCreateDto);
    }

//    @Operation(summary = "강의 답변 수정하기", description = "강의 답변 작성하기 API 입니다. ")
//    @PutMapping("/answer/{lecture_answer_index}")
//    public CommonResult updateAnswer(@PathVariable Long lecture_answer_index,
//                                     @RequestPart(value="images", required=false) List<MultipartFile> images,
//                                     @RequestPart(value="lectureAnswerCreateDto") @Validated LectureAnswerCreateDto lectureAnswerCreateDto){
//        return lectureService.updateAnswer(lecture_answer_index, images, lectureAnswerCreateDto);
//    }

    @Operation(summary = "강의 답변 삭제하기", description = "강의 답변 삭제하기 API 입니다. ")
    @DeleteMapping("/answer/{lecture_answer_index}")
    public CommonResult deleteAnswer(@PathVariable Long lecture_answer_index,
                                     HttpServletRequest request){
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        return lectureService.deleteAnswer(userId, lecture_answer_index);
    }

}
