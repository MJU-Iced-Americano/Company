package com.mju.course.domain.model.other.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionList {

    UNKNOWN(-9999, "알 수 없는 오류가 발생하였습니다."),
    NOT_EXISTENT_COURSE(5001, "존재 하지 않는 코스입니다."),
    DUPLICATION_COURSE_NAME(5002, "중복된 코스 이름입니다. ");

    private final int code;
    private final String message;
}
