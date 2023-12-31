package com.mrtutu.spring6.autowired.controller;

import com.mrtutu.spring6.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    // inject service
    // first approach: property injection
    @Autowired // try to find the object by type
    private UserService userService;

    public void add() {
        System.out.println("controller is running.......");
        userService.add();
    }
}
