package com.mju.course.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateDto {

    // 코스
    @Schema(description = "카테고리", defaultValue = "프로그래밍")
    @NotNull @NotBlank
    private String category;

    @Schema(description = "코스 이름", defaultValue = "자바 기초")
    @NotNull @NotBlank
    private String courseName;

    @Schema(description = "가격", defaultValue = "100000")
    private Long price;

    @Schema(description = "코스 설명", defaultValue = "자바 기초 강의입니다.")
    @NotNull @NotBlank
    private String courseDescription;

    @Schema(description = "난이도", defaultValue = "2")
    @NotNull
    private int difficulty;

    @Schema(description = "스킬", defaultValue = "Java")
    private ArrayList<String> skillList;

    @Schema(description = "커리 큘럼")
    private ArrayList<CurriculumCreateDto> curriculumCreateDtos;

    @Builder
    public CourseCreateDto(String category, String courseName, Long price, String courseDescription, int difficulty){
        this.category = category;
        this.courseName = courseName;
        this.price = price;
        this.courseDescription = courseDescription;
        this.difficulty = difficulty;
    }

}
