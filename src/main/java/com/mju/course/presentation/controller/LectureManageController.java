package com.mju.course.presentation.controller;

import com.mju.course.application.lecture.LectureManageService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.LectureCreateDto;
import com.mju.course.presentation.dto.request.LectureUpdateDto;
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
@RequestMapping("/lecture-service/lecture/manage")
@Tag(name = "Lecture Manage Controller", description = "강사진 용 강의 관리 관련 api")
public class LectureManageController {

    private final LectureManageService lectureManageService;

    @Operation(summary = "(강사) 강의 등록", description = "강사진 용 강의 등록 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5023", description = "존재 하지 않는 강의입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5011", description = "존재 하지 않는 커리 쿨럼입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5021", description = "이미 존재하는 강의 입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5012", description = "해당 커리큘럼의 강의 수를 초과했습니다. 커리큘럼 정보 수정 후 다시 등록해주세요. ", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "course_index", description = "코스 인덱스", required = true),
            @Parameter(name = "chapter", description = "해당 커리큘럼 챕터", required = true),
            @Parameter(name = "lecture_sequence", description = "해당 챕터의 강의 순서", required = true),
            @Parameter(name = "postLectureDto", description = "강의 정보를 담은 객체", required = true),
            @Parameter(name = "video", description = "강의 영상", required = true)
    })
    @PostMapping("/new-lecture/{course_index}/{chapter}/{lecture_sequence}")
    public CommonResult createLecture(@PathVariable Long course_index,
                                      @PathVariable int chapter,
                                      @PathVariable int lecture_sequence,
                                      @RequestPart("postLectureDto") @Validated LectureCreateDto lectureCreateDto,
                                      @RequestPart("video") MultipartFile multipartFile) throws IOException {
        return lectureManageService.createLecture(course_index,chapter,lecture_sequence, lectureCreateDto,multipartFile);
    }

    @Operation(summary = "(강사) 강의 수정", description = "강사진 용 강의 수정 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5023", description = "존재 하지 않는 강의입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5022", description = "수정된 강의 요소가 없습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "course_index", description = "코스 인덱스", required = true),
            @Parameter(name = "lectureUpdateDto", description = "강의 수정 정보를 담은 객체", required = true)
    })
    @PutMapping("/edit/{lecture_index}")
    public CommonResult updateLecture(@PathVariable Long lecture_index,
                                      @RequestBody LectureUpdateDto lectureUpdateDto){
        return lectureManageService.updateLecture(lecture_index, lectureUpdateDto);
    }

    @Operation(summary = "(강사) 강의 삭제", description = "강사진 용 강의 삭제 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5023", description = "존재 하지 않는 강의입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-5031", description = "s3 객체 삭제를 실패했습니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameter(name = "course_index", description = "코스 인덱스", required = true)
    @DeleteMapping("/delete/{lecture_index}") //s3 관련 추가
    public CommonResult deleteLecture(@PathVariable Long lecture_index){
        return lectureManageService.deleteLecture(lecture_index);
    }

    // [Crate] (강사) 대용량 파일 업로드


}
