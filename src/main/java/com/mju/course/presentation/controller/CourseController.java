package com.mju.course.presentation.controller;

import com.mju.course.application.CourseService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.CourseCreateDto;
import com.mju.course.presentation.dto.request.CourseUpdateDto;
import com.mju.course.presentation.dto.response.CourseReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/course")
@Tag(name = "1. 코스", description = "코스 관련 api 입니다.")
public class CourseController {

    private final CourseService courseService;

    // (강사) 코스 등록 - 코스 설명할 때 사진 어떻게 처리 ?
    @Operation(summary = "(강사) 코스 등록", description = "강사진용 코스 등록 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5002", description = "중복된 코스 이름입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "courseCreateDto", description = "코스 기본 정보를 담은 객체", required = true),
            @Parameter(name = "titlePhoto", description = "코스 기본 사진", required = true)
    })
    @PostMapping("/manage/new-course")
    public CommonResult createCourse(@RequestPart("courseCreateDto") CourseCreateDto courseCreateDto,
                                     @RequestPart("titlePhoto") MultipartFile titlePhoto) throws IOException {
        return courseService.createCourse(courseCreateDto, titlePhoto);
    }

    @Operation(summary = "(공통) 코스 조회", description = "코스 조회 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CourseReadDto.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameter(name = "course_index", description = "코스 인덱스", required = true)
    @GetMapping("/{course_index}")
    public CommonResult readCourse(@PathVariable Long course_index){
        return courseService.readCourse(course_index);
    }

    // (강사) 코스 수정
    @PutMapping("/manage/edit/{course_index}")
    public CommonResult updateCourse(@PathVariable Long course_index,
                                     @RequestBody CourseUpdateDto courseUpdateDto){
        return courseService.updateCourse(course_index, courseUpdateDto);
    }

    // (강사) 커리 큘럼 추가

    // (강사) 커리 큘럼 수정  -- 구현 필요
    @PostMapping("/manage/edit/{course_index}/{chapter}")
    public CommonResult updateCurriculum(@PathVariable Long course_index,
                                         @PathVariable int chapter){
        return courseService.updateCurriculum(course_index, chapter);
    }

    @Operation(summary = "(강사) 코스 삭제 - 완전 삭제", description = "강사진용 코스 삭제 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @DeleteMapping("/manage/delete/{course_index}")
    public CommonResult deleteCourse(@PathVariable Long course_index){
        return courseService.deleteCourse(course_index);
    }

    //////////////////////////////////////////////////////

    // (운영자) 코스 삭제
//    @DeleteMapping("/manage/delete/{course_index}")
//    public CommonResult deleteCourse(@PathVariable Long course_index, String comment){
//        return courseService.deleteCourse(course_index, comment);
//    }

    // (강사) 코스 신청
    @PostMapping("/manage/{course_index}")
    public CommonResult requestCourse(@PathVariable Long course_index){
        return courseService.requestCourse(course_index);
    }

    // (운영자) 코스 등록
    @GetMapping("/manage/register/{course_index}")
    public CommonResult registerCourse(@PathVariable Long course_index){
        return courseService.registerCourse(course_index);
    }

    // (운영자) 코스 등록 보류
    @PostMapping("/manage/hold/{course_index}")
    public CommonResult holdCourse(@PathVariable Long course_index, String comment){
        return courseService.holdCourse(course_index, comment);
    }

}
