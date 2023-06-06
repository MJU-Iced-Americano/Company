package com.mju.course.application;

import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.course.Curriculum;
import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.domain.model.course.enums.CourseState;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.*;
import com.mju.course.domain.repository.lecture.LectureRepository;
import com.mju.course.domain.service.LectureDomainService;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.admin.AdminReadCoursesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_COURSE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl {

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;

    private final CourseLikeRepository courseLikeRepository;
    private final CartRepository cartRepository;
    private final SkillRepository skillRepository;
    private final UserCourseRepository userCourseRepository;

    private final S3UploaderService s3UploaderService;
    private final LectureDomainService lectureDomainService;

    /**
     * (운영자) 코스 조회 리스트 조회
     * @param state
     * @param order
     * @param pageable
     * */
    public Page<AdminReadCoursesDto> readAdminCourseList(String state, String order, Pageable pageable) {
        Page<AdminReadCoursesDto> result = courseRepository.readAdminCourseList(state, order, pageable);
        return result;
    }

    /**
     * 코스 삭제하기
     * @param course_index
     * */
    @Transactional
    public void deleteCourse(Long course_index) {
        // 코스 찾기
        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        // 코스 좋아요
        findCourse.getCourseLikeList()
                .forEach(like->{
                    courseLikeRepository.delete(like);
                });

        // 장바구니
        findCourse.getCartList()
                .forEach(cart -> {
                    cartRepository.delete(cart);
                });

        // 사용 기술
        findCourse.getSkillList()
                .forEach(skill -> {
                    skillRepository.delete(skill);
                });

        // 수강 신청 리스트
        findCourse.getUserCourseList()
                .forEach(userCourse -> {
                    userCourseRepository.delete(userCourse);
                });

        // 커리 큘럼
        findCourse.getCurriculumList()
                .forEach(curriculum -> {
                    curriculum.getLectureList()
                            .forEach(lecture -> {
                                lectureDomainService.deleteLecture(lecture.getId());
                            });
                    curriculumRepository.delete(curriculum);
                });

        // 코스 삭제
        s3UploaderService.deleteS3File(findCourse.getCourseTitlePhotoKey());
        courseRepository.delete(findCourse);
    }

    /**
     * (운영자) 코스 등록
     * */
    public void registerCourse(Long course_index) {
        updateState(course_index, CourseState.registration, null);
    }

    /**
     * (운영자) 코스 등록 보류
     * */
    public void holdCourse(Long course_index,String comment) {
        updateState(course_index, CourseState.hold, comment);
    }

    private void updateState(Long course_index, CourseState status,String comment) {
        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        findCourse.updateState(status, comment);
        courseRepository.save(findCourse);
    }

}
