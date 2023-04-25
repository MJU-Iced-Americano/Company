package com.mju.course.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureCreateDto {
    private String lectureTitle;
    private String lectureDescription;
}
