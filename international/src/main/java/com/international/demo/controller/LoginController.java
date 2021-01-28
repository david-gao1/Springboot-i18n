package com.international.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    //登录页面
    @GetMapping("/login")
    public String login(Model model) {
        return "login/login.html";
    }


    //后端获取的值
//    @GetMapping("/text")
//    public String text() {
//        //return messageSource.getMessage();
//    }


}