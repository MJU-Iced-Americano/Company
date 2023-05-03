package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.Curriculum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CurriculumReadDto {

    @Schema(description = "커리큘럼 인덱스", defaultValue = "1")
    private Long curriculumIndex;
    @Schema(description = "챕터", defaultValue = "1")
    private int chapter;
    @Schema(description = "챕터 제목", defaultValue = "자바란 무엇인가")
    private String curriculumTitle;
    @Schema(description = "강의 수", defaultValue = "3")
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
