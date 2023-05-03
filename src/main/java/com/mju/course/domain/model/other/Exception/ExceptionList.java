package com.mju.course.domain.model.other.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionList {

    UNKNOWN(-9999, "알 수 없는 오류가 발생하였습니다."),
    NOT_EXISTENT_COURSE(-5001, "존재 하지 않는 코스입니다."),
    NOT_EXISTENT_CURRICULUM(-5001, "존재 하지 않는 커리 쿨럼입니다."),
    DUPLICATION_COURSE_NAME(-5002, "중복된 코스 이름입니다."),
    DUPLICATION_LECTURE(-5002, "이미 존재하는 강의 입니다."),
    EXCEEDED_LECTURE_SEQUENCE(-5004, "해당 커리큘럼의 강의 수를 초과했습니다. 커리큘럼 정보 수정 후 다시 등록해주세요."),
    NO_MODIFIED_COURSE(-5005, "수정된 코스 요소가 없습니다."),

    NO_MODIFIED_LECTURE(-5005, "수정된 강의 요소가 없습니다."),
    NOT_EXISTENT_LECTURE(-5005, "존재 하지 않는 강의입니다.");

    private final int code;
    private final String message;
}
