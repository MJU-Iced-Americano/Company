package com.mju.course.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureQAndAReadDto {
    private LectureQuestionReadDto lectureQuestionReadDto;
    private List<LectureAnswerReadDto> lectureAnswerReadDtos;
}
