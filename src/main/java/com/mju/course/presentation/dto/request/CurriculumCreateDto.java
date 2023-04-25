package com.mju.course.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurriculumCreateDto {
    private int chapter;
    private String curriculumTitle;
    private int lectureSum;
}
