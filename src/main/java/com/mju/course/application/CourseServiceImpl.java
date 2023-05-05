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
import com.mju.course.presentation.dto.response.CourseReadDto;
import com.mju.course.presentation.dto.response.CurriculumReadDto;
import com.mju.course.presentation.dto.response.LectureReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_COURSE;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureRepository lectureRepository;

    private final ResponseService responseService;

    @Override
    public CommonResult readCourse(Long course_index) {
        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        // 조회 수 증가
        findCourse.updateHit();
        courseRepository.save(findCourse);

        List<Curriculum> findCurriculum = curriculumRepository.findByCourse(findCourse); // 에러 처리

        ArrayList<CurriculumReadDto> curriculumReadDtos = new ArrayList<>();
        for(int i=0; i<findCurriculum.size(); i++){
            List<LectureReadDto> lectureReadDtos = new ArrayList<>();
            List<Lecture> lectures = lectureRepository.findByCurriculum(findCurriculum.get(i));
            for(int j=0; j< lectures.size(); j++){
                LectureReadDto lectureReadDto = LectureReadDto.of(lectures.get(i));
                lectureReadDtos.add(lectureReadDto);
            }
            CurriculumReadDto curriculumReadDto = CurriculumReadDto.of(findCurriculum.get(i), lectureReadDtos);
            curriculumReadDtos.add(curriculumReadDto);
        }

        CourseReadDto courseReadDto = CourseReadDto.of(findCourse, curriculumReadDtos);
        return responseService.getSingleResult(courseReadDto);
    }

}
