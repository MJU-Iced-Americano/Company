package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.Lecture;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LectureReadDto {
    @Schema(description = "강의 인덱스", defaultValue = "1")
    private Long lectureIndex;
    @Schema(description = "강의 순서", defaultValue = "1")
    private int lectureSequence;
    @Schema(description = "강의 제목", defaultValue = "1")
    private String lectureTitle;
    @Schema(description = "강의 시간 (분)", defaultValue = "1")
    private int lectureTime;
    @Schema(description = "강의 url", defaultValue = "1")
    private String lectureUrl;
    @Schema(description = "강의 설명", defaultValue = "1")
    private String lectureDescription;

    public static LectureReadDto of(Lecture lecture){
        return LectureReadDto.builder()
                .lectureIndex(lecture.getId())
                .lectureSequence(lecture.getLectureSequence())
                .lectureTitle(lecture.getLectureTitle())
                .lectureTime(lecture.getLectureTime())
                .lectureUrl("https://d19wla4ff811v8.cloudfront.net/output/" + lecture.getLectureKey())
                .lectureDescription(lecture.getLectureDescription())
                .build();
    }
}
