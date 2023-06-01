package com.mju.course.domain.model.course;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CourseLike extends BaseTimeEntity {

    @Id
    @Column(name = "course_like_Index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_index")
    private Course course;

    private String userId;

    @Builder
    public CourseLike(Course course, String userId) {
        this.course = course;
        this.userId = userId;
    }

    public static CourseLike of(Course course, String userId){
        return CourseLike.builder()
                .course(course)
                .userId(userId)
                .build();
    }
}
