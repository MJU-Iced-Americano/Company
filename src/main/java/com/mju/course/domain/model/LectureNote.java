package com.mju.course.domain.model;

import com.mju.course.domain.model.enums.UserType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LectureNote extends BaseTimeEntity {
    @Id
    @Column(name = "course_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 - 강의 산 사람
    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @OneToOne
    @JoinColumn(name = "course_index")
    private Lecture lecture;

    @Column(name = "lecture_note")
    private String lectureNote;

    @Builder
    public LectureNote(Lecture lecture, String lectureNote){
        this.lecture = lecture;
        this.lectureNote = lectureNote;
    }

    public static LectureNote of(Lecture lecture, String lectureNote){
        return LectureNote.builder()
                .lecture(lecture)
                .lectureNote(lectureNote)
                .build();
    }
}
