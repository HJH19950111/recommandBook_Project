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

    @RequestMapping(value = "/views/searchBook", method = RequestMethod.GET)
    public String search() {
        return "views/searchBook";
    }

    @RequestMapping(value = "/views/searchBookResult", method = RequestMethod.GET)
    public String searchBook(@RequestParam(value = "keyWord") String keyWord, Model model) {
        if (keyWord.isEmpty())
            return "views/searchBook";

        model.addAttribute("books", recommandBookService.SearchRecommandBookList(keyWord));

        return "views/searchBookResult";
    }


}
