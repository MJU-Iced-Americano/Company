package com.mju.course.application.lecture;

import com.mju.course.application.S3UploaderService;
import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.lecture.*;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.repository.lecture.*;
import com.mju.course.domain.service.LectureDomainService;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.request.AnswerCreateDto;
import com.mju.course.presentation.dto.request.LectureQuestionCreateDto;
import com.mju.course.presentation.dto.response.LectureAnswerReadDto;
import com.mju.course.presentation.dto.response.LectureQAndAReadDto;
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
        LectureQuestionReadDto lectureQuestionReadDto = LectureQuestionReadDto.of(lectureQuestion);

        // 답변들
        List<LectureAnswerReadDto> list = new ArrayList<>();
        if(lectureQuestion.getLectureAnswerList() != null && lectureQuestion.getLectureAnswerList().size() != 0){
            lectureQuestion.getLectureAnswerList()
                    .forEach(s ->{
                        list.add(LectureAnswerReadDto.of(s));
                    });
        }

        return responseService.getSingleResult(LectureQAndAReadDto.builder()
                        .lectureQuestionReadDto(lectureQuestionReadDto)
                        .lectureAnswerReadDtos(list)
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
        Page<LectureQuestionReadDto> result = lectureRepository.readQuestions(lecture, pageable);
        return responseService.getSingleResult(result);
    }

    /** 강의 답변 하나보기
     * @param lecture_answer_index
     */
    @Override
    public CommonResult readAnswer(Long lecture_answer_index) {
        LectureAnswer lectureAnswer = lectureAnswerRepository.findById(lecture_answer_index)
                .orElseThrow(()-> new CourseException(NOT_EXISTENT_LECTURE_ANSWER));
        return responseService.getSingleResult(LectureAnswerReadDto.of(lectureAnswer));
    }

    /** 강의 질문 작성하기
     * @param lecture_index
     * @param images
     * @param lectureQuestionCreateDto
     */
    @Override
    @Transactional
    public CommonResult createQuestion(String userId, Long lecture_index, List<MultipartFile> images, LectureQuestionCreateDto lectureQuestionCreateDto) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));

        LectureQuestion lectureQuestion = LectureQuestion.of(lecture, userId, lectureQuestionCreateDto);
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
     * @param userId
     * @param question_index
     */
    @Override
    @Transactional
    public CommonResult deleteQuestion(String userId, Long question_index) {
        LectureQuestion lectureQuestion = lectureQuestionRepository.findById(question_index)
                        .orElseThrow(()-> new CourseException(NOT_EXISTENT_LECTURE_QUESTION));
        if(!lectureQuestion.getUserId().equals(userId)){
            throw new CourseException(NOT_ACCESS_USER);
        }

        lectureDomainService.deleteQuestion(question_index);
        return responseService.getSuccessfulResult();
    }

    /** 강의 질문 북마크, 북마크 취소
     * @param question_index
     * @param userId
     */
    @Override
    @Transactional
    public CommonResult lectureQuestionBookmark(Long question_index, String userId) {
        LectureQuestion lectureQuestion = lectureQuestionRepository.findById(question_index)
                .orElseThrow(()-> new CourseException(NOT_EXISTENT_LECTURE_QUESTION));

        Optional<LectureQuestionBookmark> bookmark = lectureQuestionBookmarkRepository.findByLectureQuestionAndUserId(lectureQuestion, userId);
        if(bookmark.isPresent()){
            lectureQuestionBookmarkRepository.delete(bookmark.get());
            return responseService.getSingleResult("북마크가 취소되었습니다.");
        }else{
            lectureQuestionBookmarkRepository.save(LectureQuestionBookmark.of(lectureQuestion, userId));
            return responseService.getSingleResult("북마크되었습니다.");
        }

    }

    /** 강의 질문 답변 달기
     * @param question_index
     * @param images
     * @param answerCreateDto
     */
    @Override
    @Transactional
    public CommonResult createAnswer(String userId, Long question_index, List<MultipartFile> images, AnswerCreateDto answerCreateDto) {
        LectureQuestion lectureQuestion = lectureQuestionRepository.findById(question_index)
                .orElseThrow(()-> new CourseException(NOT_EXISTENT_LECTURE_QUESTION));
        LectureAnswer lectureAnswer = LectureAnswer.of(lectureQuestion, userId, answerCreateDto.getAnswer());
        LectureAnswer saveAnswer = lectureAnswerRepository.save(lectureAnswer);

        // 사진 등록
        if(images != null){
            images.forEach(s->{
                try {
                    String basicFileName = UUID.randomUUID().toString();
                    String dirName = "answer/" + String.valueOf(lectureQuestion.getId()) +"/"+String.valueOf(saveAnswer.getId());
                    String questionPhotoKey = s3UploaderService.upload(s, dirName, basicFileName);
                    LectureAnswerPhoto lectureAnswerPhoto = LectureAnswerPhoto.of(saveAnswer, questionPhotoKey);
                    lectureAnswerPhotoRepository.save(lectureAnswerPhoto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return responseService.getSuccessfulResult();
    }

    /** 강의 질문 답변 삭제
     * @param lecture_answer_index
     */
    @Override
    @Transactional
    public CommonResult deleteAnswer(String userId, Long lecture_answer_index) {
        lectureDomainService.deleteAnswer(lecture_answer_index);
        return responseService.getSuccessfulResult();
    }

}
