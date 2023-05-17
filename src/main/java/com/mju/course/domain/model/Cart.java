package com.mju.course.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Cart extends BaseTimeEntity {

    @Id
    @Column(name = "cart_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_index")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    @Builder
    public Cart(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    public static Cart of(Course course, User user){
        return Cart.builder()
                .course(course)
                .user(user)
                .build();
    }

}
