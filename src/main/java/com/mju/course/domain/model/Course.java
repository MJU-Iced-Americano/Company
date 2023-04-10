package com.mju.course.domain.model;

import com.mju.course.domain.model.enums.CourseState;
import com.mju.course.presentation.dto.PostCourseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Course extends BaseTimeEntity {

    @Id
    @Column(name = "course_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 - 강사진

    @Column(name = "category")
    private String category;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "price")
    private String price;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "course_time")
    private int courseTime;

    @Column(name = "skill")
    private String skill;

    @Column(name = "hits")
    private Long hits;

    @Column(name = "course_period")
    private String coursePeriod;

    @Enumerated(EnumType.STRING)
    private CourseState status;

    @Column(name = "comment")
    private String comment; // 보류 이유

    @Builder
    public Course(String category, String courseName, String price, String courseDescription,
                  int difficulty, int courseTime, String skill, Long hits, String coursePeriod, CourseState status){
        this.category = category;
        this.courseName = courseName;
        this.price = price;
        this.courseDescription = courseDescription;
        this.difficulty = difficulty;
        this.courseTime = courseTime;
        this.skill = skill;
        this.coursePeriod = coursePeriod;
        this.hits = hits;
        this.status = status;
    }

    public static Course of(PostCourseDto postCourseDto){
        return Course.builder()
                .category(postCourseDto.getCategory())
                .courseName(postCourseDto.getCourseName())
                .price(postCourseDto.getPrice())
                .courseDescription(postCourseDto.getCourseDescription())
                .difficulty(postCourseDto.getDifficulty())
                .courseTime(postCourseDto.getCourseTime())
                .skill(postCourseDto.getSkill())
                .coursePeriod(postCourseDto.getCoursePeriod())
                .hits(0L)
                .status(CourseState.hold)
                .build();
    }

    public void updateState(CourseState status,String comment) {
        this.status = status;
        this.comment = comment;
    }

}
