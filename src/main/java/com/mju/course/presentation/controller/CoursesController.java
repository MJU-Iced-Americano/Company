package com.mju.course.presentation.controller;

import com.mju.course.application.CoursesService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/courses")
@Tag(name = "3. 코스 목록", description = "코스 목록 관련 api 입니다.")
public class CoursesController { // 코스 목록 필터링

    private final CoursesService coursesService;

    @Operation(summary = "(공통) 기본", description = " order : 기본(seq), 최신순 (new), 난이도 순 (difficulty), 조회 수 높은 순 (hits)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CoursesReadDto.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @GetMapping(params = {"order"})
    public CommonResult readBasicCourses(@RequestParam("order") String order){
        return coursesService.readBasicCourses(order);
    }

    @Operation(summary = "(공통) 기본 + skill", description = " order : 기본(seq), 최신순 (new), 난이도 순 (difficulty), 조회 수 높은 순 (hits)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CoursesReadDto.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @GetMapping(params = {"order", "skill"})
    public CommonResult readBasicSkillCourses(@RequestParam("order") String order,
                                              @RequestParam("skill") List<String> skill){
        return coursesService.readBasicSkillCourses(order, skill);
    }

    @Operation(summary = "(공통) 카테고리 별 - 기본 + 정렬", description = " order : 기본(seq), 최신순 (new), 난이도 순 (difficulty), 조회 수 높은 순 (hits)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CoursesReadDto.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @GetMapping(value = "/{category}", params = {"order"})
    public CommonResult readCategoryCourses(@PathVariable String category,
                                            @RequestParam("order") String order){
        return coursesService.readCategoryCourses(category, order);
    }

    @Operation(summary = "(공통) 카테고리 별 - 기본 + 정렬 + skill", description = " order : 기본(seq), 최신순 (new), 난이도 순 (difficulty), 조회 수 높은 순 (hits)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CoursesReadDto.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @GetMapping(value = "/{category}", params = {"order", "skill"})
    public CommonResult readCategorySkillCourses(@PathVariable String category,
                                                 @RequestParam("order") String order,
                                                 @RequestParam("skill") List<String> skill){
        return coursesService.readCategorySkillCourses(category, order,skill);
    }

    // 5월 이후 개발
    ///////////////////////////////////////   다른 MSA 와의 통신
    // 평점 높은 순, 좋아요 높은 순, 리뷰 많은 순

    /////////////////////////////////////   검색 기능 - elastic search 필요
    // 검색 시스템


}
