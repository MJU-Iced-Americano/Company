package com.mju.course.application.lecture;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.AnswerCreateDto;
import com.mju.course.presentation.dto.request.LectureQuestionCreateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LectureService {
    CommonResult readLecture(Long lecture_index, String tab);

    CommonResult readQAndA(Long question_index);
    CommonResult readQuestions(Long lecture_index, Pageable pageable);
    CommonResult readAnswer(Long lecture_answer_index);

    CommonResult createQuestion(String userId, Long lecture_index, List<MultipartFile> images, LectureQuestionCreateDto lectureQuestionCreateDto);
    CommonResult updateQuestion(Long question_index);
    CommonResult deleteQuestion(String userId, Long question_index);

    CommonResult lectureQuestionBookmark(Long question_index, String userId);

    CommonResult createAnswer(String userId, Long question_index, List<MultipartFile> images, AnswerCreateDto answerCreateDto);
    CommonResult deleteAnswer(String userId, Long lecture_answer_index);

}
