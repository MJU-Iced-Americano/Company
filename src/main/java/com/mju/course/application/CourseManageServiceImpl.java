package com.mju.course.application;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.Curriculum;
import com.mju.course.domain.model.Lecture;
import com.mju.course.domain.model.enums.CourseState;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.repository.course.CurriculumRepository;
import com.mju.course.domain.repository.lecture.LectureRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.request.*;
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
public class CourseManageServiceImpl implements CourseManageService {

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
        String basicFileName = course.getId() + "-title" ;
        String dirName = "courses/"+String.valueOf(saveCourse.getId()) +"/title";  // 폴더 이름
        String courseTitlePhotoKey = s3UploaderService.upload(titlePhoto, dirName, basicFileName);
        course.updateTitlePhoto(courseTitlePhotoKey);
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
    public CommonResult updateCourse(Long course_index, CourseUpdateDto courseUpdateDto, MultipartFile titlePhoto) throws IOException {
        Course findCourse = findCourse(course_index);

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

        if(titlePhoto != null){
            arr.add("타이틀 사진");
            s3UploaderService.deleteS3File(findCourse.getCourseTitlePhotoKey());

            // 사진 등록
            String basicFileName = findCourse.getId() + "-title" ;
            String dirName = "courses/"+String.valueOf(findCourse.getId()) +"/title";  // 폴더 이름
            String courseTitlePhotoUrl = s3UploaderService.upload(titlePhoto, dirName, basicFileName);
            findCourse.updateTitlePhoto(courseTitlePhotoUrl);
            isModified = true;
        }

        if(!isModified){
            throw new CourseException(NO_MODIFIED_COURSE);
        }else{
            courseRepository.save(findCourse);
            return responseService.getSingleResult(arr +"가 수정되었습니다.");
        }
    }

    @Override
    public CommonResult deleteCourse(Long course_index, String comment) {
        if(comment != null){
            return updateState(course_index, CourseState.delete, comment);
        }else{
            throw new CourseException(PLEASE_COURSE_DELETE_REASON);
        }
    }

    private CommonResult updateState(Long course_index, CourseState status,String comment) {
        Course findCourse = findCourse(course_index);

        findCourse.updateState(status, comment);
        courseRepository.save(findCourse);
        return responseService.getSuccessfulResult();
    }

    @Override
    public CommonResult requestCourse(Long course_index) {
        Course findCourse = findCourse(course_index);

        List<Curriculum> findCurriculum = curriculumRepository.findByCourse(findCourse); // 에러 처리

        boolean checkLecture = false;
        for(int i=0; i<findCurriculum.size();i++){
            List<Lecture> lectures = lectureRepository.findByCurriculum(findCurriculum.get(i));
            if(lectures.size() != findCurriculum.get(i).getLectureSum()) checkLecture = true;
        }
        if(!checkLecture){
            throw new CourseException(DIFFERENT_LECTURE_SUM);
        }else{
            return responseService.getSingleResult("코스가 신청되었습니다.");
        }

    }

    @Override
    public CommonResult addCurriculum(Long course_index, CurriculumCreateDto curriculumCreateDto) {
        Course findCourse = findCourse(course_index);

        Optional<Curriculum> findCurriculum1 = curriculumRepository.findByCourseAndChapter(findCourse, curriculumCreateDto.getChapter());
        if(findCurriculum1.isPresent()){
            throw new CourseException(EXISTENT_CURRICULUM_CHAPTER);
        }
        Optional<Curriculum> findCurriculum = curriculumRepository.findByCourseAndCurriculumTitle(findCourse, curriculumCreateDto.getCurriculumTitle());
        if(findCurriculum.isPresent()){
            throw new CourseException(EXISTENT_CURRICULUM_NAME);
        }

        Curriculum curriculum = Curriculum.of(curriculumCreateDto, findCourse);
        curriculumRepository.save(curriculum);
        return responseService.getSingleResult("커리 큘럼이 추가되었습니다.");
    }

    @Override
    public CommonResult updateCurriculum(Long course_index, int chapter, CurriculumCreateDto curriculumCreateDto) {
        Course findCourse = findCourse(course_index);
        Optional<Curriculum> findCurriculum = Optional.ofNullable(curriculumRepository.findByCourseAndChapter(findCourse, chapter)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_CURRICULUM)));

        List<String> arr = new ArrayList<>();
        boolean isModified = false;

        Optional<Curriculum> findCurriculumChapter = curriculumRepository.findByCourseAndChapter(findCourse, curriculumCreateDto.getChapter());
        if(findCurriculumChapter.isPresent()){
            throw new CourseException(EXISTENT_CURRICULUM_CHAPTER);
        }
        Optional<Curriculum> findCurriculumTitle = curriculumRepository.findByCourseAndCurriculumTitle(findCourse, curriculumCreateDto.getCurriculumTitle());
        if(findCurriculumTitle.isPresent()){
            throw new CourseException(EXISTENT_CURRICULUM_NAME);
        }

        if(curriculumCreateDto.getLectureSum() != 0){
            List<Lecture> lectures = lectureRepository.findByCurriculum(findCurriculum.get());
            if(lectures.size() >= curriculumCreateDto.getLectureSum()){
                throw new CourseException(EXCEEDED_LECTURE_CURRICULUM);
            }
        }

        if(curriculumCreateDto.getChapter() != 0 && curriculumCreateDto.getChapter() != findCurriculum.get().getChapter()){
            arr.add("챕터");
            findCurriculum.get().updateChapter(curriculumCreateDto.getChapter());
            isModified = true;
        }
        if(curriculumCreateDto.getCurriculumTitle() != null && !curriculumCreateDto.getCurriculumTitle().equals(findCurriculum.get().getCurriculumTitle())){
            arr.add("커리큘럼 제목");
            findCurriculum.get().updateCurriculumTitle(curriculumCreateDto.getCurriculumTitle());
            isModified = true;
        }
        if(curriculumCreateDto.getLectureSum() != 0 && curriculumCreateDto.getLectureSum() != findCurriculum.get().getLectureSum()){
            arr.add("강의 수");
            findCurriculum.get().updateLectureSum(curriculumCreateDto.getLectureSum());
            isModified = true;
        }

        if(!isModified){
            throw new CourseException(NO_MODIFIED_CURRICULUM);
        }else{
            curriculumRepository.save(findCurriculum.get());
            return responseService.getSingleResult(arr +"가 수정되었습니다.");
        }
    }

    @Override
    public CommonResult deleteCurriculum(Long course_index, int chapter) {
        Course findCourse = findCourse(course_index);
        Optional<Curriculum> findCurriculum = Optional.ofNullable(curriculumRepository.findByCourseAndChapter(findCourse, chapter)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_CURRICULUM)));
        List<Lecture> lectures = lectureRepository.findByCurriculum(findCurriculum.get());
        if(lectures.size() == 0){
            curriculumRepository.delete(findCurriculum.get());
            return responseService.getSingleResult("커리큘럼이 삭제되었습니다.");
        }else{
            throw new CourseException(EXISTENT_CURRICULUM_LECTURE);
        }
    }

    private Course findCourse(Long course_index){
        return courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
    }

}
