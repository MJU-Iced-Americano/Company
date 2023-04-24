//package com.mju.course.application;
//
//import com.mju.course.domain.model.Course;
//import com.mju.course.domain.repository.CourseRepository;
//import com.mju.course.infrastructure.config.JasyptConfig;
//import com.mju.course.presentation.dto.request.PostCourseDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestPropertySource(locations = "classpath:test.properties")
//class CourseServiceImplTest {
//
//    @Autowired
//    CourseRepository courseRepository;
//
//    @Autowired
//    private JasyptConfig jasyptConfig;
//    @Test
//    void plus(){
//        int result = 3;
//        assertEquals(3, result);
//    }
//
//    @Test
//    void 코스_DB_저장(){
//        // given
//        PostCourseDto postCourseDto = PostCourseDto.builder()
//                .category("demo")
//                .courseName("demo")
//                .price("test")
//                .courseDescription("test")
//                .difficulty(3)
//                .skill("test")
//                .build();
//        Course course = Course.of(postCourseDto);
//
//        // when
//        Course saveCourse = courseRepository.save(course);
//
//        // then
//        assertEquals(course.getId(), saveCourse.getId());
//    }
//
//}