package com.project.recommandBook.controller;

import com.project.recommandBook.service.RecommandBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class BookController {

    private final RecommandBookService recommandBookService;

    @RequestMapping(value = "/views/searchBook/searchBookNaver", method = RequestMethod.GET)
    public String searchNaver() {
        return "views/searchBook/searchBookNaver";
    }

    @RequestMapping(value = "/views/searchBook/searchBookKakao", method = RequestMethod.GET)
    public String searchKakao() {
        return "views/searchBook/searchBookKakao";
    }

    @RequestMapping(value = "/views/searchBook/searchBookResultNaver", method = RequestMethod.GET)
    public String searchBookNaver(@RequestParam(value = "keyWord") String keyWord, Model model) {
        if (keyWord.isEmpty())
            return "views/searchBook/searchBookNaver";

        model.addAttribute("books", recommandBookService.SearchRecommandNaverBookList(keyWord));

        return "views/searchBook/searchBookResultNaver";
    }

    @RequestMapping(value = "/views/searchBook/searchBookResultKakao", method = RequestMethod.GET)
    public String searchBookKakao(@RequestParam(value = "keyWord") String keyWord, Model model) {
        if (keyWord.isEmpty())
            return "views/searchBook/searchBookKakao";

        model.addAttribute("books", recommandBookService.SearchRecommandKakaoBookList(keyWord));

        return "views/searchBook/searchBookResultKakao";
    }
}
