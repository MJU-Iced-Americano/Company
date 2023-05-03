package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@Builder
public class CourseReadDto {

    // 코스
    @Schema(description = "코스 인덱스", defaultValue = "2")
    private Long courseIndex;
    @Schema(description = "카테고리", defaultValue = "프로그래밍")
    private String category;
    @Schema(description = "코스 이름", defaultValue = "자바 기초")
    private String courseName;
    @Schema(description = "코스 가격", defaultValue = "100000")
    private String price;
    @Schema(description = "코스 설명", defaultValue = "자바 기초")
    private String courseDescription;
    @Schema(description = "난이도", defaultValue = "2")
    private int difficulty;
    @Schema(description = "코스 생성 시간")
    private int courseTime;
    @Schema(description = "스킬", defaultValue = "자바")
    private String skill;
    @Schema(description = "조회수", defaultValue = "2")
    private Long hits;
    @Schema(description = "코스 기본(타이틀) 사진 url")
    private String courseTitlePhotoUrl;

    @Schema(description = "커리 큘럼 수", defaultValue = "3")
    private int curriculumSum;

    @Schema(description = "커리 큘럼 객체")
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
                .courseTitlePhotoUrl("https://d19wla4ff811v8.cloudfront.net/" + course.getCourseTitlePhotoUrl())
                .curriculumSum(curriculumReadDtos.size())
                .curriculumReadDtos(curriculumReadDtos)
                .hits(course.getHits())
                .build();
    }
}
