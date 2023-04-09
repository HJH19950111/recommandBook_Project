package com.project.recommandBook.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "t_like_post_info",
        uniqueConstraints = {@UniqueConstraint(columnNames = "likePost_id")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likePost_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfo_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public LikePost(Long id, UserInfo userInfo, Board board) {
        this.id = id;
        this.userInfo = userInfo;
        this.board = board;
    }
}
