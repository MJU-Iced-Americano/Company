package com.mju.course.presentation.controller;

import com.mju.course.application.LectureManageService;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.presentation.dto.request.LectureCreateDto;
import com.mju.course.presentation.dto.request.LectureUpdateDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/lecture/manage")
@Tag(name = "3. 강의 관리", description = "강사진 용 강의 관리 관련 api 입니다. ")
public class LectureManageController {

    private final LectureManageService lectureManageService;

    // [Crate] (강사) 강의 등록
    @PostMapping("/new-lecture/{course_index}/{chapter}/{lecture_sequence}")
    public CommonResult createLecture(@PathVariable Long course_index,
                                      @PathVariable int chapter,
                                      @PathVariable int lecture_sequence,
                                      @RequestPart("postLectureDto") LectureCreateDto lectureCreateDto,
                                      @RequestPart("video") MultipartFile multipartFile) throws IOException {
        return lectureManageService.createLecture(course_index,chapter,lecture_sequence, lectureCreateDto,multipartFile);
    }

    // [Update] (강사) 강의 수정
    @PutMapping("/edit/{lecture_index}") //s3 관련 추가
    public CommonResult updateLecture(@PathVariable Long lecture_index,
                                      @RequestBody LectureUpdateDto lectureUpdateDto){
        return lectureManageService.updateLecture(lecture_index, lectureUpdateDto);
    }

    // [Delete] (강사) 강의 삭제
    @DeleteMapping("/delete/{lecture_index}") //s3 관련 추가
    public CommonResult deleteLecture(@PathVariable Long lecture_index){
        return lectureManageService.deleteLecture(lecture_index);
    }

    // [Crate] (강사) 대용량 파일 업로드


}
