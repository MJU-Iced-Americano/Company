package com.mju.course.application;

import com.mju.course.domain.model.other.Result.CommonResult;

import java.util.List;

public interface CoursesService {

    CommonResult readBasicCourses(String order);
    CommonResult readBasicSkillCourses(String order, List<String> skill);

    CommonResult readCategoryCourses(String category, String order);
    CommonResult readCategorySkillCourses(String category, String order, List<String> skill);
}
