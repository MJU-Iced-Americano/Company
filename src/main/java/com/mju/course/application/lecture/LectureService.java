package com.mju.course.application.lecture;

import com.mju.course.domain.model.other.Result.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LectureService {
    CommonResult readLecture(Long lecture_index, String tab);

    CommonResult createQuestion(Long lecture_index, List<MultipartFile> images, String question, Long userId);
    CommonResult readQuestion(Long question_index);
    CommonResult readQuestions(String lecture_index);
    CommonResult updateQuestion(Long question_index);
    CommonResult deleteQuestion(Long question_index);


}
