package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
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

    @Builder
    public LectureQuestionPhoto(LectureQuestion lectureQuestion, String lectureQuestionPhotoKey) {
        this.lectureQuestion = lectureQuestion;
        this.lectureQuestionPhotoKey = lectureQuestionPhotoKey;
    }

    public static LectureQuestionPhoto of(LectureQuestion lectureQuestion, String lectureQuestionPhotoKey) {
        return LectureQuestionPhoto.builder()
                .lectureQuestion(lectureQuestion)
                .lectureQuestionPhotoKey(lectureQuestionPhotoKey)
                .build();
    }
}
