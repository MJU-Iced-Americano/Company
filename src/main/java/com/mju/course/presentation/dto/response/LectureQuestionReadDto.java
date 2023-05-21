package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.lecture.LectureQuestion;
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
public class LectureQuestionReadDto {
    @Schema(description = "강의 질문 index")
    private Long lectureQuestionIndex;

    @Schema(description = "질문 제목")
    private String lectureQuestionTitle;

    @Schema(description = "질문 설명")
    private String lectureQuestion;

    @Schema(description = "질문자 유저명")
    private String username;

    @Schema(description = "생성일")
    private LocalDateTime createdAt;

    @Schema(description = "조회수")
    private long hits;

    @Schema(description = "bookmark 수")
    private long bookmarkSum;

    @Schema(description = "답변 수")
    private long answerSum;

    public static LectureQuestionReadDto of(LectureQuestion lectureQuestion) {
        return LectureQuestionReadDto.builder()
                .lectureQuestionIndex(lectureQuestion.getId())
                .lectureQuestionTitle(lectureQuestion.getLectureQuestionTitle())
                .lectureQuestion(lectureQuestion.getLectureQuestion())
                .username("질문자")
                .createdAt(lectureQuestion.getCreatedAt())
                .hits(lectureQuestion.getHits())
                .bookmarkSum(lectureQuestion.getLectureQuestionBookmarks().size())
                .answerSum(lectureQuestion.getLectureAnswerList().size())
                .build();
    }
}
