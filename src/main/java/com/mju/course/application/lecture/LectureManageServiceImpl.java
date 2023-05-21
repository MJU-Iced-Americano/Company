package com.mju.course.application.lecture;

import com.mju.course.application.S3UploaderService;
import com.mju.course.domain.model.course.Course;
import com.mju.course.domain.model.course.Curriculum;
import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.repository.course.CurriculumRepository;
import com.mju.course.domain.repository.lecture.LectureRepository;
import com.mju.course.domain.service.LectureDomainService;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.request.LectureCreateDto;
import com.mju.course.presentation.dto.request.LectureUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.mju.course.domain.model.other.Exception.ExceptionList.*;
import static com.mju.course.domain.model.other.Exception.ExceptionList.EXCEEDED_LECTURE_SEQUENCE;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureManageServiceImpl implements LectureManageService {

    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureRepository lectureRepository;

    private final ResponseService responseService;
    private final S3UploaderService s3UploaderService;
    private final LectureDomainService lectureDomainService;

    @Override
    public CommonResult createLecture(Long course_index, int chapter, int lecture_sequence, LectureCreateDto lectureCreateDto, MultipartFile multipartFile) throws IOException {

        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        Curriculum findCurriculum = curriculumRepository.findByCourseAndChapter(findCourse, chapter)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_CURRICULUM));

        // 이미 등록된 강의 일 때
        Optional<Lecture> checkLecture = lectureRepository.findByCurriculumAndLectureSequence(findCurriculum, lecture_sequence);
        if(checkLecture.isPresent()) throw new CourseException(DUPLICATION_LECTURE);

        // 만약 커리 쿨럼에 등록된 강의 수를 초과 했을 경우
        if(findCurriculum.getLectureSum() < lecture_sequence) throw new CourseException(EXCEEDED_LECTURE_SEQUENCE);

        // 동영상 s3 에 등록
        // 1. input (inputKey)
        String basicFileName = course_index + "-" + chapter + "-" + lecture_sequence + "-" + UUID.randomUUID().toString();
        String dirName = "input/"+String.valueOf(course_index)+"/" + String.valueOf(chapter);  // 폴더 이름

        String inputKey = s3UploaderService.upload(multipartFile, dirName, basicFileName);

        // 2. output (lectureKey)
        String lectureKey = basicFileName+"/Default/HLS/"+basicFileName+".m3u8";

        // 동영상 시간 파악

        // 강의 DB 저장
        
        Lecture lecture = Lecture.of(findCurriculum, lecture_sequence, lectureCreateDto,lectureKey, inputKey);
        Lecture saveLecture = lectureRepository.save(lecture);

        // 코스 업데이트

        return responseService.getSuccessfulResult();
    }

    @Override
    public CommonResult updateLecture(Long lecture_index, LectureUpdateDto lectureUpdateDto) {
        Lecture lecture = lectureRepository.findById(lecture_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_LECTURE));

        boolean isModified = false; // 수정 유무
        ArrayList<String> arr = new ArrayList<>();

        if(lectureUpdateDto.getLectureTitle() != null && !lectureUpdateDto.getLectureTitle().equals(lecture.getLectureTitle())){
            lecture.updateLectureTitle(lectureUpdateDto.getLectureTitle());
            arr.add("강의 제목");
            isModified = true;
        }

        if(lectureUpdateDto.getLectureDescription() != null && !lectureUpdateDto.getLectureDescription().equals(lecture.getLectureDescription())){
            lecture.updateLectureDescription(lectureUpdateDto.getLectureDescription());
            arr.add("강의 설명");
            isModified = true;
        }

        if(isModified == false){
            throw new CourseException(NO_MODIFIED_LECTURE);
        }else{
            lectureRepository.save(lecture);
            return responseService.getSingleResult(arr +"가 수정되었습니다.");
        }
    }

    /**
     * 강의 삭제
     * @param lecture_index
     * */
    @Override
    @Transactional
    public CommonResult deleteLecture(Long lecture_index) {
        lectureDomainService.deleteLecture(lecture_index);
        return responseService.getSuccessfulResult();
    }

}
