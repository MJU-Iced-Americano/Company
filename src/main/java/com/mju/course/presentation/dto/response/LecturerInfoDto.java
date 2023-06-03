package com.mju.course.presentation.dto.response;

import lombok.Data;

@Data
public class LecturerInfoDto {
    private Long lecturerIndex;
    private String lecturerPhoto;
    private String lecturerName;
    private String lecturerCareer;
    private String lecturerAddress;
    private String userId;
}
