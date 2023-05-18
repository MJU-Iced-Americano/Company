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

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @Builder
    public CourseLike(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    public static CourseLike of(Course course, User user){
        return CourseLike.builder()
                .course(course)
                .user(user)
                .build();
    }
}
