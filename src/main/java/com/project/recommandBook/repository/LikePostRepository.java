package com.project.recommandBook.repository;

import com.project.recommandBook.entity.Board;
import com.project.recommandBook.entity.LikePost;
import com.project.recommandBook.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    Optional<LikePost> findByUserInfoAndBoard(UserInfo userInfo, Board board);
}
