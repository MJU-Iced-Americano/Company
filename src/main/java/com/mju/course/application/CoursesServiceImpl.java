package com.mju.course.application;

import com.mju.course.domain.model.Course;
import com.mju.course.domain.model.other.Result.CommonResult;
import com.mju.course.domain.repository.course.CourseRepository;
import com.mju.course.domain.service.ResponseService;
import com.mju.course.presentation.dto.response.CoursesReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoursesServiceImpl implements CoursesService {

    private final CourseRepository courseRepository;
    private final ResponseService responseService;

    @Override
    public CommonResult readBasicCourses(String order) {
        List<Course> courses = courseRepository.findAll();
        List<Course> sortCourses = sortCourses(courses, order);
        return result(sortCourses);
    }

    @Override
    public CommonResult readBasicSkillCourses(String order, List<String> skill) {
        List<Course> courses = courseRepository.findAll();
        List<Course> newCourses = findSkillCourses(courses, skill);
        List<Course> sortCourses = sortCourses(newCourses, order);
        return result(sortCourses);
    }

    @Override
    public CommonResult readCategoryCourses(String category, String order) {
        List<Course> courses = courseRepository.findByCategory(category);
        List<Course> sortCourses = sortCourses(courses, order);
        return result(sortCourses);
    }

    @Override
    public CommonResult readCategorySkillCourses(String category, String order, List<String> skill) {
        List<Course> courses = courseRepository.findByCategory(category);
        List<Course> newCourses = findSkillCourses(courses, skill);
        List<Course> sortCourses = sortCourses(newCourses, order);
        return result(sortCourses);
    }

    private CommonResult result(List<Course> courses){
        List<CoursesReadDto> coursesReadDtos = new ArrayList<>();
        for(int i=0; i<courses.size(); i++){
            CoursesReadDto coursesReadDto = CoursesReadDto.of(courses.get(i));
            coursesReadDtos.add(coursesReadDto);
        }
        return responseService.getListResult(coursesReadDtos);
    }

    private List<Course> sortCourses(List<Course> courses, String order){
        if(order.equals("new")){
            courses = courses.stream()
                    .sorted((a, b) -> a.getCreatedAt().compareTo(b.getCreatedAt()))
                    .collect(Collectors.toList());
        }else if(order.equals("hits")){
            courses = courses.stream()
                    .sorted((a, b) -> (int) (b.getHits() - a.getHits()))
                    .collect(Collectors.toList());
        }else if(order.equals("difficulty")){
            courses = courses.stream()
                    .sorted((a, b) -> b.getDifficulty() - a.getDifficulty())
                    .collect(Collectors.toList());
        }
        return courses;
    }

    private List<Course> findSkillCourses(List<Course> courses, List<String> skill){
        List<Course> newCourses = new ArrayList<>();

        for(int i=0; i<courses.size(); i++){
            String str = courses.get(i).getSkill();
            StringTokenizer st = new StringTokenizer(str,",");
            while(st.hasMoreTokens()){
                String a = st.nextToken().trim();
                boolean check = false;
                for(String value : skill){
                    if(a.equals(value)){
                        newCourses.add(courses.get(i));
                        check = true;
                        break;
                    }
                }
                if(check == true) break;
            }
        }
        return newCourses;
    }
}
