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

    public static LectureQAndAReadDto of(LectureQuestionReadDto lectureQuestionReadDto, List<LectureAnswerReadDto> list){
        return LectureQAndAReadDto.builder()
                .lectureQuestionReadDto(lectureQuestionReadDto)
                .lectureAnswerReadDtos(list)
                .build();
    }
}
