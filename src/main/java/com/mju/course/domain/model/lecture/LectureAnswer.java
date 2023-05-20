package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.User;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @Column(name = "lecture_answer")
    private String lectureAnswer;

    // 양방향 매핑
    @OneToMany(mappedBy = "lectureAnswer")
    private List<LectureAnswerPhoto> lectureAnswerPhotoList = new ArrayList<>();
    
}