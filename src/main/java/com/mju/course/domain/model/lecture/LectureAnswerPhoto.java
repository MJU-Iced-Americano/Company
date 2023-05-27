package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LectureAnswerPhoto extends BaseTimeEntity {

    @Id
    @Column(name = "lecture_answer_photo_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="lecture_answer_index")
    private LectureAnswer lectureAnswer;

    @Column(name = "lecture_answer_photo_key")
    private String lectureAnswerPhotoKey;

    @Builder
    public LectureAnswerPhoto(LectureAnswer lectureAnswer, String lectureAnswerPhotoKey) {
        this.lectureAnswer = lectureAnswer;
        this.lectureAnswerPhotoKey = lectureAnswerPhotoKey;
    }

    public static LectureAnswerPhoto of(LectureAnswer saveAnswer, String questionPhotoKey) {
        return LectureAnswerPhoto.builder()
                .lectureAnswer(saveAnswer)
                .lectureAnswerPhotoKey(questionPhotoKey)
                .build();
    }
}
