package com.project.recommandBook.enums;


import com.project.recommandBook.dto.BoardDto;
import com.project.recommandBook.entity.Board;

public class BoardMapper {

    public static BoardDto convertToDto(Board board) {
        BoardDto boardDto = new BoardDto();

        boardDto.setId(board.getId());
        boardDto.setAuthor(board.getAuthor());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setCreatedAt(board.getCreatedAt());
        boardDto.setModifiedAt(board.getModifiedAt());
        boardDto.setLikeCount(board.getLikeCount());

        return boardDto;
    }
}
