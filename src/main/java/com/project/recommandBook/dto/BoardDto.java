package com.project.recommandBook.dto;

import com.project.recommandBook.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BoardDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likeCount;
    private int viewCount;

    @Builder
    public Board toEntity() {
        return Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .likeCount(likeCount)
                .viewCount(viewCount)
                .build();
    }
}
