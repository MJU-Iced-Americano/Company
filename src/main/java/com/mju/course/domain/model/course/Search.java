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

    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;

    private String search;

    @Builder
    public Search(User user, String search) {
        this.user = user;
        this.search = search;
    }

    public static Search of(User user, String search){
        return Search.builder()
                .user(user)
                .search(search)
                .build();
    }
}
