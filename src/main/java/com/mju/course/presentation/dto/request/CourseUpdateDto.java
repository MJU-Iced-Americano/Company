package com.mju.course.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateDto {

    // 코스
    private String category;
    private String courseName;
    private String price;
    private String courseDescription;
    private int difficulty;

    // 스킬
    private ArrayList<String> skillList;
}
