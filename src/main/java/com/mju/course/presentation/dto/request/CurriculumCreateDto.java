package com.mju.course.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurriculumCreateDto {
    @Schema(description = "챕터", defaultValue = "1")
    @NotNull
    private int chapter;

    @Schema(description = "챕터 제목", defaultValue = "자바란 무엇인가")
    @NotNull @NotBlank
    private String curriculumTitle;

    @Schema(description = "강의 수", defaultValue = "3")
    @NotNull
    private int lectureSum;
}
