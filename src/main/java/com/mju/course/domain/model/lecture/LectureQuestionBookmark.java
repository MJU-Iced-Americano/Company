package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.User;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;
}
