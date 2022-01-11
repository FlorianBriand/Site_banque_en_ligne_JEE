package com.example.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StarterController {

    @GetMapping(path = "/")
    public String loadIndexPage(Model model) {
        return "index";
    }

    @GetMapping(path = "/login")
    public String loadLoginPage(Model model) {
        return "connexion/login";
    }

    @GetMapping(path = "/signup")
    public String loadSignUpPage(Model model) {
        return "connexion/signup";
    }


    @GetMapping(path = "/actionwidget")
    public String loadactionwidgetPage(Model model) {
        return "action/actionwidget";
    }


}
