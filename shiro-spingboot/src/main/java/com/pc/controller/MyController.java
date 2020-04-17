package com.pc.controller;

import com.pc.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MyController {
    @RequestMapping({"/", "/index"})
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/user/add")
    public String toAdd() {
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate() {
        return "/user/update";
    }

    @GetMapping("/login")
    public String toGetLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String toPostLogin(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        subject.login(token);
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
