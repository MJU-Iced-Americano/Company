package com.mju.course.domain.model.other.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionList {

    UNKNOWN(-9999, "알 수 없는 오류가 발생하였습니다."),

    // 코스
    NOT_EXISTENT_COURSE(-5001, "존재 하지 않는 코스입니다."),
    DUPLICATION_COURSE_NAME(-5002, "중복된 코스 이름입니다."),
    NO_MODIFIED_COURSE(-5003, "수정된 코스 요소가 없습니다."),

    // 커리 큘럼
    NOT_EXISTENT_CURRICULUM(-5011, "존재 하지 않는 커리 쿨럼입니다."),
    EXCEEDED_LECTURE_SEQUENCE(-5012, "해당 커리큘럼의 강의 수를 초과했습니다. 커리큘럼 정보 수정 후 다시 등록해주세요."),

    // 강의
    DUPLICATION_LECTURE(-5021, "이미 존재하는 강의 입니다."),
    NO_MODIFIED_LECTURE(-5022, "수정된 강의 요소가 없습니다."),
    NOT_EXISTENT_LECTURE(-5023, "존재 하지 않는 강의입니다."),

    // s3
    ERROR_S3_OBJECT_DELETE(-5031, "s3 객체 삭제를 실패했습니다.");

    private final int code;
    private final String message;
}
