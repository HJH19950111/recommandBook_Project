package com.project.recommandBook.dto;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class KakaoResultBookDto {
    private List<KakaoBookDto> documents;
    private int total_count;
    private int pageable_count;
    private boolean is_end;
}
