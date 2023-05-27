package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.lecture.LectureAnswer;
import com.mju.course.domain.model.lecture.LectureAnswerPhoto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureAnswerReadDto {
    @Schema(description = "질문 답변 index")
    private Long lectureAnswerIndex;

    @Schema(description = "답변자 유저명")
    private String username;

    @Schema(description = "설명")
    private String lectureAnswer;

    @Schema(description = "답변 시간")
    private LocalDateTime createdAt;

    @Schema(description = "답변 사진")
    private List<String> lectureAnswerPhotoList;

    public static LectureAnswerReadDto of(LectureAnswer lectureAnswer) {
        List<String> lectureAnswerPhotoList = new ArrayList<>();
        lectureAnswer.getLectureAnswerPhotoList()
                .forEach(content->{
                    lectureAnswerPhotoList.add("https://d19wla4ff811v8.cloudfront.net/" + content.getLectureAnswerPhotoKey());
                });

        return LectureAnswerReadDto.builder()
                .lectureAnswerIndex(lectureAnswer.getId())
                .username("답변자")
                .lectureAnswer(lectureAnswer.getLectureAnswer())
                .createdAt(lectureAnswer.getCreatedAt())
                .lectureAnswerPhotoList(lectureAnswerPhotoList)
                .build();
    }
}
