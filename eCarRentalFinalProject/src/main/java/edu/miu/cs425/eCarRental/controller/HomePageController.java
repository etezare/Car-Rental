package edu.miu.cs425.eCarRental.controller;

import edu.miu.cs425.eCarRental.utility.PeriodRequested;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping(value = {"/","/ecarrental","/public/home","ecarrental/public/home"})
    public String home0() {
        return "public/home/index";
    }

//    @GetMapping(value = {"/public/home","/ecarrental/public/home"})
//    public String home1() {
//        return "public/home/index";
//    }

    @GetMapping(value = {"/secured/service","/ecarrental/secured/service"})
    public String service() {
        return "secured/services";
    }

    @GetMapping(value = {"/secured/home","/ecarrental/secured/home"})
    public String home2() {
        return "secured/index";
    }

    @GetMapping(value = {"/public/home/about","/ecarrental/public/home/about"})
    public String about() {
        return "public/home/about";
    }


    @GetMapping(value = {"/public/home/contact","/ecarrental/public/home/contact"})
    public String contact() {
        return "public/home/contact";
    }

    @GetMapping(value = {"/public/home/logout","/ecarrental/public/home/logout"})
    public String logOut() {
        return "public/home/login";
    }

//    @GetMapping(value = {"/public/virtualtour","/ecarrental/public/virtualtour"})
//    public String virtualtour() {
//        return "public/virtualtour";
//    }
//
//    @GetMapping(value = {"/secured/home","/ecarrental/secured/home"})
//    public String home2() {
//        return "secured/index";
//    }
}
