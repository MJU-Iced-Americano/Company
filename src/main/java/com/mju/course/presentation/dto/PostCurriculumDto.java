package com.mju.course.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class PostCurriculumDto {
    // 커리 큘럼
    private int chapter;
    private String curriculum_title;

    // 강의
    private ArrayList<PostLectureDto> postLectureDtos;
}
