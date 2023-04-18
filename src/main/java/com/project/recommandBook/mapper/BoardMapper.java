package com.project.recommandBook.mapper;


import com.project.recommandBook.dto.BoardDto;
import com.project.recommandBook.entity.Board;

import java.util.List;
import java.util.stream.Collectors;

public class BoardMapper {

    public static BoardDto convertToDto(Board board) {
        BoardDto boardDto = new BoardDto();

        boardDto.setId(board.getId());
        boardDto.setAuthor(board.getAuthor());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setCreatedAt(board.getCreatedAt());
        boardDto.setModifiedAt(board.getModifiedAt());
        boardDto.setRecommandBookName(board.getRecommandBookName());
        boardDto.setViewCount(board.getViewCount());
        boardDto.setLikeCount(board.getLikeCount());

        return boardDto;
    }

    public static List<BoardDto> convertToDtoList(List<Board> albums) {
        //albums에 있는 앨범을 하나씩 AlbumMapper로 변환 후 List형태로 모음
        return albums.stream().map(BoardMapper::convertToDto).collect(Collectors.toList());
    }
}
