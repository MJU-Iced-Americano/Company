package com.mju.course.application.lecture;

import com.mju.course.domain.model.other.Result.CommonResult;

public interface LectureService {
    CommonResult createLectureNote(Long lecture_index, String note, String lectureNote);
    CommonResult updateLectureNote(Long lecture_index, String note, String lectureNote);
    CommonResult deleteLectureNote(Long lecture_index, String note);

    CommonResult readLecture(Long lecture_index, String tab);
}
