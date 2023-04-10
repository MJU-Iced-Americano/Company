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
    private String courseName;
    private String price;
    private String courseDescription;
    private int difficulty;
    private int courseTime;
    private String skill;
    private String coursePeriod;

    // 커리 큘럼
    private ArrayList<PostCurriculumDto> postCurriculumDtos;


    @Builder
    public PostCourseDto(String category, String courseName, String price, String courseDescription,
                         int difficulty, int courseTime, String skill, String coursePeriod){
        this.category = category;
        this.courseName = courseName;
        this.price = price;
        this.courseDescription = courseDescription;
        this.difficulty = difficulty;
        this.courseTime = courseTime;
        this.skill = skill;
        this.coursePeriod = coursePeriod;
    }

}
