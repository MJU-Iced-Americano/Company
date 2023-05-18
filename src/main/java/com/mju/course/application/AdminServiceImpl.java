package com.mju.course.application;

import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.course.Curriculum;
import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.domain.model.enums.CourseState;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.repository.course.CurriculumRepository;
import com.mju.course.domain.repository.lecture.LectureRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.admin.AdminReadCoursesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_COURSE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl {

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureRepository lectureRepository;

    private final ResponseService responseService;
    private final S3UploaderService s3UploaderService;

    public CommonResult readAdminCourseList(String state, String order, Pageable pageable) {
        Page<AdminReadCoursesDto> result = courseRepository.readAdminCourseList(state, order, pageable);
        return responseService.getSingleResult(result);
    }

    public CommonResult deleteCourse(Long course_index) {
        // 코스
        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        // 커리 큘럼
        List<Curriculum> findCurriculum = curriculumRepository.findByCourse(findCourse);

        // 강의 삭제
        for(int i=0; i<findCurriculum.size(); i++){
            List<Lecture> lectures = lectureRepository.findByCurriculum(findCurriculum.get(i));
            if(lectures.size() != 0){
                for(int j=0; j< lectures.size(); j++){
                    s3UploaderService.deleteS3File(lectures.get(i).getLectureKey());
                    lectureRepository.delete(lectures.get(i));
                }
            }
        }

        // 커리 큘럼 삭제
        for(int i=0; i< findCurriculum.size(); i++){
            curriculumRepository.delete(findCurriculum.get(i));
        }

        // 코스 삭제
        s3UploaderService.deleteS3File(findCourse.getCourseTitlePhotoKey());
        courseRepository.delete(findCourse);

        return responseService.getSuccessfulResult();
    }


    public CommonResult registerCourse(Long course_index) {
        return updateState(course_index, CourseState.registration, null);
    }

    public CommonResult holdCourse(Long course_index,String comment) {
        return updateState(course_index, CourseState.hold, comment);
    }

    private CommonResult updateState(Long course_index, CourseState status,String comment) {
        Course findCourse = courseRepository.findById(course_index).orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        findCourse.updateState(status, comment);
        courseRepository.save(findCourse);
        return responseService.getSuccessfulResult();
    }

}
