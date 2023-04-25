package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@Builder
public class CourseReadDto {

    // 코스
    private Long courseIndex;
    private String category;
    private String courseName;
    private String price;
    private String courseDescription;
    private int difficulty;
    private int courseTime;
    private String skill;
    private Long hits;
    private String courseTitlePhotoUrl;

    private int curriculumSum;

    // 커리 큘럼
    private ArrayList<CurriculumReadDto> curriculumReadDtos;

    public static CourseReadDto of(Course course, ArrayList<CurriculumReadDto> curriculumReadDtos){
        return CourseReadDto.builder()
                .courseIndex(course.getId())
                .category(course.getCategory())
                .courseName(course.getCourseName())
                .price(course.getPrice())
                .courseDescription(course.getCourseDescription())
                .difficulty(course.getDifficulty())
                .courseTime(course.getCourseTime())
                .skill(course.getSkill())
                .courseTitlePhotoUrl(course.getCourseTitlePhotoUrl())
                .curriculumSum(curriculumReadDtos.size())
                .curriculumReadDtos(curriculumReadDtos)
                .hits(course.getHits())
                .build();
    }
}
