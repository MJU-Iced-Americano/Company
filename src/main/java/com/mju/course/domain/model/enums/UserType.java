package com.mju.course.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
    ROLE_USER("일반 회원"),
    ROLE_ADMIN("관리자"),
    ROLE_LECTURER("강사진");

    private final String str;
}
