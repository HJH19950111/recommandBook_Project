package com.project.recommandBook.repository;

import com.project.recommandBook.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findTop2BylikeCountOrderByCreatedAtDesc(int likeCount);
}
