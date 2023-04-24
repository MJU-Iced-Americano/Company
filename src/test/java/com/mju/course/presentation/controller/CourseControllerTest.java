//package com.mju.course.presentation.controller;
//
//import com.mju.course.domain.model.Course;
//import com.mju.course.domain.model.Curriculum;
//import com.mju.course.presentation.dto.request.PostCourseDto;
//import com.mju.course.presentation.dto.request.PostCurriculumDto;
//import org.json.JSONArray;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.jar.JarException;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CourseControllerTest {
//
//    private JSONArray arr;
//
//    @BeforeEach
//    public void setting() throws JarException{
//        arr = new JSONArray();
//    }
//
//    @Test
//    void 리스트_포함된_값이_json_으로_들어오는지() {
//        Course course = Course.of(PostCourseDto.builder()
//                .category("test")
//                .courseName("test02")
//                .price("test")
//                .courseDescription("test")
//                .difficulty(3)
//                .skill("test")
//                .build());
//
//        PostCurriculumDto curriculumDto1 = PostCurriculumDto.builder()
//                .chapter(1)
//                .curriculumTitle("test1")
//                .build();
//        PostCurriculumDto curriculumDto2 = PostCurriculumDto.builder()
//                .chapter(2)
//                .curriculumTitle("test1")
//                .build();
//        Curriculum curriculum1 = Curriculum.of(curriculumDto1, course);
//        Curriculum curriculum2 = Curriculum.of(curriculumDto2, course);
//
//
//
//    }
//}