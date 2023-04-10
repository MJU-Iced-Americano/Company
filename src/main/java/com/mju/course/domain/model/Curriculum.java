package com.mju.course.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Curriculum extends BaseTimeEntity{

    @Id
    @Column(name = "curriculum_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_index")
    private Course course;

    @Column(name = "chapter")
    private int chapter;

    @Column(name = "curriculum_title")
    private String curriculum_title;

}
