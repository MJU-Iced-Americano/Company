package com.mju.course.application.course;

import com.mju.course.domain.model.course.*;
import com.mju.course.domain.model.lecture.Lecture;
import com.mju.course.domain.model.other.Exception.CourseException;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.*;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.controller.LecturerFeignClient;
import com.mju.course.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.mju.course.domain.model.other.Exception.ExceptionList.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CartRepository cartRepository;
    private final CourseLikeRepository courseLikeRepository;
    private final UserCourseRepository userCourseRepository;
    private final SearchRepository searchRepository;
    private final SkillRepository skillRepository;

    private final LecturerFeignClient lecturerFeignClient;

    /**
     * 기술 리스트 보기
     * */
    @Override
    public Set<String> readSkills() {
        List<Skill> skillList = skillRepository.findAll();
        Set<String> skills = new HashSet<>();
        skillList.forEach(skill -> {
            skills.add(skill.getSkill());
        });
        return skills;
    }

    /**
     * 코스 목록 보기
     * @param category
     * @param order
     * @param skill
     * @param pageable
     * @param search
     * @param userId
     * @return
     */
    @Override
    public Page<CoursesReadDto> readCourseList(String category, String order, List<String> skill, Pageable pageable, String search, String userId){
        if(userId != null && search != null){
            searchRepository.save(Search.of(userId, search));
        }
        Page<CoursesReadDto> result = courseRepository.readCourseList(category, order, skill, pageable, search);
        result.forEach(readDto -> readDto.updateUrl(readDto.getCourseTitlePhotoUrl()));  // key를 url로 변경

        return result;
    }

    /** 코스 하나 읽기
     * @param course_index
     * @param userId
     */
    @Override
    public CourseReadDto readCourse(Long course_index, String userId) {

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

        // 강사 정보
        LecturerInfoDto lecturerInfoDto = lecturerFeignClient.readLecturer(findCourse.getLecturerIndex());

        CourseReadDto courseReadDto = CourseReadDto.of(findCourse, skillList, curriculumReadDtoList,lecturerInfoDto);

        // 유저 정보
        if(userId != null){
            Optional<Cart> cart = cartRepository.findByCourseAndUserId(findCourse, userId);
            Optional<CourseLike> like = courseLikeRepository.findByCourseAndUserId(findCourse, userId);
            courseReadDto.addUserInfo(cart, like);
        }

        return courseReadDto;
    }

    /** 검색어 보기
     * @param userId
     */
    @Override
    public List<SearchReadDto> readSearch(String userId) {
        List<SearchReadDto> searchReadDtos = new ArrayList<>();
        if(userId != null){
            List<Search> searchList = searchRepository.findByUserId(userId);
            searchList.forEach(content->{
                searchReadDtos.add(SearchReadDto.of(content));
            });
        }
        return searchReadDtos;
    }

    /** 검색어 하나 삭제
     * @param search_index
     * @param userId
     */
    @Override
    @Transactional
    public String deleteSearch(Long search_index, String userId){
        // 존재하지 않는 유저

        // 존재하지 않는 검색어 입니다.
        Search search = searchRepository.findById(search_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_USER_SEARCH));

        // 유저가 일치하지 않습니다.
        if(!search.getUserId().equals(userId)){
            throw new CourseException(NOT_ACCESS_USER_SEARCH);
        }
        searchRepository.delete(search);

        return "검색어가 삭제되었습니다.";
    }

    /** 검색어 전체 삭제
     * @param userId
     */
    @Override
    @Transactional
    public String deleteSearchList(String userId) {
        List<Search> searchList = searchRepository.findByUserId(userId);
        searchList.forEach(search -> {
            searchRepository.delete(search);
        });
        return "검색어가 모두 삭제되었습니다.";
    }

    /** 장바구니 추가
     * @param course_index
     * @param userId
     */
    @Override
    @Transactional
    public String addCart(String userId, Long course_index) {
        Course course = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
        Optional<Cart> cart = cartRepository.findByCourseAndUserId(course, userId);
        if(cart.isPresent()){
            throw new CourseException(EXISTENT_CART);
        }
        Cart saveCart = Cart.of(course, userId);
        cartRepository.save(saveCart);

        return "장바구니에 추가되었습니다.";
    }

    /** 장바구니 삭제
     * @param course_index
     * @param userId
     */
    @Override
    @Transactional
    public String deleteCart(String userId, Long course_index) {
        Course course = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));
        Cart cart = cartRepository.findByCourseAndUserId(course, userId)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_CART));
        cartRepository.delete(cart);

        return "장바구니에서 삭제되었습니다.";
    }

    /** 코스 좋아요 추가, 삭제
     * @param course_index
     * @param userId
     */
    @Override
    @Transactional
    public void courseLike(String userId, Long course_index) {
        Course course = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        Optional<CourseLike> like = courseLikeRepository.findByCourseAndUserId(course, userId);
        if(like.isPresent()){
            courseLikeRepository.delete(like.get());
        }else{
            courseLikeRepository.save(CourseLike.of(course, userId));
        }
    }

    /** 코스 수강 신청
     * @param course_index
     * @param userId
     */
    @Override
    @Transactional
    public String applyCourse(String userId, Long course_index) {
        // 만약, 이미 수강 신청한 강좌라면 이미 수강신청한 강좌입니다란 정보 추출
        Course course = courseRepository.findById(course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_COURSE));

        Optional<UserCourse> checkUserCourse = userCourseRepository.findByUserIdAndCourse(userId, course);
        if(checkUserCourse.isPresent()){
            throw new CourseException(ALREADY_APPLY_COURSE);
        }

        UserCourse userCourse = UserCourse.of(userId, course);
        userCourseRepository.save(userCourse);

        return "코스 수강신청되었습니다.";
    }

    /** 코스 수강 취소
     * @param user_course_index
     * @param userId
     */
    @Override
    @Transactional
    public String cancelCourse(String userId, Long user_course_index) {
        UserCourse userCourse = userCourseRepository.findById(user_course_index)
                .orElseThrow(() -> new CourseException(NOT_EXISTENT_USER_COURSE));
        if(!userCourse.getUserId().equals(userId)){
            throw new CourseException(NOT_ACCESS_USER_COURSE);
        }
        userCourseRepository.delete(userCourse);
        return "코스 수강 신청이 취소되었습니다.";
    }

}
