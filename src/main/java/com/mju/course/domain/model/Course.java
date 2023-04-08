package com.mju.course.domain.model;

import com.mju.course.domain.model.enums.CourseState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Course extends BaseTimeEntity {

    @Id
    @Column(name = "course_index")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String difficulty;

    @Column(name = "course_time")
    private String course_time;

    @Column(name = "skill")
    private String skill;

    @Column(name = "hits")
    private Long hits;

    @Column(name = "course_period")
    private String course_period;

    @Enumerated(EnumType.STRING)
    private CourseState status;

}
