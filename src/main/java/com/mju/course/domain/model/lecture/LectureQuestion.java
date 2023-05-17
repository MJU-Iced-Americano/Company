package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.User;
import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.course.Skill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class LectureQuestion extends BaseTimeEntity {

    @Id
    @Column(name = "lecture_question_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="lecture_index")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @Column(name = "lecture_question")
    private String lectureQuestion;

    // 양방향 매핑
    @OneToMany(mappedBy = "lectureQuestion")
    private List<LectureAnswer> lectureAnswerList = new ArrayList<>();

    @OneToMany(mappedBy = "lectureQuestion")
    private List<LectureQuestionPhoto> lectureQuestionPhotoList = new ArrayList<>();

}
