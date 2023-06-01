package com.mju.course.domain.model.course;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.course.enums.CourseState;
import com.mju.course.presentation.dto.request.CourseCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Course extends BaseTimeEntity {

    @Id
    @Column(name = "course_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @Column(name = "category")
    private String category;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "price")
    private Long price;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "course_time")
    private int courseTime;

    @Column(name = "hits")
    private Long hits;

    @Enumerated(EnumType.STRING)
    private CourseState status;

    @Column(name = "comment")
    private String comment; // 보류 이유

    @Column(name = "course_title_photo_key")
    private String courseTitlePhotoKey; // 코스 대표 사진

    // 양방향 매핑
    @OneToMany(mappedBy = "course")
    private List<Skill> skillList = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Curriculum> curriculumList = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CourseLike> courseLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<UserCourse> userCourseList = new ArrayList<>();

    @Builder
    public Course(String userId, String category, String courseName, Long price, String courseDescription,
                  int difficulty, int courseTime, Long hits, CourseState status){
        this.userId = userId;
        this.category = category;
        this.courseName = courseName;
        this.price = price;
        this.courseDescription = courseDescription;
        this.difficulty = difficulty;
        this.courseTime = courseTime;
        this.hits = hits;
        this.status = status;
    }

    public static Course of(String lecturerId, CourseCreateDto courseCreateDto){
        return Course.builder()
                .userId(lecturerId)
                .category(courseCreateDto.getCategory())
                .courseName(courseCreateDto.getCourseName())
                .price(courseCreateDto.getPrice())
                .courseDescription(courseCreateDto.getCourseDescription())
                .difficulty(courseCreateDto.getDifficulty())
                .hits(0L)
                .status(CourseState.hold)
                .build();
    }

    public void updateTitlePhoto(String courseTitlePhotoKey){
        this.courseTitlePhotoKey = courseTitlePhotoKey;
    }
    public void updateState(CourseState status,String comment) {
        this.status = status;
        this.comment = comment;
    }

    public void updateCourseTime(int courseTime){
        this.courseTime += courseTime;
    }

    public void updateHit(){
        this.hits += 1;
    }

    public void updateCategory(String category){
        this.category = category;
    }

    public void updateCourseName(String courseName){
        this.courseName = courseName;
    }

    public void updatePrice(Long price){
        this.price = price;
    }

    public void updateCourseDescription(String courseDescription){
        this.courseDescription = courseDescription;
    }

    public void updateDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

}
