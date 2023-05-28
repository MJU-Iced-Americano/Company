package com.mju.course.presentation.dto.response;

import com.mju.course.domain.model.course.Search;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SearchReadDto {
    private Long id;
    private String search;

    public static SearchReadDto of(Search search){
        return SearchReadDto.builder()
                .id(search.getId())
                .search(search.getSearch())
                .build();
    }

}
