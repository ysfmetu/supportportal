package com.ysf.supportportal.controller;

import com.ysf.supportportal.controller.utility.JWTTokenProvider;
import com.ysf.supportportal.domain.User;
import com.ysf.supportportal.domain.UserPrincipal;
import com.ysf.supportportal.exception.ExceptionHandling;
import com.ysf.supportportal.exception.domain.EmailExistException;
import com.ysf.supportportal.exception.domain.UserNotFoundException;
import com.ysf.supportportal.exception.domain.UsernameExistException;
import com.ysf.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.ysf.supportportal.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping("user")
public class UserController extends ExceptionHandling {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("home")
    public String showUser(){

        return "application is working";
    }
    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(),user.getPassword());
        User loginUser=userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal=new UserPrincipal(loginUser);
        HttpHeaders jwtHeader=getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser,jwtHeader,HttpStatus.ACCEPTED);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers=new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER,jwtTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User newUser=userService.register(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
