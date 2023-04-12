package com.project.recommandBook.dto;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class KakaoBookDto {
    private String title;
    private String contents;
    private String url;
    private String isbn;
    private String datetime;
    private String[] authors;
    private String publisher;
    private String[] translators;
    private String price;
    private String sale_price;
    private String thumbnail;
    private String status;
}
