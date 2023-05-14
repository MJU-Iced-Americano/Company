package com.mju.course.application.lecture;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.Curriculum;
import com.mju.course.domain.model.Lecture;
import com.mju.course.domain.model.LectureNote;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.repository.course.CurriculumRepository;
import com.mju.course.domain.repository.lecture.LectureNoteRepository;
import com.mju.course.domain.repository.lecture.LectureRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.CurriculumReadDto;
import com.mju.course.presentation.dto.response.LectureCurriculumReadDto;
import com.mju.course.presentation.dto.response.LectureReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_COURSE;
import static com.mju.course.domain.model.other.Exception.ExceptionList.NOT_EXISTENT_LECTURE;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureServiceImpl implements LectureService{

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureRepository lectureRepository;
    private final LectureNoteRepository lectureNoteRepository;

    private final ResponseService responseService;

    @Override
    public CommonResult readLecture(Long lecture_index, String tab){
        if(tab.equals("basic")){
            return responseService.getSingleResult(readBasicLecture(lecture_index));
        }else{
            return responseService.getSingleResult(readCurriculumLecture(lecture_index));
        }
    }

    public LectureReadDto readBasicLecture(Long lecture_index) {
            Lecture lecture = lectureRepository.findById(lecture_index)
                    .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));
            return LectureReadDto.of(lecture);
    }

    public LectureCurriculumReadDto readCurriculumLecture(Long lecture_index) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));

        Course findCourse = courseRepository.findById(lecture.getCurriculum().getCourse().getId())
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
        List<Curriculum> findCurriculum = curriculumRepository.findByCourse(findCourse); // 에러 처리

        LectureReadDto lectureDto = LectureReadDto.of(lecture);
        ArrayList<CurriculumReadDto> curriculumReadDtos = new ArrayList<>();
        for(int i=0; i<findCurriculum.size(); i++){
            List<LectureReadDto> lectureReadDtos = new ArrayList<>();
            List<Lecture> lectures = lectureRepository.findByCurriculum(findCurriculum.get(i));
            for(int j=0; j< lectures.size(); j++){
                LectureReadDto lectureReadDto = LectureReadDto.of(lectures.get(i));
                lectureReadDtos.add(lectureReadDto);
            }
            CurriculumReadDto curriculumReadDto = CurriculumReadDto.of(findCurriculum.get(i), lectureReadDtos);
            curriculumReadDtos.add(curriculumReadDto);
        }
        return LectureCurriculumReadDto.builder()
                .lectureReadDto(lectureDto)
                .curriculumReadDtos(curriculumReadDtos)
                .build();
    }

    @Override
    public CommonResult createLectureNote(Long lecture_index, String note, String lectureNote) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));

        // 강의 노트 와 유저 비교해서 예외 확인하기

        // 강의와 유저를 findBy 해서 이미 강의 노트가 존재하면 수정

        // lectureNote 가 null 일 때

        LectureNote saveLectureNote = LectureNote.of(lecture, lectureNote);
        lectureNoteRepository.save(saveLectureNote);

        return responseService.getSuccessfulResult();
    }

    @Override
    public CommonResult updateLectureNote(Long lecture_index, String note, String lectureNote) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));

        // 수정 사항이 없을 경우


        return null;
    }

    @Override
    public CommonResult deleteLectureNote(Long lecture_index, String note) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));

        return null;
    }
}
