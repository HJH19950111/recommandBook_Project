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
public class ResultBookDto {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<BookDto> items;
}
