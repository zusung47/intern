package com.tigensoft.intern.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

    //검색 키워드
    private String searchText;
    //검색 조건
    private String searchCondition;

}
