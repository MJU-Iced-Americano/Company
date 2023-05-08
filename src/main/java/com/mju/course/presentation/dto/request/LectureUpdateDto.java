package com.mju.course.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureUpdateDto {
    @Schema(description = "강의 제목", defaultValue = "IDE 설치하기")
    private String lectureTitle;
    @Schema(description = "강의 상세 설명")
    private String lectureDescription;
}
