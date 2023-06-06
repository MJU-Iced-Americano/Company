package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.course.Curriculum;
import com.mju.course.presentation.dto.request.LectureCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Lecture extends BaseTimeEntity {

    @Id
    @Column(name = "lecture_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="curriculum_index")
    private Curriculum curriculum;

    @Column(name = "lecture_sequence")
    private int lectureSequence;

    @Column(name = "lecture_title")
    private String lectureTitle;

    @Column(name = "lecture_time")
    private int lectureTime;

    @Column(name = "lecture_key")
    private String lectureKey;

    @Column(name = "lecutre_input_key")
    private String lectureInputKey;

    @Column(name = "lecture_description")
    private String lectureDescription;
    
    private String hls360;
    private String hls540;
    private String hls720;
    private String mp4740;

    // 양방향 매핑
    @OneToMany(mappedBy = "lecture")
    private List<LectureQuestion> lectureQuestionList = new ArrayList<>();

    @Builder
    public Lecture(Curriculum curriculum, int lectureSequence, String lectureTitle,
                   int lectureTime, String lectureKey, String lectureDescription, String lectureInputKey){
        this.curriculum = curriculum;
        this.lectureSequence = lectureSequence;
        this.lectureTitle = lectureTitle;
        this.lectureTime = lectureTime;
        this.lectureKey = lectureKey;
        this.lectureInputKey = lectureInputKey;
        this.lectureDescription= lectureDescription;
    }

    public static Lecture of(Curriculum curriculum, int lecture_sequence,
                             LectureCreateDto lectureCreateDto, String lectureKey, String inputKey){
        return Lecture.builder()
                .curriculum(curriculum)
                .lectureSequence(lecture_sequence)
                .lectureTime(lectureCreateDto.getLectureTime())
                .lectureTitle(lectureCreateDto.getLectureTitle())
                .lectureKey(lectureKey)
                .lectureInputKey(inputKey)
                .lectureDescription(lectureCreateDto.getLectureDescription())
                .build();
    }

    public void updateLectureTitle (String lectureTitle){
        this.lectureTitle = lectureTitle;
    }

    public void updateLectureDescription(String lectureDescription){
        this.lectureDescription = lectureDescription;
    }

    public void saveVideo(String basicFileName){
        String hls360 = basicFileName+"/Default/HLS/"+basicFileName+"_360.m3u8";
        String hls540 = basicFileName+"/Default/HLS/"+basicFileName+"_540.m3u8";
        String hls720 = basicFileName+"/Default/HLS/"+basicFileName+"_720.m3u8";
        String mp4740 = basicFileName+"/Default/MP4/"+basicFileName+".mp4";

        this.hls360 = hls360;
        this.hls540 = hls540;
        this.hls720 = hls720;
        this.mp4740 = mp4740;
    }
}
