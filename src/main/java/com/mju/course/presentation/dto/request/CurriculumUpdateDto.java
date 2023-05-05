package com.mju.course.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurriculumUpdateDto {
    @Schema(description = "챕터 제목", defaultValue = "자바란 무엇인가")
    private String curriculumTitle;
    @Schema(description = "강의 수", defaultValue = "3")
    private int lectureSum;
}
