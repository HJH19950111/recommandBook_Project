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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class EventController {

    private final UserInfoService userInfoService;
    private final UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/views/event/attendanceCheck", method = RequestMethod.GET)
    public String attendanceCheck(Principal principal, Model model) {
        Optional<UserInfo> res = userInfoRepository.findByUserId(principal.getName());
        model.addAttribute("post", res.get());

//        userInfoRepository.changeIsGetPoint(false);
        return "views/event/attendanceCheck";
    }

    @RequestMapping(value = "/views/event/attendanceCheck/submit", method = RequestMethod.POST)
    public String attendanceCheckSubmit(Principal principal) {
        Optional<UserInfo> res = userInfoRepository.findByUserId(principal.getName());
        int point = 50;
        userInfoService.updatePoint(res.get().getUserId(), point);

        return "redirect:/views/event/attendanceCheck";
    }


//    @RequestMapping(value = "/views/event/addRoulettePoint", method = RequestMethod.GET)
//    public String addRoulettePoint(@RequestParam(value ="pointData") int pointData, Model model, Principal principal) {
//        Optional<UserInfo> res = userInfoRepository.findByUserId(principal.getName());
//        LocalDate now = LocalDate.now();
//
//        userInfoService.updatePoint(res.get().getUserId(), pointData);
//        model.addAttribute("post", res.get());
//
//        return "redirect:/";
//    }
}
