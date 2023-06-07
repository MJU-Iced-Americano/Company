package com.mju.course.application.course;

import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageServiceImpl {

    private final CourseRepository courseRepository;

    public List<MyPageUserCourseDto> requestCourseList(String userId) {
        return courseRepository.requestCourseList(userId);
    }

    public List<MyPageCourseLikeDto> requestCourseLike(String userId) {
        return courseRepository.requestCourseLike(userId);
    }

    public List<MyPageCartDto> readCart(String userId) {
        return courseRepository.readCart(userId);
    }
}
