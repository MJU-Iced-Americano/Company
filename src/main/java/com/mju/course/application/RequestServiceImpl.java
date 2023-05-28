package com.mju.course.application;

import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl {

    private final CourseRepository courseRepository;

    public List<RequestUserCourseDto> requestCourseList(Long userId) {
        return courseRepository.requestCourseList(userId);
    }

    public List<RequestCourseLikeDto> requestCourseLike(Long userId) {
        return courseRepository.requestCourseLike(userId);
    }
}
