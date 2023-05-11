package com.mju.course.domain.repository.lecture;

import com.mju.course.domain.model.LectureNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureNoteRepository extends JpaRepository<LectureNote, Long> {

}
