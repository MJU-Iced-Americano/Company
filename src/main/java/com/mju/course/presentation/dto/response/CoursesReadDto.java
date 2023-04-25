package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CoursesReadDto {
    private Long courseIndex;
    private String courseName;
    private String price;
    private int difficulty;

    public static CoursesReadDto of(Course course){
        return CoursesReadDto.builder()
                .courseIndex(course.getId())
                .courseName(course.getCourseName())
                .price(course.getPrice())
                .difficulty(course.getDifficulty())
                .build();
    }
}
