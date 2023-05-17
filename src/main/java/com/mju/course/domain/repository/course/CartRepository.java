package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.Cart;
import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCourseAndUser(Course course, User user);
}
