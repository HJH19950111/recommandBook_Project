package com.project.recommandBook.controller;

import com.project.recommandBook.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserInfoRepository userInfoRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String main() {
        return "views/home";
    }

    @RequestMapping(value = "/views/home", method = RequestMethod.GET)
    public String home() {
        return "views/home";
    }

    @RequestMapping(value = "/views/contact", method = RequestMethod.GET)
    public String contact() {
        return "views/contact";
    }
}
