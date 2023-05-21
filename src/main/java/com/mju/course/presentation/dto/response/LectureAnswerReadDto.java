package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.lecture.LectureAnswer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    public static LectureAnswerReadDto of(LectureAnswer lectureAnswer) {
        return LectureAnswerReadDto.builder()
                .lectureAnswerIndex(lectureAnswer.getId())
                .username("답변자")
                .lectureAnswer(lectureAnswer.getLectureAnswer())
                .createdAt(lectureAnswer.getCreatedAt())
                .build();
    }
}
