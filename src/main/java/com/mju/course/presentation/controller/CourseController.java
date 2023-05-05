package com.mju.course.presentation.controller;

import com.mju.course.application.CourseManageService;
import com.mju.course.application.CourseService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.response.CourseReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/course")
@Tag(name = "4. 코스", description = "코스 관련 api 입니다. ")
public class CourseController {

    private final CourseService courseService;

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

}
