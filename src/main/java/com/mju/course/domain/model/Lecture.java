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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="curriculum_index")
    private Curriculum curriculum;

    @Column(name = "lecture_sequence")
    private int lecture_sequence;

    @Column(name = "lecture_title")
    private String lecture_title;

    @Column(name = "lecture_time")
    private int lecture_time;

    @Column(name = "lecture_url")
    private String lecture_url;

    @Column(name = "lecture_description")
    private String lecture_description;

}
