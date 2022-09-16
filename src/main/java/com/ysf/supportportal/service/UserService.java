package com.ysf.supportportal.service;

import com.ysf.supportportal.domain.User;
import com.ysf.supportportal.exception.domain.EmailExistException;
import com.ysf.supportportal.exception.domain.UserNotFoundException;
import com.ysf.supportportal.exception.domain.UsernameExistException;
import com.ysf.supportportal.service.impl.UserServiceImpl;

import java.util.List;

public interface UserService {
    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException;
    List<User> getUsers();
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
