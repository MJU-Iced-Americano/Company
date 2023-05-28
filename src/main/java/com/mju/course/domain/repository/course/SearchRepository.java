package com.mju.course.domain.repository.course;

import com.mju.course.domain.model.User;
import com.mju.course.domain.model.course.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {

    List<Search> findByUser(User user);
}
