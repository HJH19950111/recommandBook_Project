package com.project.recommandBook.controller;

import com.project.recommandBook.dto.UserInfoDto;
import com.project.recommandBook.entity.UserInfo;
import com.project.recommandBook.repository.UserInfoRepository;
import com.project.recommandBook.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class LoginController {

    private final UserInfoService userInfoService;
    private final UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/views/login", method = RequestMethod.GET)
    public String login(@ModelAttribute UserInfoDto userInfoDto) {

        return "views/login";
    }

    @RequestMapping(value = "/views/login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute @Validated UserInfoDto userInfoDto,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "views/login";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/views/signUp", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("userInfoDto", new UserInfoDto());
        return "views/signUp";
    }

    @RequestMapping(value = "/views/signUp", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute @Validated UserInfoDto userInfoDto,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "views/signUp";
        }

        userInfoService.join(userInfoDto);

        return "redirect:/login";
    }

    @RequestMapping(value = "/views/logout", method = RequestMethod.GET)
    public String logoutPost(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @RequestMapping(value = "/views/userInfo", method = RequestMethod.GET)
    public String userInfo(Principal principal, Model model) {
        Optional<UserInfo> res = userInfoRepository.findByUserId(principal.getName());
        model.addAttribute("post", res.get());
        return "views/userInfo";
    }
}
