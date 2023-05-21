package com.mju.course.presentation.dto.request;

import com.mju.course.presentation.dto.response.LectureReadQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureReadQAndADto {
    private LectureReadQuestionDto lectureReadQuestionDto;
    private List<LectureReadAnswerDto> lectureReadAnswerDtos;
}
