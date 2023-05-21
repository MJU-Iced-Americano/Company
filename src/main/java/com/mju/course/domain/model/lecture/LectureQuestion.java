package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.User;
import com.mju.course.presentation.dto.request.LectureQuestionCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "lecture_question_title")
    private String lectureQuestionTitle;

    @Column(name = "lecture_question")
    private String lectureQuestion;

    @Column(name = "hits")
    private long hits;

    // 양방향 매핑
    @OneToMany(mappedBy = "lectureQuestion")
    private List<LectureAnswer> lectureAnswerList = new ArrayList<>();

    @OneToMany(mappedBy = "lectureQuestion")
    private List<LectureQuestionPhoto> lectureQuestionPhotoList = new ArrayList<>();

    @OneToMany(mappedBy = "lectureQuestion")
    private List<LectureQuestionBookmark> lectureQuestionBookmarks = new ArrayList<>();

    public void updateHits(){
        this.hits +=1;
    }

    @Builder
    public LectureQuestion(Lecture lecture, User user, String lectureQuestion, String lectureQuestionTitle) {
        this.lecture = lecture;
        this.user = user;
        this.lectureQuestionTitle = lectureQuestionTitle;
        this.lectureQuestion = lectureQuestion;
    }

    public static LectureQuestion of(Lecture lecture, User user, LectureQuestionCreateDto lectureQuestionCreateDto){
        return LectureQuestion.builder()
                .lecture(lecture)
                .user(user)
                .lectureQuestion(lectureQuestionCreateDto.getLectureQuestion())
                .lectureQuestionTitle(lectureQuestionCreateDto.getLectureQuestionTitle())
                .build();
    }

}