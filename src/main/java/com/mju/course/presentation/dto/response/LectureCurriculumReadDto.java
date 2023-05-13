package com.mju.course.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@Builder
public class LectureCurriculumReadDto {
    private LectureReadDto lectureReadDto;
    private ArrayList<CurriculumReadDto> curriculumReadDtos;
}
