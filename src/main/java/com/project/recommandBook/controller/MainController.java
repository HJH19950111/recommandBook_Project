package com.project.recommandBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String main() {
        return "views/home";
    }

    @RequestMapping(value = "/views/home", method = RequestMethod.GET)
    public String home() {
        return "views/home";
    }

    @RequestMapping(value = "/views/about", method = RequestMethod.GET)
    public String about() {
        return "views/about";
    }

    @RequestMapping(value = "/views/contact", method = RequestMethod.GET)
    public String contact() {
        return "views/contact";
    }
}
