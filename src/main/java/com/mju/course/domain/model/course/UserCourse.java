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
public class UserCourse extends BaseTimeEntity {

    @Id
    @Column(name = "user_course_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @ManyToOne
    @JoinColumn(name="course_index")
    private Course course;

    private boolean courseStatus;

    @Builder
    public UserCourse(String userId, Course course, boolean courseStatus) {
        this.userId = userId;
        this.course = course;
        this.courseStatus = courseStatus;
    }

    public static UserCourse of(String userId, Course course){
        return UserCourse.builder()
                .userId(userId)
                .course(course)
                .courseStatus(true)
                .build();
    }

}
