package com.mju.course.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class RequestCourseLikeDto {

    @Schema(description = "유저가 코스 좋아요 한 인덱스", defaultValue = "1")
    private Long userCourseLikeIndex;
    @Schema(description = "카테 고리")
    private String category;
    @Schema(description = "코스 이름", defaultValue = "자바 기초")
    private String courseName;
    @Schema(description = "가격", defaultValue = "100000")
    private Long price;
    @Schema(description = "난이도", defaultValue = "2")
    private int difficulty;
    @Schema(description = "코스 기본 사진 URL")
    private String courseTitlePhotoUrl;
    @Schema(description = "코스 좋아요 생성 시간")
    private LocalDateTime createdAt;

    public void updateUrl(String courseTitlePhotoKey){
        this.courseTitlePhotoUrl = "https://d19wla4ff811v8.cloudfront.net/" + courseTitlePhotoKey;
    }

}
