package edu.miu.cs425.eCarRental.controller;

import java.util.List;

import javax.validation.Valid;

import edu.miu.cs425.eCarRental.model.User;
import edu.miu.cs425.eCarRental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/ecarrental/user/users")
    public ModelAndView manageCategories() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("user/users/users");
        return modelAndView;
    }

    @PostMapping(value = "/ecarrental/user/users/add/save")
    public String addNewUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "user/users/newcategoryform";
        }
        user = userService.save(user);
        return "redirect:/ecarrental/user/users";
    }

    @GetMapping(value = "/ecarrental/user/users/edit/{userId}")
    public String editUserForm(@PathVariable("userId") Long userId, Model model) {
        User user = userService.findById(userId);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/users/edituserform";
        }
        return "user/users/users";
    }

    @GetMapping(value="/ecarrental/user/users/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long id, Model model){
        userService.delete(id);
        return "redirect:/ecarrental/user/users";
    }
}