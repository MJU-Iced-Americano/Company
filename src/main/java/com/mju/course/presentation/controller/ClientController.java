package com.mju.course.presentation.controller;

import com.mju.course.application.course.CourseService;
import com.mju.course.presentation.dto.response.ClientReadCourseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Tag(name = "ClientController", description = "MSA 연결 관련 api")
public class ClientController {

    private final CourseService courseService;
    @Operation(summary = "코스 인덱스, 강사진 정보")
    @GetMapping("/course/{course_index}")
    public ClientReadCourseDto returnCourse(@PathVariable Long course_index){
        return courseService.returnCourse(course_index);
    }

    @Operation(summary = "코해당 코스를 수강신청한 유저들")
    @GetMapping("/course/{course_index}/users")
    public List<String> returnCourseUsers(@PathVariable Long course_index){
        return courseService.returnCourseUsers(course_index);
    }

}
