package edu.miu.cs425.eCarRental.controller;

import edu.miu.cs425.eCarRental.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
}
