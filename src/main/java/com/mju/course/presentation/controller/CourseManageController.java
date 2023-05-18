package com.mju.course.presentation.controller;

import com.mju.course.application.course.CourseManageService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course-service/course/manage")
@Tag(name = "Course Manage controller", description = "강사진 용 코스 관련 api")
public class CourseManageController {

    private final CourseManageService courseManageService;

    // (강사) 코스 등록 - 코스 설명할 때 사진 어떻게 처리 ? -> 다중 사진 처리
    @Operation(summary = "(강사) 코스 등록", description = "강사진 용 코스 등록 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5002", description = "중복된 코스 이름입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "courseCreateDto", description = "코스 기본 정보를 담은 객체", required = true),
            @Parameter(name = "titlePhoto", description = "코스 기본 사진", required = true)
    })
    @PostMapping("/new-course")
    public CommonResult createCourse(@RequestPart("courseCreateDto") @Validated CourseCreateDto courseCreateDto,
                                     @RequestPart(value="titlePhoto") MultipartFile titlePhoto) throws IOException {
        return courseManageService.createCourse(courseCreateDto, titlePhoto);
    }

    @Operation(summary = "(강사) 코스 수정", description = "코스 수정 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5002", description = "중복된 코스 이름입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5003", description = "수정된 코스 요소가 없습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "course_index", description = "코스 인덱스", required = true),
            @Parameter(name = "courseUpdateDto", description = "코스 변경 정보를 담은 객체"),
            @Parameter(name = "titlePhoto", description = "코스 기본 사진")
    })
    @PutMapping("/edit/{course_index}")
    public CommonResult updateCourse(@PathVariable Long course_index,
                                     @RequestPart(value ="courseUpdateDto",required = false) CourseUpdateDto courseUpdateDto,
                                     @RequestPart(value ="changeTitlePhoto",required = false) MultipartFile titlePhoto) throws IOException {
        return courseManageService.updateCourse(course_index, courseUpdateDto,titlePhoto);
    }

    @Operation(summary = "(강사) 코스 삭제", description = "강사진용 코스 삭제 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5004", description = "코스 삭제 이유를 입력해주세요.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "course_index", description = "코스 인덱스", required = true),
            @Parameter(name = "comment", description = "삭제 이유", required = true)
    })
    @DeleteMapping("/delete/{course_index}")
    public CommonResult deleteCourse(@PathVariable Long course_index, String comment){
        return courseManageService.deleteCourse(course_index, comment);
    }

    @Operation(summary = "(강사) 코스 신청", description = "강사진용 코스 삭제 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5005", description = "이미 등록 신청한 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5006", description = "커리큘럼에 따른 강의가 등록되지 않았습니다. 다시 확인해 주세요.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameter(name = "course_index", description = "코스 인덱스", required = true)
    @PostMapping("/request/{course_index}")
    public CommonResult requestCourse(@PathVariable Long course_index){
        return courseManageService.requestCourse(course_index);
    }

    @Operation(summary = "(강사) 커리 큘럼 추가", description = "강사진용 커리 큘럼 추가 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5013", description = "이미 해당 코스 안에 존재하는 챕터입니다. 다시 입력해주세요.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5014", description = "이미 해당 코스 안에 존재하는 챕터 이름입니다. 다시 입력해주세요.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "course_index", description = "코스 인덱스", required = true),
            @Parameter(name = "curriculumCreateDto", description = "커리 큘럼 정보를 담은 객체")
    })
    @PostMapping("/edit/{course_index}/add-curriculum")
    public CommonResult addCurriculum(@PathVariable Long course_index,
                                      @RequestBody @Validated CurriculumCreateDto curriculumCreateDto){
        return courseManageService.addCurriculum(course_index,curriculumCreateDto);
    }

    @Operation(summary = "(강사) 커리 큘럼 수정", description = "커리 큘럼 수정 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5011", description = "존재 하지 않는 커리 쿨럼입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5013", description = "이미 해당 코스 안에 존재하는 챕터입니다. 다시 입력해주세요.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5014", description = "이미 해당 코스 안에 존재하는 챕터 이름입니다. 다시 입력해주세요.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5017", description = "이미 올라간 강의 수보다 작은 수를 수정했습니다. 강의수를 변경 or 강의 삭제 or 다시 입력 바랍니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5016", description = "수정된 커리큘럼 요소가 없습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "course_index", description = "코스 인덱스", required = true),
            @Parameter(name = "chapter", description = "커리 큘럼 챕터"),
            @Parameter(name = "curriculumCreateDto", description = "커리 큘럼 수정 정보를 담은 객체")
    })
    @PutMapping("/edit/{course_index}/{chapter}")
    public CommonResult updateCurriculum(@PathVariable Long course_index,
                                         @PathVariable int chapter,
                                         @RequestBody CurriculumCreateDto curriculumCreateDto){
        return courseManageService.updateCurriculum(course_index, chapter, curriculumCreateDto);
    }

    @Operation(summary = "(강사) 커리 큘럼 삭제", description = "커리 큘럼 삭제 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5011", description = "존재 하지 않는 커리 쿨럼입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5018", description = "해당 커리큘럼에 이미 강의가 존재합니다. 강의 삭제 후 다시 시도해주세요.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "course_index", description = "코스 인덱스", required = true),
            @Parameter(name = "chapter", description = "커리 큘럼 챕터")
    })
    @PutMapping("/delete/{course_index}/{chapter}")
    public CommonResult deleteCurriculum(@PathVariable Long course_index,
                                         @PathVariable int chapter){
        return courseManageService.deleteCurriculum(course_index, chapter);
    }

}
