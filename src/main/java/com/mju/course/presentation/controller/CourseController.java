package com.mju.course.presentation.controller;

import com.mju.course.application.CourseManageService;
import com.mju.course.application.CourseService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.response.CourseReadDto;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/course")
@Tag(name = "Course controller", description = "코스 관련 api")
public class CourseController {

    private final CourseService courseService;

    // 검색 기능 - elastic search 필요

    // 추후 개발 - 다른 MSA 와의 통신 : 평점 높은 순, 좋아요 높은 순, 리뷰 많은 순
    @Operation(summary = "목록 보기", description = " order : 최신순 (createdAt), 난이도 순 (difficulty), 조회 수 높은 순 (hits)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CoursesReadDto.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @GetMapping()
    public CommonResult readCourseList(@RequestParam(value = "category", required = false) String category,
                                       @RequestParam(value = "order", required = false, defaultValue = "createdAt") String order,
                                       @RequestParam(value = "skill", required = false) List<String> skill,
                                       Pageable pageable) {
        return courseService.readCourseList(category, order, skill, pageable);
    }

    @Operation(summary = "(공통) 코스 조회", description = "코스 조회 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CourseReadDto.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameter(name = "course_index", description = "코스 인덱스", required = true)
    @GetMapping("/{course_index}")
    public CommonResult readCourse(@PathVariable Long course_index) {
        return courseService.readCourse(course_index);
    }

}
