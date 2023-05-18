package com.mju.course.application.course;

import com.mju.course.domain.model.*;
import com.mju.course.domain.model.course.*;
import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.UserRepository;
import com.mju.course.domain.repository.course.*;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.CourseReadDto;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import com.mju.course.presentation.dto.response.CurriculumReadDto;
import com.mju.course.presentation.dto.response.LectureReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mju.course.domain.model.other.Exception.ExceptionList.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CartRepository cartRepository;
    private final CourseLikeRepository courseLikeRepository;
    private final UserRepository userRepository;

    private final ResponseService responseService;

    /**
     * 코스 목록 보기
     * @param category
     * @param order
     * @param skill
     * @param pageable
     * @param search
     * @return
     */
    @Override
    public CommonResult readCourseList(String category, String order, List<String> skill, Pageable pageable, String search){
        Page<CoursesReadDto> result = courseRepository.readCourseList(category, order, skill, pageable, search);
        result.forEach(readDto -> readDto.updateUrl(readDto.getCourseTitlePhotoUrl()));  // key를 url로 변경
        return responseService.getSingleResult(result);
    }

    /** 코스 하나 읽기
     * @param course_index
     * @param userId
     */
    @Override
    public CommonResult readCourse(Long course_index, Long userId) {
        Course findCourse = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        // 조회 수 증가
        findCourse.updateHit();
        courseRepository.save(findCourse);

        // 스킬
        List<String> skillList = new ArrayList<>();
        List<Skill> getSkillList = findCourse.getSkillList();
        getSkillList.forEach(s->skillList.add(s.getSkill()));

        // 커리 큘럼, 강의
        ArrayList<CurriculumReadDto> curriculumReadDtoList = new ArrayList<>();
        List<Curriculum> curriculumList = findCourse.getCurriculumList();
        curriculumList.forEach(curriculum -> {
            List<Lecture> lectureList = curriculum.getLectureList();
            List<LectureReadDto> lectureReadDtos = lectureList
                    .stream()
                    .map(LectureReadDto::of)
                    .collect(Collectors.toList());
            curriculumReadDtoList.add(CurriculumReadDto.of(curriculum, lectureReadDtos));
        });

        CourseReadDto courseReadDto = CourseReadDto.of(findCourse, skillList, curriculumReadDtoList);

        // 유저 정보
        if(userId != null && userId != 0){
            User user = userRepository.findById(userId).get();
            Optional<Cart> cart = cartRepository.findByCourseAndUser(findCourse, user);
            Optional<CourseLike> like = courseLikeRepository.findByCourseAndUser(findCourse, user);
            courseReadDto.addUserInfo(cart, like);
        }
        
        return responseService.getSingleResult(courseReadDto);
    }

    /** 장바구니 추가
     * @param course_index
     * @param userId
     */
    @Override
    public CommonResult addCart(Long userId, Long course_index) {
        Course course = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
        User user = userRepository.findById(userId).get();

        Optional<Cart> cart = cartRepository.findByCourseAndUser(course, user);
        if(cart.isPresent()){
            throw new CourseException(EXISTENT_CART);
        }
        Cart saveCart = Cart.of(course, user);
        cartRepository.save(saveCart);

        return responseService.getSuccessfulResult();
    }

    /** 장바구니 삭제
     * @param course_index
     * @param userId
     */
    @Override
    public CommonResult deleteCart(Long userId, Long course_index) {
        Course course = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
        User user = userRepository.findById(userId).get();

        Cart cart = cartRepository.findByCourseAndUser(course, user)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_CART));
        cartRepository.delete(cart);

        return responseService.getSuccessfulResult();
    }

    /** 코스 좋아요 추가, 삭제
     * @param course_index
     * @param userId
     */
    @Override
    public CommonResult courseLike(Long userId, Long course_index) {
        Course course = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
        User user = userRepository.findById(userId).get();

        Optional<CourseLike> like = courseLikeRepository.findByCourseAndUser(course, user);
        if(like.isPresent()){
            courseLikeRepository.delete(like.get());
        }else{
            courseLikeRepository.save(CourseLike.of(course, user));
        }
        return responseService.getSuccessfulResult();
    }

}
