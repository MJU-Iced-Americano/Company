package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class LectureAnswer extends BaseTimeEntity {

    @Id
    @Column(name = "lecture_answer_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="lecture_question_index")
    private LectureQuestion lectureQuestion;

    private String userId;

    @Column(name = "lecture_answer")
    private String lectureAnswer;

    // 양방향 매핑
    @OneToMany(mappedBy = "lectureAnswer")
    private List<LectureAnswerPhoto> lectureAnswerPhotoList = new ArrayList<>();

    @Builder
    public LectureAnswer(LectureQuestion lectureQuestion, String userId, String lectureAnswer) {
        this.lectureQuestion = lectureQuestion;
        this.userId = userId;
        this.lectureAnswer = lectureAnswer;
    }

    public static LectureAnswer of(LectureQuestion lectureQuestion, String userId, String answer) {
        return LectureAnswer.builder()
                .lectureQuestion(lectureQuestion)
                .userId(userId)
                .lectureAnswer(answer)
                .build();
    }
}
