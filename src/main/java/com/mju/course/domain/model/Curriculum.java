package com.mju.course.domain.model;

import com.mju.course.presentation.dto.request.CurriculumCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
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
    private String curriculumTitle;

    @Column(name = "lecture_sum")
    private int lectureSum;

    @Builder
    public Curriculum(int chapter, String curriculumTitle, int lectureSum, Course course){
        this.course = course;
        this.chapter = chapter;
        this.lectureSum = lectureSum;
        this.curriculumTitle = curriculumTitle;
    }

    public static Curriculum of(CurriculumCreateDto curriculumCreateDto, Course course){
        return Curriculum.builder()
                .chapter(curriculumCreateDto.getChapter())
                .curriculumTitle(curriculumCreateDto.getCurriculumTitle())
                .lectureSum(curriculumCreateDto.getLectureSum())
                .course(course)
                .build();
    }

}
