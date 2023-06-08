package com.mju.course.application.lecture;

import com.mju.course.presentation.dto.request.AnswerCreateDto;
import com.mju.course.presentation.dto.request.LectureQuestionCreateDto;
import com.mju.course.presentation.dto.response.LectureAnswerReadDto;
import com.mju.course.presentation.dto.response.LectureQAndAReadDto;
import com.mju.course.presentation.dto.response.LectureQuestionReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LectureService {
    Object readLecture(Long lecture_index, String tab);

    LectureQAndAReadDto readQAndA(Long question_index);
    Page<LectureQuestionReadDto> readQuestions(Long lecture_index, Pageable pageable);
    LectureAnswerReadDto readAnswer(Long lecture_answer_index);

    void createQuestion(String userId, Long lecture_index, List<MultipartFile> images, LectureQuestionCreateDto lectureQuestionCreateDto);
    void updateQuestion(Long question_index);
    void deleteQuestion(String userId, Long question_index);

    String lectureQuestionBookmark(Long question_index, String userId);

    void createAnswer(String userId, Long question_index, List<MultipartFile> images, AnswerCreateDto answerCreateDto);
    void deleteAnswer(String userId, Long lecture_answer_index);

}
