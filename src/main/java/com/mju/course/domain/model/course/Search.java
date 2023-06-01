package com.mju.course.domain.model.course;

import com.mju.course.domain.model.BaseTimeEntity;
import com.mju.course.domain.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Search extends BaseTimeEntity {

    @Id
    @Column(name = "search_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String search;

    @Builder
    public Search(String userId, String search) {
        this.userId = userId;
        this.search = search;
    }

    public static Search of(String userId, String search){
        return Search.builder()
                .userId(userId)
                .search(search)
                .build();
    }
}
