package edu.miu.cs425.eCarRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomePageController {
    @GetMapping(value = "/ecarrental/admin/home")
    public String adminHomePage() {
        return "admin/home";
    }

//    @GetMapping(value = "/ecarrental/page/underconstruction")
//    public String underConstruction() {
//        return "general/underconstruction";
//    }
}
