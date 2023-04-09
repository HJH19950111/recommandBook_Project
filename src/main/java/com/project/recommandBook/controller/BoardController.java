package com.project.recommandBook.controller;

import com.project.recommandBook.dto.BoardDto;
import com.project.recommandBook.repository.UserInfoRepository;
import com.project.recommandBook.service.BoardService;
import com.project.recommandBook.service.LikePostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final LikePostService likePostService;
    private final UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/views/board/list", method = RequestMethod.GET)
    public String boardList(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);

        return "views/board/list";
    }

    @RequestMapping(value = "/views/board/post", method = RequestMethod.GET)
    public String post() {
        return "views/board/post";
    }

    @RequestMapping(value = "/views/board/post", method = RequestMethod.POST)
    public String writePost(@ModelAttribute BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/views/board/list";
    }

    @RequestMapping(value = "/views/board/post/{id}", method = RequestMethod.GET)
    public String viewPostDetailInfo(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);

        return "views/board/detailInfo";
    }

    @RequestMapping(value = "/views/board/post/edit/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);

        return "views/board/edit";
    }

    @RequestMapping(value = "/views/board/post/edit/{id}", method = RequestMethod.PUT)
    public String updatePost(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/views/board/list";
    }

    @RequestMapping(value = "/views/board/post/edit/{id}", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable("id") Long id) {
        boardService.deletePost(id);

        return "redirect:/views/board/list";
    }

    @RequestMapping(value = "/views/board/likePost/{id}", method = RequestMethod.GET)
    public String likeBoard(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);

        return "views/board/likePost";
    }

    @RequestMapping(value = "/views/board/likePost/{id}", method = RequestMethod.POST)
    public String likePost(@PathVariable("id") Long id, Principal principal) throws Exception {
        likePostService.checkDuplicates(id, principal.getName());

        return "redirect:/views/board/list";
    }

//    @RequestMapping(value = "/views/board/post/likePost/{id}", method = RequestMethod.DELETE)
//    public String unLikePost(@PathVariable("id") Long id, Principal principal) throws Exception {
//        likePostService.checkDuplicates(id, principal.getName());
//
//        return "redirect:/views/board/detailInfo";
//    }
}
