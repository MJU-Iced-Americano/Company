package com.mju.course.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Skill extends BaseTimeEntity {

    @Id
    @Column(name = "skill_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_index")
    private Course course;

    @Column(name = "skill")
    private String skill;

    @Builder
    public Skill(Course course, String skill) {
        this.course = course;
        this.skill = skill;
    }
}
