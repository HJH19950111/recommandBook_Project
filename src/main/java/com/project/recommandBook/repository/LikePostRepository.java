package com.project.recommandBook.repository;

import com.project.recommandBook.entity.Board;
import com.project.recommandBook.entity.LikePost;
import com.project.recommandBook.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    Optional<LikePost> findByUserInfoAndBoard(UserInfo userInfo, Board board);
}
