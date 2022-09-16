package com.ysf.supportportal.controller;

import com.ysf.supportportal.domain.User;
import com.ysf.supportportal.exception.ExceptionHandling;
import com.ysf.supportportal.exception.domain.EmailExistException;
import com.ysf.supportportal.exception.domain.UserNotFoundException;
import com.ysf.supportportal.exception.domain.UsernameExistException;
import com.ysf.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController extends ExceptionHandling {
    @Autowired
    private UserService userService;

    @GetMapping("home")
    public String showUser(){

        return "application is working";
    }
    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User newUser=userService.register(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
