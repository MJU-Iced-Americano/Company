package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LectureQuestionPhoto extends BaseTimeEntity {

    @Id
    @Column(name = "lecture_question_photo_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="lecture_question_index")
    private LectureQuestion lectureQuestion;

    @Column(name = "lecture_question_photo_key")
    private String lectureQuestionPhotoKey;
    
}
