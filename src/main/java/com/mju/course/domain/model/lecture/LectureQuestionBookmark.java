package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LectureQuestionBookmark extends BaseTimeEntity {

    @Id
    @Column(name = "lecutre_quesiton_bookmark_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="lecture_question_index")
    private LectureQuestion lectureQuestion;

    private String userId;

    @Builder
    public LectureQuestionBookmark(LectureQuestion lectureQuestion, String userId) {
        this.lectureQuestion = lectureQuestion;
        this.userId = userId;
    }

    public static LectureQuestionBookmark of(LectureQuestion lectureQuestion, String userId) {
        return LectureQuestionBookmark.builder()
                .lectureQuestion(lectureQuestion)
                .userId(userId)
                .build();
    }
}
