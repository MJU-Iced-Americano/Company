package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

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

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @Builder
    public LectureQuestionBookmark(LectureQuestion lectureQuestion, User user) {
        this.lectureQuestion = lectureQuestion;
        this.user = user;
    }

    public static LectureQuestionBookmark of(LectureQuestion lectureQuestion, User user) {
        return LectureQuestionBookmark.builder()
                .lectureQuestion(lectureQuestion)
                .user(user)
                .build();
    }
}
