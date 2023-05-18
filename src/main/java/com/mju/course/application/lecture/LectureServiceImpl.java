package com.mju.course.application.lecture;

import com.mju.course.application.S3UploaderService;
import com.mju.course.domain.model.User;
import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.course.Curriculum;
import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.domain.model.lecture.LectureQuestion;
import com.mju.course.domain.model.lecture.LectureQuestionPhoto;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.UserRepository;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.repository.course.CurriculumRepository;
import com.mju.course.domain.repository.lecture.*;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.CurriculumReadDto;
import com.mju.course.presentation.dto.response.LectureCurriculumReadDto;
import com.mju.course.presentation.dto.response.LectureReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_COURSE;
import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_LECTURE;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureServiceImpl implements LectureService{

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureRepository lectureRepository;

    private final UserRepository userRepository;

    private final LectureQuestionRepository lectureQuestionRepository;
    private final LectureQuestionPhotoRepository lectureQuestionPhotoRepository;

    private final LectureAnswerRepository lectureAnswerRepository;
    private final LectureAnswerPhotoRepository lectureAnswerPhotoRepository;

    private final ResponseService responseService;
    private final S3UploaderService s3UploaderService;

    @Override
    public CommonResult readLecture(Long lecture_index, String tab){
        if(tab.equals("basic")){
            return responseService.getSingleResult(readBasicLecture(lecture_index));
        }else{
            return responseService.getSingleResult(readCurriculumLecture(lecture_index));
        }
    }

    public LectureReadDto readBasicLecture(Long lecture_index) {
            Lecture lecture = lectureRepository.findById(lecture_index)
                    .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));
            return LectureReadDto.of(lecture);
    }

    public LectureCurriculumReadDto readCurriculumLecture(Long lecture_index) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));

        Course findCourse = courseRepository.findById(lecture.getCurriculum().getCourse().getId())
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
        List<Curriculum> findCurriculum = curriculumRepository.findByCourse(findCourse); // 에러 처리

        LectureReadDto lectureDto = LectureReadDto.of(lecture);
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
        return LectureCurriculumReadDto.builder()
                .lectureReadDto(lectureDto)
                .curriculumReadDtos(curriculumReadDtos)
                .build();
    }

    /** 강의 질문 작성하기
     * @param lecture_index
     * @param images
     * @param question
     * @param userId
     */
    @Override
    @Transactional
    public CommonResult createQuestion(Long lecture_index, List<MultipartFile> images, String question, Long userId) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));
        User user = userRepository.findById(userId).get();

        LectureQuestion lectureQuestion = LectureQuestion.of(lecture, user, question);
        LectureQuestion saveQuestion = lectureQuestionRepository.save(lectureQuestion);

        // 사진 등록
        if(images.size() !=0){
            images.forEach(s->{
                try {
                    String basicFileName = UUID.randomUUID().toString();
                    String dirName = "question/" + String.valueOf(lecture.getId()) +"/"+String.valueOf(saveQuestion.getId());
                    String questionPhotoKey = s3UploaderService.upload(s, dirName, basicFileName);

                    LectureQuestionPhoto lectureQuestionPhoto = LectureQuestionPhoto.of(saveQuestion, questionPhotoKey);
                    lectureQuestionPhotoRepository.save(lectureQuestionPhoto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return responseService.getSuccessfulResult();
    }

    @Override
    public CommonResult readQuestion(Long question_index) {
        return null;
    }

    @Override
    public CommonResult readQuestions(String lecture_index) {
        return null;
    }

    @Override
    public CommonResult updateQuestion(Long question_index) {
        return null;
    }

    @Override
    public CommonResult deleteQuestion(Long question_index) {
        return null;
    }
}
