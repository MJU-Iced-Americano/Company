package com.mju.course.domain.model.course;

import com.mju.course.domain.model.BaseTimeEntity;
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

    private String userId;

    @Builder
    public Cart(Course course, String userId) {
        this.course = course;
        this.userId = userId;
    }

    public static Cart of(Course course, String userId){
        return Cart.builder()
                .course(course)
                .userId(userId)
                .build();
    }

}
