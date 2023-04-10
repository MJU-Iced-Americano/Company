package com.mju.course.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class PostCourseDto {

    // 코스
    private String category;
    private String course_name;
    private String price;
    private String course_description;
    private int difficulty;
    private int course_time;
    private String skill;
    private String course_period;

    // 커리 큘럼
    private ArrayList<PostCurriculumDto> postCurriculumDtos;


    @Builder
    public PostCourseDto(String category, String course_name, String price, String course_description,
                         int difficulty, int course_time, String skill, String course_period){
        this.category = category;
        this.course_name = course_name;
        this.price = price;
        this.course_description = course_description;
        this.difficulty = difficulty;
        this.course_time = course_time;
        this.skill = skill;
        this.course_period = course_period;
    }

}
