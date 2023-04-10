package com.mju.course.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostLectureDto {

    // 강의
    private int lecture_sequence;
    private String lecture_title;
    private int lecture_time;  // 동영상을 직접 분석 해서 시간을 알아내야 하나?
    private String lecture_url;
    private String lecture_description;

}
