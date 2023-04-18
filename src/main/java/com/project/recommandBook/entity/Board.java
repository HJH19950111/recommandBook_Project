package com.project.recommandBook.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_board_info",
        uniqueConstraints = {@UniqueConstraint(columnNames = "board_id")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "author", unique = false, nullable = false)
    private String author;

    @Column(name = "title", unique = false, nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(name = "recommand_BookName", unique = false, nullable = false, columnDefinition = "TEXT")
    private String recommandBookName;

    @Column(name = "content", unique = false, nullable = false)
    private String content;

    @Column(name = "created_At", unique = false, nullable = true, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_At", unique = false, nullable = true, updatable = false)
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Column(name = "like_Count", unique = false)
    @ColumnDefault("0")
    private int likeCount;

    @Column(name = "view_Count", nullable = false)
    @ColumnDefault("0")
    private int viewCount;


    @Builder
    public Board(Long id, String author, String title, String recommandBookName, String content, int likeCount, int viewCount) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.recommandBookName = recommandBookName;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
    }

    public void addLikeCount() {
        this.likeCount += 1;
    }

    public void subLikeCount() {
        this.likeCount -= 1;
    }

    public void addViewCount() {
        this.viewCount += 1;
    }
}

