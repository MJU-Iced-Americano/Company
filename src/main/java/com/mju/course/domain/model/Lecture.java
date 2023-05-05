package com.mju.course.domain.model;

import com.mju.course.presentation.dto.request.LectureCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "lecture_key")
    private String lectureKey;

    @Column(name = "lecture_description")
    private String lectureDescription;

    @Builder
    public Lecture(Curriculum curriculum, int lectureSequence, String lectureTitle,
                   int lectureTime, String lectureKey, String lectureDescription){
        this.curriculum = curriculum;
        this.lectureSequence = lectureSequence;
        this.lectureTitle = lectureTitle;
        this.lectureTime = lectureTime;
        this.lectureKey = lectureKey;
        this.lectureDescription= lectureDescription;
    }

    public static Lecture of(Curriculum curriculum, int lecture_sequence, LectureCreateDto lectureCreateDto, String lectureUrl){
        return Lecture.builder()
                .curriculum(curriculum)
                .lectureSequence(lecture_sequence)
                .lectureTitle(lectureCreateDto.getLectureTitle())
                .lectureKey(lectureUrl)
                .lectureDescription(lectureCreateDto.getLectureDescription())
                .build();
    }

    public void updateLectureTitle (String lectureTitle){
        this.lectureTitle = lectureTitle;
    }

    public void updateLectureDescription(String lectureDescription){
        this.lectureDescription = lectureDescription;
    }

}
