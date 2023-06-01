package com.mju.course.domain.model.course.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseState {
    registration("등록"),
    request("신청"),
    hold("보류"),
    delete("삭제");

    private final String str;
}
