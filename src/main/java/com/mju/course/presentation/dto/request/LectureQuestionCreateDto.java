package com.mju.course.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureQuestionCreateDto {
    @Schema(description = "유저 이름")
    private long userId;

    @Schema(description = "질문 제목")
    @NotNull @NotBlank
    private String lectureQuestionTitle;

    @Schema(description = "질문")
    @NotNull @NotBlank
    private String lectureQuestion;
}
