package com.mju.course.presentation.dto.response.admin;

import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.enums.CourseState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class AdminReadCoursesDto {

    @Schema(description = "코스 인덱스", defaultValue = "2")
    private Long courseIndex;

    @Schema(description = "코스 상태", defaultValue = "registration")
    private CourseState status;

    @Schema(description = "카테 고리", defaultValue = "프로그래밍")
    private String category;

    @Schema(description = "난이도", defaultValue = "2")
    private int difficulty;

    @Schema(description = "코스 이름", defaultValue = "자바 기초")
    private String courseName;

    @Schema(description = "가격", defaultValue = "100000")
    private Long price;

    @Schema(description = "코스 시간")
    private int courseTime;

    @Schema(description = "코스 생성일")
    private LocalDateTime createdAt;

    @Schema(description = "조회수", defaultValue = "2")
    private long hits;

    @Schema(description = "스킬 리스트")
    private List<String> skillList;

    public static AdminReadCoursesDto of(Course course){
        List<String> skills = new ArrayList<>();
        course.getSkillList().forEach(getSkill ->{skills.add(getSkill.getSkill());});

        return AdminReadCoursesDto.builder()
                .courseIndex(course.getId())
                .status(course.getStatus())
                .category(course.getCategory())
                .difficulty(course.getDifficulty())
                .courseName(course.getCourseName())
                .price(course.getPrice())
                .courseTime(course.getCourseTime())
                .createdAt(course.getCreatedAt())
                .hits(course.getHits())
                .skillList(skills)
                .build();
    }
}
