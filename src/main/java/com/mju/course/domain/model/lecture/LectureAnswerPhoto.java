package com.mju.course.domain.model.lecture;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LectureAnswerPhoto extends BaseTimeEntity {

    @Id
    @Column(name = "lecture_answer_photo_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="lecture_answer_index")
    private LectureAnswer lectureAnswer;

    @Column(name = "lecture_answer_photo_key")
    private String lectureAnswerPhotoKey;
    
}
