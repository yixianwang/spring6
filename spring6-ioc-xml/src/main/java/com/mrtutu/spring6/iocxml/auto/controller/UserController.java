package com.mrtutu.spring6.iocxml.auto.controller;

import com.mrtutu.spring6.iocxml.auto.service.UserService;
import com.mrtutu.spring6.iocxml.auto.service.UserServiceImpl;

public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void addUser() {
        System.out.println("controller method is running.......");
        // call service method
        userService.addUserService();
        
//        UserService userService = new UserServiceImpl();
//        userService.addUserService();
    }
}
