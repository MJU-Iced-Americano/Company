package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.CoursePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePhotoRepository extends JpaRepository<CoursePhoto, Long> {

}
