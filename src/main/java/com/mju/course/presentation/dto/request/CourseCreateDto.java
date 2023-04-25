package com.mju.course.presentation.dto.request;

import lombok.*;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateDto {

    // 코스
    private String category;
    private String courseName;
    private String price;
    private String courseDescription;
    private int difficulty;
    private String skill;

    // 커리 큘럼
    private ArrayList<CurriculumCreateDto> curriculumCreateDtos;

    @Builder
    public CourseCreateDto(String category, String courseName, String price, String courseDescription,
                           int difficulty, String skill){
        this.category = category;
        this.courseName = courseName;
        this.price = price;
        this.courseDescription = courseDescription;
        this.difficulty = difficulty;
        this.skill = skill;
    }

}
