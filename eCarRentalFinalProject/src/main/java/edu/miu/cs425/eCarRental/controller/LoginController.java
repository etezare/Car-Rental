package edu.miu.cs425.eCarRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/public/home/login","/ecarrental/public/home/login"})
    public String login() {
        return "public/home/login";
    }

}
