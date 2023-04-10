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
    private String course_name;

    @Column(name = "price")
    private String price;

    @Column(name = "course_description")
    private String course_description;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "course_time")
    private int course_time;

    @Column(name = "skill")
    private String skill;

    @Column(name = "hits")
    private Long hits;

    @Column(name = "course_period")
    private String course_period;

    @Enumerated(EnumType.STRING)
    private CourseState status;

    @Column(name = "comment")
    private String comment; // 보류 이유

    @Builder
    public Course(String category, String course_name, String price, String course_description,
                  int difficulty, int course_time, String skill, Long hits, String course_period, CourseState status){
        this.category = category;
        this.course_name = course_name;
        this.price = price;
        this.course_description = course_description;
        this.difficulty = difficulty;
        this.course_time = course_time;
        this.skill = skill;
        this.course_period = course_period;
        this.hits = hits;
        this.status = status;
    }

    public static Course of(PostCourseDto postCourseDto){
        return Course.builder()
                .category(postCourseDto.getCategory())
                .course_name(postCourseDto.getCourse_name())
                .price(postCourseDto.getPrice())
                .course_description(postCourseDto.getCourse_description())
                .difficulty(postCourseDto.getDifficulty())
                .course_time(postCourseDto.getCourse_time())
                .skill(postCourseDto.getSkill())
                .course_period(postCourseDto.getCourse_period())
                .hits(0L)
                .status(CourseState.hold)
                .build();
    }

    public void updateState(CourseState status,String comment) {
        this.status = status;
        this.comment = comment;
    }

}
