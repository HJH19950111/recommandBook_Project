package com.project.recommandBook.controller;

import com.project.recommandBook.entity.UserInfo;
import com.project.recommandBook.repository.UserInfoRepository;
import com.project.recommandBook.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class EventController {

    private final UserInfoService userInfoService;
    private final UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/views/event/attendanceCheck", method = RequestMethod.GET)
    public String attendanceCheck() {

        return "views/event/attendanceCheck";
    }

    @RequestMapping(value = "/views/event/attendanceCheck/submit", method = RequestMethod.POST)
    public String attendanceCheckSubmit() {

        return "redirect:/views/event/attendanceCheck";
    }

    @RequestMapping(value = "/views/event/pointRoulette", method = RequestMethod.GET)
    public String pointRoulette() {

        return "views/event/pointRoulette";
    }

    @RequestMapping(value = "/views/userInfo", method = RequestMethod.GET)
    public String viewUserInfo(@RequestParam(value ="pointData") int pointData, Model model, Principal principal) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserId(principal.getName());
        userInfoService.updatePoint(userInfo.get().getUserId(), pointData);
        model.addAttribute("post", userInfo.get());

        return "redirect:/";
    }
}
