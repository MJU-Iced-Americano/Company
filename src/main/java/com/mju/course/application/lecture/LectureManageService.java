package com.mju.course.application.lecture;

import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.LectureCreateDto;
import com.mju.course.presentation.dto.request.LectureUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LectureManageService {

    CommonResult createLecture(String userId, Long course_index, int chapter, int lecture_sequence, LectureCreateDto lectureCreateDto, MultipartFile multipartFile) throws IOException;

    CommonResult updateLecture(String userId, Long lecture_index, LectureUpdateDto lectureUpdateDto);

    CommonResult deleteLecture(String userId, Long lecture_index);
}
