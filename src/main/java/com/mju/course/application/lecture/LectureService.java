package com.mju.course.application.lecture;

import com.mju.course.domain.model.other.Result.CommonResult;

public interface LectureService {
    CommonResult readLecture(Long lecture_index, String tab);
}
