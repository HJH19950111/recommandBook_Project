package com.project.recommandBook.service;

import com.project.recommandBook.dto.BoardDto;
import com.project.recommandBook.entity.Board;
import com.project.recommandBook.mapper.BoardMapper;
import com.project.recommandBook.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = BoardMapper.convertToDto(board);
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public void updateViewCount(Long id) {
        Optional<Board> res = boardRepository.findById(id);
        res.get().addViewCount();
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();
        BoardDto boardDto = BoardMapper.convertToDto(board);

        return boardDto;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
