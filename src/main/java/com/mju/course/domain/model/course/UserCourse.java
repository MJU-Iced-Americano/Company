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

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @ManyToOne
    @JoinColumn(name="course_index")
    private Course course;

    private boolean courseStatus;

    @Builder
    public UserCourse(User user, Course course, boolean courseStatus) {
        this.user = user;
        this.course = course;
        this.courseStatus = courseStatus;
    }

    public static UserCourse of(User user, Course course){
        return UserCourse.builder()
                .user(user)
                .course(course)
                .courseStatus(true)
                .build();
    }

}
