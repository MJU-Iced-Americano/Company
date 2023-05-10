package com.mju.course.presentation.dto.response.admin;

import com.mju.course.domain.model.enums.CourseState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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
    @Schema(description = "사용 기술", defaultValue = "자바")
    private String skill;
//    @Schema(description = "나타난 사용 기술 제외 한 갯수", defaultValue = "5")
//    private int skillSum;
    @Schema(description = "가격", defaultValue = "100000")
    private String price;
    @Schema(description = "코스 시간")
    private int courseTime;
    @Schema(description = "코스 생성일", defaultValue = "23.05.09")
    private LocalDateTime createdAt;
    @Schema(description = "조회수", defaultValue = "2")
    private Long hits;

    public AdminReadCoursesDto(Long id, CourseState status, String category,
                               Integer difficulty, String courseName, String skill,
                               String price, Integer courseTime, LocalDateTime createdAt, Long hits) {
        this.courseIndex = id;
        this.status = status;
        this.category = category;
        this.difficulty = difficulty;
        this.courseName = courseName;
        this.skill = skill;
        this.price = price;
        this.courseTime = courseTime;
        this.createdAt = createdAt;
        this.hits = hits;
    }

//    public static AdminReadCoursesDto of(Course course){
////        return AdminReadCoursesDto.builder()
////                .courseIndex(course.id)
////                .status(String.valueOf(course.status))
////                .category(course.category)
////                .difficulty(course.difficulty)
////                // 강사 이름
////                .courseName(course.courseName)
////                .skill(course.skill)
////                .price(course.price)
////                .courseTime(course.courseTime)
////                .hits(course.hits)
////                .build();
//        return AdminReadCoursesDto.builder()
//                .courseIndex(course.getId())
//                .status(String.valueOf(course.getStatus()))
//                .category(course.getCategory())
//                .difficulty(course.getDifficulty())
//                // 강사 이름
//                .courseName(course.getCourseName())
//                .skill(course.getSkill())
//                .price(course.getPrice())
//                .courseTime(course.getCourseTime())
//                .hits(course.getHits())
//                .build();
//    }

}
