package com.mju.course.domain.repository;

import com.mju.course.domain.model.Course;
import com.mju.course.presentation.dto.PostCourseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void 코스_이름으로_찾기() {
        // given
        PostCourseDto postCourseDto = PostCourseDto.builder()
                .category("test")
                .courseName("test")
                .price("test")
                .courseDescription("test")
                .difficulty(3)
                .courseTime(100)
                .skill("test")
                .coursePeriod("test")
                .build();
        Course course = Course.of(postCourseDto);

        // when
        Optional<Course> findCourse = courseRepository.findByCourseName(postCourseDto.getCourseName());

        // then
        assertEquals(course.getCourseName(), findCourse.get().getCourseName());
    }
}