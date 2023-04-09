package com.project.recommandBook.service;

import com.project.recommandBook.dto.LikePostDto;
import com.project.recommandBook.entity.Board;
import com.project.recommandBook.entity.LikePost;
import com.project.recommandBook.entity.UserInfo;
import com.project.recommandBook.repository.BoardRepository;
import com.project.recommandBook.repository.LikePostRepository;
import com.project.recommandBook.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikePostService {

    private final UserInfoRepository userInfoRepository;
    private final BoardRepository boardRepository;
    @Autowired
    private final LikePostRepository likePostRepository;

    @Transactional
    public void checkDuplicates(Long boardId, String userName) throws Exception {
        LikePostDto likePostDto = getLikePostDto(boardId, userName);

        UserInfo userInfo = userInfoRepository.findById(likePostDto.getUserInfoId());
        Optional<Board> boardRes = boardRepository.findById(likePostDto.getBoardId());
        Board board = boardRes.get();
        Optional<LikePost> likePostRes = likePostRepository.findByUserInfoAndBoard(userInfo, board);

        if (likePostRes.isPresent()) {
            board.subLikeCount();;
            delete(likePostDto);
        }
        else {
            board.addLikeCount();
            insert(likePostDto);
        }
    }

    @Transactional
    private LikePostDto getLikePostDto(Long boardId, String userName) {
        Optional<UserInfo> userInfoRes = userInfoRepository.findByUserId(userName);
        LikePostDto likePostDto = new LikePostDto();
        likePostDto.setBoardId(boardId);
        likePostDto.setUserInfoId(userInfoRes.get().getId());

        return likePostDto;
    }

    @Transactional
    private void insert(LikePostDto likePostDto) throws Exception {
        UserInfo userInfo = userInfoRepository.findById(likePostDto.getUserInfoId());
        Optional<Board> boardRes = boardRepository.findById(likePostDto.getBoardId());

        LikePost likePost = LikePost.builder()
                .board(boardRes.get())
                .userInfo(userInfo)
                .build();

        likePostRepository.save(likePost);
    }

    @Transactional
    private void delete(LikePostDto likePostDto) {
        UserInfo userInfo = userInfoRepository.findById(likePostDto.getUserInfoId());
        Optional<Board> boardRes = boardRepository.findById(likePostDto.getBoardId());
        Optional<LikePost> likePostRes = likePostRepository.findByUserInfoAndBoard(userInfo, boardRes.get());

        likePostRepository.delete(likePostRes.get());
    }
}
