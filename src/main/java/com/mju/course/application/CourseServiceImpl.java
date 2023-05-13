package com.mju.course.application;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.Curriculum;
import com.mju.course.domain.model.Lecture;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.repository.course.CurriculumRepository;
import com.mju.course.domain.repository.course.SkillRepository;
import com.mju.course.domain.repository.lecture.LectureRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.CourseReadDto;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import com.mju.course.presentation.dto.response.CurriculumReadDto;
import com.mju.course.presentation.dto.response.LectureReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_COURSE;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final SkillRepository skillRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureRepository lectureRepository;

    private final ResponseService responseService;

    // 코스 목록 보기
    @Override
    public CommonResult readCourseList(String category, String order, List<String> skill, Pageable pageable){
        Page<CoursesReadDto> result = courseRepository.readCourseList(category, order, skill, pageable);
        result.forEach(readDto -> readDto.updateUrl(readDto.getCourseTitlePhotoUrl()));  // key를 url로 변경
        return responseService.getSingleResult(result);
    }

    @Override
    public CommonResult readCourse(Long course_index) {
        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        // 조회 수 증가
        findCourse.updateHit();
        courseRepository.save(findCourse);

        // 스킬
        ArrayList<String> skillList = skillRepository.findByCourse(findCourse);

        // 커리 큘럼, 강의
        List<Curriculum> findCurriculum = curriculumRepository.findByCourse(findCourse); // 에러 처리
        ArrayList<CurriculumReadDto> curriculumReadDtoList = new ArrayList<>();
        findCurriculum.forEach(curriculum -> {
            List<Lecture> lectures = lectureRepository.findByCurriculum(curriculum);
            List<LectureReadDto> lectureReadDtos = lectures
                    .stream()
                    .map(LectureReadDto::of)
                    .collect(Collectors.toList());
            curriculumReadDtoList.add(CurriculumReadDto.of(curriculum, lectureReadDtos));
        });

        CourseReadDto courseReadDto = CourseReadDto.of(findCourse, skillList, curriculumReadDtoList);
        return responseService.getSingleResult(courseReadDto);
    }

}
