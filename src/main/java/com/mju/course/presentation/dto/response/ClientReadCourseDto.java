package com.mju.course.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ClientReadCourseDto {
    private Long courseIndex;
    private Long lecturerIndex;
}
