package com.mju.course.application;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.enums.CourseState;
import com.mju.course.domain.model.other.Exception.ExceptionList;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.CourseRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.PostCourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_COURSE;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final ResponseService responseService;

    @Override
    public CommonResult createCourse(PostCourseDto postCourseDto) {
        Course course = Course.of(postCourseDto);
        courseRepository.save(course);
        return responseService.getSuccessfulResult();
    }

    @Override
    public CommonResult registerCourse(Long course_index) {
        return updateState(course_index, CourseState.registration, null);
    }

    @Override
    public CommonResult holdCourse(Long course_index,String comment) {
        return updateState(course_index, CourseState.hold, comment);
    }

    @Override
    public CommonResult deleteCourse(Long course_index, String comment) {
        return updateState(course_index, CourseState.delete, comment);
    }

    private CommonResult updateState(Long course_index, CourseState status,String comment) {
        Optional<Course> course = courseRepository.findById(course_index);
        if(course.isEmpty()){
            return responseService.getFailResult(NOT_EXISTENT_COURSE.getCode(), NOT_EXISTENT_COURSE.getMessage());
        }
        course.get().updateState(status, comment);
        courseRepository.save(course.get());
        return responseService.getSuccessfulResult();
    }

}
