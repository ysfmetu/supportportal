package com.ysf.supportportal.controller;

import com.ysf.supportportal.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/home")
    public String showUser(){

        return "application is working";
    }
}
