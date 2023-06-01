package com.mju.course.domain.service;

import com.mju.course.application.S3UploaderService;
import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.domain.model.lecture.LectureAnswer;
import com.mju.course.domain.model.lecture.LectureQuestion;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.repository.lecture.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.mju.course.domain.model.other.Exception.ExceptionList.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureDomainService {

    private final LectureRepository lectureRepository;

    private final LectureQuestionRepository lectureQuestionRepository;
    private final LectureQuestionPhotoRepository lectureQuestionPhotoRepository;

    private final LectureAnswerRepository lectureAnswerRepository;
    private final LectureAnswerPhotoRepository lectureAnswerPhotoRepository;

    private final LectureQuestionBookmarkRepository lectureQuestionBookmarkRepository;

    private final S3UploaderService s3UploaderService;

    /**
     * 강의 삭제
     * @param lecture_index
     * */
    public void deleteLecture(Long lecture_index){
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));

        // 강의 질문 삭제
        lecture.getLectureQuestionList()
                        .forEach(s->{
                            deleteQuestion(s.getId());
                        });

        s3UploaderService.deleteS3File(lecture.getLectureKey()); // output 값 삭제
        s3UploaderService.deleteS3File(lecture.getLectureInputKey()); // input 값 삭제

        lectureRepository.delete(lecture);
    }

    /** 강의 질문 삭제
     * @param question_index
     */
    public void deleteQuestion(Long question_index){
        LectureQuestion lectureQuestion = lectureQuestionRepository.findById(question_index)
                .orElseThrow(()-> new CourseException(NOT_EXISTENT_LECTURE_QUESTION));

        // 강의 질문 관련 사진 삭제
        lectureQuestion.getLectureQuestionPhotoList()
                .forEach(lectureQuestionPhoto -> {
                    s3UploaderService.deleteS3File(lectureQuestionPhoto.getLectureQuestionPhotoKey());
                    lectureQuestionPhotoRepository.delete(lectureQuestionPhoto);
                });

        // 강의 질문 북마크 삭제
        lectureQuestion.getLectureQuestionBookmarks()
                .forEach(lectureQuestionBookmark -> {
                    lectureQuestionBookmarkRepository.delete(lectureQuestionBookmark);
                });

        if(lectureQuestion.getLectureAnswerList().size() != 0){
            lectureQuestion.getLectureAnswerList()
                    .forEach(lectureAnswer -> {
                        deleteAnswer(lectureAnswer.getId());
                    });
        }
    }

    /** 강의 질문 답변 삭제
     * @param lecture_answer_index
     */
    public void deleteAnswer(Long lecture_answer_index) {
        LectureAnswer lectureAnswer = lectureAnswerRepository.findById(lecture_answer_index)
                .orElseThrow(()-> new CourseException(NOT_EXISTENT_LECTURE_ANSWER));
        // 강의 답변 관련 사진 삭제
        lectureAnswer.getLectureAnswerPhotoList()
                .forEach(content->{
                    s3UploaderService.deleteS3File(content.getLectureAnswerPhotoKey());
                    lectureAnswerPhotoRepository.delete(content);
                });
        // 강의 답변 삭제
        lectureAnswerRepository.delete(lectureAnswer);
    }

}
