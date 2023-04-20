package com.project.recommandBook.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class EventController {

    @RequestMapping(value = "/views/event/attendanceCheck", method = RequestMethod.GET)
    public String attendanceCheck() {

        return "views/event/attendanceCheck";
    }

    @RequestMapping(value = "/views/event/attendanceCheck/submit", method = RequestMethod.POST)
    public String attendanceCheckSubmit() {
        int i = 0;
        i += 4;

        return "views/event/attendanceCheck";
    }
}
