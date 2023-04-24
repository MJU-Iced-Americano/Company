package com.mju.course.domain.repository;

import com.mju.course.domain.model.Curriculum;
import com.mju.course.domain.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findByCurriculum(Curriculum curriculum);

    Optional<Lecture> findByLectureSequence(int lecture_sequence);
}
