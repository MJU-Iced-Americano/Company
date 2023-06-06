package com.mju.course.application.lecture;

import com.mju.course.presentation.dto.request.LectureCreateDto;
import com.mju.course.presentation.dto.request.LectureUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LectureManageService {

    void createLecture(Long lecturerId, Long course_index, int chapter, int lecture_sequence, LectureCreateDto lectureCreateDto, MultipartFile multipartFile) throws IOException;

    String updateLecture(Long lecturerId, Long lecture_index, LectureUpdateDto lectureUpdateDto);

    String deleteLecture(Long lecturerId, Long lecture_index);
}
