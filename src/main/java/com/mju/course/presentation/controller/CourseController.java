package com.mju.course.presentation.controller;

import com.mju.course.application.UserServiceImpl;
import com.mju.course.application.course.CourseService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.CourseReadDto;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import com.mju.course.presentation.dto.response.SearchReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course-service/course")
@Tag(name = "Course controller", description = "코스 관련 api")
public class CourseController {

    private final CourseService courseService;
    private final UserServiceImpl userService;

    private final ResponseService responseService;

    @Operation(summary = "기술리스트 보기")
    @GetMapping("/skill/all")
    public CommonResult readSkills(){
        Set<String> skills = courseService.readSkills();
        return responseService.getSingleResult(skills);
    }

    // 추후 개발 - 다른 MSA 와의 통신 : 평점 높은 순, 리뷰 많은 순
    // 유저 정보가 존재한다면 - 검색어 저장
    @Operation(summary = "목록 보기", description = " order : 최신순 (createdAt), 난이도 순 (difficulty), 조회 수 높은 순 (hits), 좋아요 수(likeSum)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CoursesReadDto.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameters({
            @Parameter(name = "order", description = "생성일(createdAt - 기본값), 난이도 순 (difficulty), 조회 수 높은 순 (hits), 좋아요 수(likeSum)", required = false),
            @Parameter(name = "skill", description = "코스 스킬 (Java, Programming)", required = false),
            @Parameter(name = "category", description = "코스 카테고리", required = false),
            @Parameter(name = "search", description = "검색어", required = false)
    })
    @GetMapping()
    public CommonResult readCourseList(@RequestParam(value = "category", required = false) String category,
                                       @RequestParam(value = "order", required = false, defaultValue = "createdAt") String order,
                                       @RequestParam(value = "skill", required = false) List<String> skill,
                                       @RequestParam(value = "search", required = false) String search,
                                       Pageable pageable,
                                       HttpServletRequest request) {
        String userId = userService.getUserId(request);
        Page<CoursesReadDto> result = courseService.readCourseList(category, order, skill, pageable, search, userId);
        return responseService.getSingleResult(result);
    }

    @Operation(summary = "검색어 보기", description = "검색어 보기 API 입니다. ")
    @GetMapping("/search")
    public CommonResult readSearch(HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        List<SearchReadDto> result = courseService.readSearch(userId);
        return responseService.getListResult(result);
    }

    @Operation(summary = "검색어 하나 삭제", description = "검색어 하나 삭제 API 입니다. ")
    @DeleteMapping("/delete-search/{search_index}")
    public CommonResult deleteSearch(@PathVariable Long search_index,
                                     HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        String result = courseService.deleteSearch(search_index, userId);
        return responseService.getSingleResult(result);
    }

    @Operation(summary = "검색어 전체 삭제", description = "검색어 전체 삭제 API 입니다. ")
    @DeleteMapping("/delete-search/list")
    public CommonResult deleteSearchList(HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        String result = courseService.deleteSearchList(userId);
        return responseService.getSingleResult(result);
    }

    @Operation(summary = "(공통) 코스 조회", description = "코스 조회 API 입니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공하였습니다.", content = @Content(schema = @Schema(implementation = CourseReadDto.class))),
            @ApiResponse(responseCode = "-5001", description = "존재 하지 않는 코스입니다.", content = @Content(schema = @Schema(implementation = CommonResult.class))),
            @ApiResponse(responseCode = "-9999", description = "알 수 없는 오류가 발생하였습니다.")
    })
    @Parameter(name = "course_index", description = "코스 인덱스", required = true)
    @GetMapping("/{course_index}")
    public CommonResult readCourse(@PathVariable Long course_index,
                                   HttpServletRequest request) {
        CourseReadDto result = courseService.readCourse(course_index, userService.getUserId(request));
        return responseService.getSingleResult(result);
    }

    @Operation(summary = "(공통) 코스 장바구니 추가", description = "코스 장바구니 추가 API 입니다. ")
    @PostMapping("/{course_index}/cart")
    public CommonResult addCart(@PathVariable Long course_index,
                                HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        String result = courseService.addCart(userId, course_index);
        return responseService.getSingleResult(result);
    }

    @Operation(summary = "(공통) 코스 장바구니 삭제", description = "코스 장바구니 삭제 API 입니다. ")
    @DeleteMapping("/{course_index}/cart")
    public CommonResult deleteCart(@PathVariable Long course_index,
                                   HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        String result = courseService.deleteCart(userId, course_index);
        return responseService.getSingleResult(result);
    }

    @Operation(summary = "(공통) 코스 좋아요, 좋아요 취소", description = "코스 좋아요 API 입니다. ")
    @GetMapping("/{course_index}/like")
    public CommonResult courseLike(@PathVariable Long course_index,
                                   HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        courseService.courseLike(userId, course_index);
        return responseService.getSuccessfulResult();
    }

    @Operation(summary = "코스 수강 신청", description = "코스 수강 신청 API 입니다. ")
    @GetMapping("/apply-course/{course_index}")
    public CommonResult applyCourse(@PathVariable Long course_index,
                                    HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        String result = courseService.applyCourse(userId, course_index);
        return responseService.getSingleResult(result);
    }

    @Operation(summary = "코스 수강 취소", description = "코스 취소 API 입니다. ")
    @GetMapping("/cancel-course/{user_course_index}")
    public CommonResult cancelCourse(@PathVariable Long user_course_index,
                                     HttpServletRequest request) {
        String userId = userService.getUserId(request);
        userService.checkUserId(userId);
        String result = courseService.cancelCourse(userId, user_course_index);
        return responseService.getSingleResult(result);
    }

}
