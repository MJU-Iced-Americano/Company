package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.lecture.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class LectureCurriculumReadDto {
    private LectureReadDto lectureReadDto;
    private List<CurriculumReadDto> curriculumReadDtos;

    public static LectureCurriculumReadDto of(Lecture lecture, Course course){
        LectureReadDto lectureReadDto = LectureReadDto.of(lecture);
        List<CurriculumReadDto> curriculumReadDtos = new ArrayList<>();
        course.getCurriculumList()
                .forEach(curriculum->{
                    List<LectureReadDto> lectureReadDtos = new ArrayList<>();
                    curriculum.getLectureList()
                            .forEach(getLecture->{
                                lectureReadDtos.add(LectureReadDto.of(getLecture));
                            });
                    curriculumReadDtos.add(CurriculumReadDto.of(curriculum,lectureReadDtos));
                });
        return LectureCurriculumReadDto.builder()
                .lectureReadDto(lectureReadDto)
                .curriculumReadDtos(curriculumReadDtos)
                .build();
    }
}
