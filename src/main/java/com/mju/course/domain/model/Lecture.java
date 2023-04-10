package com.mju.course.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Lecture extends BaseTimeEntity{

    @Id
    @Column(name = "lecture_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="curriculum_index")
    private Curriculum curriculum;

    @Column(name = "lecture_sequence")
    private int lectureSequence;

    @Column(name = "lecture_title")
    private String lectureTitle;

    @Column(name = "lecture_time")
    private int lectureTime;

    @Column(name = "lecture_url")
    private String lectureUrl;

    @Column(name = "lecture_description")
    private String lectureDescription;

}
