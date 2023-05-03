package com.mju.course.application;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.Curriculum;
import com.mju.course.domain.model.Lecture;
import com.mju.course.domain.model.enums.CourseState;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.CourseRepository;
import com.mju.course.domain.repository.CurriculumRepository;
import com.mju.course.domain.repository.LectureRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.request.*;
import com.mju.course.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mju.course.domain.model.other.Exception.ExceptionList.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureRepository lectureRepository;

    private final ResponseService responseService;
    private final S3UploaderService s3UploaderService;

    @Transactional
    @Override
    public CommonResult createCourse(CourseCreateDto courseCreateDto, MultipartFile titlePhoto) throws IOException {
        Optional<Course> checkCourse = courseRepository.findByCourseName(courseCreateDto.getCourseName());
        if(checkCourse.isPresent()) throw new CourseException(DUPLICATION_COURSE_NAME);

        // 코스 저장
        Course course = Course.of(courseCreateDto);
        Course saveCourse = courseRepository.save(course);

        // 코스 대표 사진 저장
        String dirName = "courses/"+String.valueOf(saveCourse.getId()) +"/title";  // 폴더 이름
        String courseTitlePhotoUrl = s3UploaderService.upload(titlePhoto, dirName);
        course.updateTitlePhoto(courseTitlePhotoUrl);
        courseRepository.save(saveCourse);

        // 커리 큘럼 저장
        for(int i = 0; i< courseCreateDto.getCurriculumCreateDtos().size(); i++){
            Curriculum curriculum = Curriculum.of(courseCreateDto.getCurriculumCreateDtos().get(i), saveCourse);
            curriculumRepository.save(curriculum);
        }

        // 코스 설명 사진 저장

        return responseService.getSuccessfulResult();
    }

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

    @Override
    public CommonResult updateCourse(Long course_index, CourseUpdateDto courseUpdateDto) {
        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        boolean isModified = false; // 수정 유무

        ArrayList<String> arr = new ArrayList<>();

        if(courseUpdateDto.getCategory() != null && !courseUpdateDto.getCategory().equals(findCourse.getCategory())){
            findCourse.updateCategory(courseUpdateDto.getCategory());
            arr.add("카테고리");
            isModified = true;
        }

        if(courseUpdateDto.getCourseName() != null && !courseUpdateDto.getCourseName().equals(findCourse.getCourseName())){
            // 코스 이름 중복 확인 - 작동이 안됨
            Optional<Course> checkCourse = courseRepository.findByCourseName(courseUpdateDto.getCourseName());
            if(checkCourse.isPresent()) {
                throw new CourseException(DUPLICATION_COURSE_NAME);
            }else{
                arr.add("코스 이름");
                findCourse.updateCourseName(courseUpdateDto.getCourseName());
                isModified = true;
            }
        }

        if(courseUpdateDto.getPrice() != null && !courseUpdateDto.getPrice().equals(findCourse.getPrice())){
            arr.add("코스 가격");
            findCourse.updatePrice(courseUpdateDto.getPrice());
            isModified = true;
        }
        if(courseUpdateDto.getCourseDescription() != null && !courseUpdateDto.getCourseDescription().equals(findCourse.getCourseDescription())){
            arr.add("코스 설명");
            findCourse.updateCourseDescription(courseUpdateDto.getCourseDescription());
            isModified = true;
        }
        if(courseUpdateDto.getDifficulty() != 0 && courseUpdateDto.getDifficulty() != findCourse.getDifficulty()){
            arr.add("난이도");
            findCourse.updateDifficulty(courseUpdateDto.getDifficulty());
            isModified = true;
        }
        if(courseUpdateDto.getSkill() != null && !courseUpdateDto.getSkill().equals(findCourse.getSkill())){
            arr.add("스킬");
            findCourse.updateSkill(courseUpdateDto.getSkill());
            isModified = true;
        }

        if(isModified == false){
            throw new CourseException(NO_MODIFIED_COURSE);
        }else{
            courseRepository.save(findCourse);
            return responseService.getSingleResult(arr +"가 수정되었습니다.");
        }
    }

    @Override
    public CommonResult updateCurriculum(Long course_index, int chapter) {
        return null;
    }


    @Override
    public CommonResult requestCourse(Long course_index) {
        // 코스에 대한 커리 쿨럼 정보를 불러 와서 강의 시간을 얻어와 코스의 합을 구함
        Optional<Course> findCourse = courseRepository.findById(course_index);
        // 존재하는 코스인지 확인

//        ArrayList<Curriculum> curriculumArrayList = curriculumRepository.findByCourse(findCourse.get());
        // 존재하는 커리쿨럼인지 확인

        return null;
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
