package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LectureReadDto {
    private Long lectureIndex;
    private int lectureSequence;
    private String lectureTitle;
    private int lectureTime;
    private String lectureUrl;
    private String lectureDescription;

    public static LectureReadDto of(Lecture lecture){
        return LectureReadDto.builder()
                .lectureIndex(lecture.getId())
                .lectureSequence(lecture.getLectureSequence())
                .lectureTitle(lecture.getLectureTitle())
                .lectureTime(lecture.getLectureTime())
                .lectureUrl(lecture.getLectureUrl())
                .lectureDescription(lecture.getLectureDescription())
                .build();
    }
}
