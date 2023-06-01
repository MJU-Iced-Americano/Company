package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
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

    private String userId;

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
    public LectureQuestion(Lecture lecture, String userId, String lectureQuestion, String lectureQuestionTitle) {
        this.lecture = lecture;
        this.userId = userId;
        this.lectureQuestionTitle = lectureQuestionTitle;
        this.lectureQuestion = lectureQuestion;
    }

    public static LectureQuestion of(Lecture lecture, String userId, LectureQuestionCreateDto lectureQuestionCreateDto){
        return LectureQuestion.builder()
                .lecture(lecture)
                .userId(userId)
                .lectureQuestion(lectureQuestionCreateDto.getLectureQuestion())
                .lectureQuestionTitle(lectureQuestionCreateDto.getLectureQuestionTitle())
                .build();
    }

}