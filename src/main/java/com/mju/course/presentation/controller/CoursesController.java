package com.mju.course.presentation.controller;

import com.mju.course.application.CoursesService;
import com.mju.course.domain.model.other.Result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CoursesController { // 코스 목록 필터링

    private final CoursesService coursesService;

    /**
     * @description 기본 - 기본(seq), 최신순 (new), 난이도 순 (difficulty), 조회 수 높은 순 (hits)
     * @param order
     * @url /courses?order=
     * */
    @GetMapping(params = {"order"})
    public CommonResult readBasicCourses(@RequestParam("order") String order){
        return coursesService.readBasicCourses(order);
    }

    /**
     * @description 기본 - 기본(seq), 최신순 (new), 난이도 순 (difficulty), 조회 수 높은 순 (hits) + skill
     * @param order
     * @param skill
     * @url /courses?order=&skill
     * */
    @GetMapping(params = {"order", "skill"})
    public CommonResult readBasicSkillCourses(@RequestParam("order") String order,
                                              @RequestParam("skill") List<String> skill){
        return coursesService.readBasicSkillCourses(order, skill);
    }

    /**
     * @description 카테고리 별 - 기본 + 정렬
     * @param order
     * @param category
     * @url /courses/{category}?order={ }
     * */
    @GetMapping(value = "/{category}", params = {"order"})
    public CommonResult readCategoryCourses(@PathVariable String category,
                                            @RequestParam("order") String order){
        return coursesService.readCategoryCourses(category, order);
    }

    /**
     * @description 카테고리 별 - 기본 + 정렬 + skill
     * @param category
     * @param skill
     * @param order
     * @url /courses/{category}?order={ }?skill=
     * */
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
