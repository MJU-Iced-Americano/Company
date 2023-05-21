package com.mju.course.application.lecture;

import com.mju.course.application.S3UploaderService;
import com.mju.course.domain.model.User;
import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.lecture.*;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.UserRepository;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.repository.lecture.*;
import com.mju.course.domain.service.LectureDomainService;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.request.LectureQuestionCreateDto;
import com.mju.course.presentation.dto.request.LectureReadAnswerDto;
import com.mju.course.presentation.dto.request.LectureReadQAndADto;
import com.mju.course.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mju.course.domain.model.other.Exception.ExceptionList.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureServiceImpl implements LectureService{

    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;

    private final UserRepository userRepository;

    private final LectureQuestionRepository lectureQuestionRepository;
    private final LectureQuestionPhotoRepository lectureQuestionPhotoRepository;

    private final LectureAnswerRepository lectureAnswerRepository;
    private final LectureAnswerPhotoRepository lectureAnswerPhotoRepository;

    private final LectureQuestionBookmarkRepository lectureQuestionBookmarkRepository;

    private final ResponseService responseService;
    private final S3UploaderService s3UploaderService;
    private final LectureDomainService lectureDomainService;

    /** 강의 보기
     * @param lecture_index
     * @param tab
     */
    @Override
    public CommonResult readLecture(Long lecture_index, String tab){
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));
        if(tab.equals("curriculum")){
            Course course = courseRepository.findById(lecture.getCurriculum().getCourse().getId())
                    .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
            return responseService.getSingleResult(LectureCurriculumReadDto.of(lecture, course));
        }else{
            return responseService.getSingleResult(LectureReadDto.of(lecture));
        }
    }

    /** 강의 질문 하나 보기 (+답변)
     * @param question_index
     */
    @Override
    @Transactional
    public CommonResult readQAndA(Long question_index) {
        LectureQuestion lectureQuestion = lectureQuestionRepository.findById(question_index)
                .orElseThrow(()-> new CourseException(NOT_EXISTENT_LECTURE_QUESTION));

        // 조회 수 증가
        lectureQuestion.updateHits();
        lectureQuestionRepository.save(lectureQuestion);

        // 질문 dto
        LectureReadQuestionDto lectureReadQuestionDto = LectureReadQuestionDto.of(lectureQuestion);

        // 답변들
        List<LectureReadAnswerDto> list = new ArrayList<>();
        if(lectureQuestion.getLectureAnswerList() != null && lectureQuestion.getLectureAnswerList().size() != 0){
            lectureQuestion.getLectureAnswerList()
                    .forEach(s ->{
                        list.add(LectureReadAnswerDto.of(s));
                    });
        }

        return responseService.getSingleResult(LectureReadQAndADto.builder()
                        .lectureReadQuestionDto(lectureReadQuestionDto)
                        .lectureReadAnswerDtos(list)
                .build());
    }

    /** 강의 질문 리스트 보기 (페이징 처리)
     * @param lecture_index
     * @param pageable
     */
    @Override
    public CommonResult readQuestions(Long lecture_index, Pageable pageable) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));
        Page<LectureReadQuestionDto> result = lectureRepository.readQuestions(lecture, pageable);
        return responseService.getSingleResult(result);
    }

    /** 강의 질문 작성하기
     * @param lecture_index
     * @param images
     * @param lectureQuestionCreateDto
     */
    @Override
    @Transactional
    public CommonResult createQuestion(Long lecture_index, List<MultipartFile> images, LectureQuestionCreateDto lectureQuestionCreateDto) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));
        User user = userRepository.findById(lectureQuestionCreateDto.getUserId()).get();

        LectureQuestion lectureQuestion = LectureQuestion.of(lecture, user, lectureQuestionCreateDto);
        LectureQuestion saveQuestion = lectureQuestionRepository.save(lectureQuestion);

        // 사진 등록
        if(images != null){
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

    /** 강의 질문 수정 하기
     * @param question_index
     */
    @Override
    public CommonResult updateQuestion(Long question_index) {
        return null;
    }

    /** 강의 질문 삭제 하기
     * @param question_index
     */
    @Override
    @Transactional
    public CommonResult deleteQuestion(Long question_index) {
        lectureDomainService.deleteQuestion(question_index);
        return responseService.getSuccessfulResult();
    }

}
