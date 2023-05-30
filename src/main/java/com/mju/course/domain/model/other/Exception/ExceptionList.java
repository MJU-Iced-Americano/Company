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
    PLEASE_COURSE_DELETE_REASON(-5004, "코스 삭제 이유를 입력해주세요."),
    ALREADY_REQUEST_COURSE(-5005, "이미 등록 신청한 코스입니다."),
    DIFFERENT_LECTURE_SUM(-5006, "커리큘럼에 따른 강의가 등록되지 않았습니다. 다시 확인해 주세요."),
    ALREADY_APPLY_COURSE(-5007, "이미 해당 유저가 신청한 코스입니다."),

    // 커리 큘럼
    NOT_EXISTENT_CURRICULUM(-5011, "존재 하지 않는 커리 쿨럼입니다."),
    EXCEEDED_LECTURE_SEQUENCE(-5012, "해당 커리큘럼의 강의 수를 초과했습니다. 커리큘럼 정보 수정 후 다시 등록해주세요."),
    EXISTENT_CURRICULUM_CHAPTER(-5013, "이미 해당 코스 안에 존재하는 챕터입니다. 다시 입력해주세요."),
    EXISTENT_CURRICULUM_NAME(-5014, "이미 해당 코스 안에 존재하는 챕터 이름입니다. 다시 입력해주세요."),
    EXISTENT_CURRICULUM_COURSE(-5015, "해당 커리큘럼에 올라간 강의가 있습니다. 해당 커리큘럼에 관련된 강의 삭제 후 다시 시도해주세요."),
    NO_MODIFIED_CURRICULUM(-5016, "수정된 커리큘럼 요소가 없습니다."),
    EXCEEDED_LECTURE_CURRICULUM(-5017, "이미 올라간 강의 수보다 작은 수를 수정했습니다. 강의수를 변경 or 강의 삭제 or 다시 입력 바랍니다."),
    EXISTENT_CURRICULUM_LECTURE(-5018, "해당 커리큘럼에 이미 강의가 존재합니다. 강의 삭제 후 다시 시도해주세요."),

    // 강의
    DUPLICATION_LECTURE(-5021, "이미 존재하는 강의 입니다."),
    NO_MODIFIED_LECTURE(-5022, "수정된 강의 요소가 없습니다."),
    NOT_EXISTENT_LECTURE(-5023, "존재 하지 않는 강의입니다."),

    // s3
    ERROR_S3_OBJECT_DELETE(-5031, "s3 객체 삭제를 실패했습니다."),

    // 강의 노트
    NOT_EXISTENT_LECTURE_NOTE(-5041, "존재하지 않는 강의 노트입니다."),
    NO_MODIFIED_LECTURE_NOTE(-5042, "수정사항이 없습니다."),

    // 유저
    EMPTY_USER(-5051, "유저 정보를 입력해 주세요."),
    NOT_CORRECT_USER(-5052, "강사가 아닙니다. 강사로 로그인 다시 부탁드립니다."),
    NOT_ACCESS_USER(-5053, "접근할 수 없는 유저 입니다."),
    EMPTY_JWT(-5054, "토큰이 없습니다. 확인부탁드립니다."),

    // 코스 - 장바구니, 좋아요
    EXISTENT_CART(-5061, "이미 장바구니 안에 존재합니다."),
    NOT_EXISTENT_CART(-5062, "존재하지 않는 장바구니 정보입니다."),
    EXISTENT_LIKE(-5063, "이미 좋아요를 눌렀습니다."),
    NOT_EXISTENT_LIKE(-5064, "존재하지 않는 좋아요 정보입니다."),

    // 강의 질문
    NOT_EXISTENT_LECTURE_QUESTION(-5071, "존재하지 않는 강의 질문입니다."),

    // 강의 질문 답변
    NOT_EXISTENT_LECTURE_ANSWER(-5081, "존재하지 않는 강의 질문 답변입니다."),

    // 코스 수강신청
    NOT_EXISTENT_USER_COURSE(-5091, "존재하지 않는 수강신청입니다."),
    NOT_ACCESS_USER_COURSE(-5092, "접근 불가능한 유저입니다."),

    // 검색
    NOT_EXISTENT_USER_SEARCH(-6001, "존재하지 않는 검색입니다."),
    NOT_ACCESS_USER_SEARCH(-6002, "접근 불가능한 유저입니다.");

    private final int code;
    private final String message;
}
