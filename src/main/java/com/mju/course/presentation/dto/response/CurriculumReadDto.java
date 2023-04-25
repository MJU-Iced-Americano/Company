package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.Curriculum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CurriculumReadDto {

    private Long curriculumIndex;
    private int chapter;
    private String curriculumTitle;
    private int lectureSum;

    private List<LectureReadDto> lectureReadDtos;

    public static CurriculumReadDto of(Curriculum curriculum, List<LectureReadDto> lectureReadDtos){
        return CurriculumReadDto.builder()
                .curriculumIndex(curriculum.getId())
                .chapter(curriculum.getChapter())
                .curriculumTitle(curriculum.getCurriculumTitle())
                .lectureSum(curriculum.getLectureSum())
                .lectureReadDtos(lectureReadDtos)
                .build();
    }
}
