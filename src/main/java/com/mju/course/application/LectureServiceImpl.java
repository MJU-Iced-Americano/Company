package com.mju.course.application;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.Curriculum;
import com.mju.course.domain.model.Lecture;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.CourseRepository;
import com.mju.course.domain.repository.CurriculumRepository;
import com.mju.course.domain.repository.LectureRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.request.LectureCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.mju.course.domain.model.other.Exception.ExceptionList.*;
import static com.mju.course.domain.model.other.Exception.ExceptionList.EXCEEDED_LECTURE_SEQUENCE;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureServiceImpl implements LectureService{

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureRepository lectureRepository;

    private final ResponseService responseService;
    private final S3UploaderService s3UploaderService;

    @Override
    public CommonResult createLecture(Long course_index, int chapter, int lecture_sequence, LectureCreateDto lectureCreateDto, MultipartFile multipartFile) throws IOException {

        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        Curriculum findCurriculum = curriculumRepository.findByCourseAndChapter(findCourse, chapter)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_CURRICULUM));

        // 이미 등록된 강의 일 때
        Optional<Lecture> checkLecture = lectureRepository.findByCurriculumAndLectureSequence(findCurriculum, lecture_sequence);
        if(checkLecture.isPresent()) throw new CourseException(DUPLICATION_LECTURE);

        // 만약 커리 쿨럼에 등록된 강의 수를 초과 했을 경우
        if(findCurriculum.getLectureSum() < lecture_sequence) throw new CourseException(EXCEEDED_LECTURE_SEQUENCE);

        // 동영상 s3 에 등록
        String dirName = "test1/"+String.valueOf(course_index)+"/" + String.valueOf(chapter);  // 폴더 이름
        String lectureUrl = s3UploaderService.upload(multipartFile, dirName);

        // 동영상 시간 파악

        // 강의 DB 저장
        Lecture lecture = Lecture.of(findCurriculum, lecture_sequence, lectureCreateDto, lectureUrl);
        Lecture saveLecture = lectureRepository.save(lecture);

        // 코스 업데이트

        return responseService.getSuccessfulResult();
    }

}
