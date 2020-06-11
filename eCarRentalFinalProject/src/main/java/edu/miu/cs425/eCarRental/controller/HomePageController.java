package edu.miu.cs425.eCarRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value = {"/","/home","/ecarrental/home"})
    public String homePage() {
        return "public/home/index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "public/login/login";
    }

}
