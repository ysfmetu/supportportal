package com.ysf.supportportal.service.impl;

import com.ysf.supportportal.domain.User;
import com.ysf.supportportal.domain.UserPrincipal;
import com.ysf.supportportal.enumeration.Role;
import com.ysf.supportportal.exception.domain.EmailExistException;
import com.ysf.supportportal.exception.domain.UserNotFoundException;
import com.ysf.supportportal.exception.domain.UsernameExistException;
import com.ysf.supportportal.repository.UserRepository;
import com.ysf.supportportal.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.Date;
import java.util.List;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    public static final String DEFAULT_USER_IMAGE_PATH = "/user/image/profile/temp";
    private Logger LOGGER= LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal;
        if (user == null) {
            LOGGER.error("username Not Found" + username);
            throw new UsernameNotFoundException("Girmiş olduğun kullanıcı geçerli değildir. kullanıcı adın :" + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            userPrincipal = new UserPrincipal(user);
            LOGGER.info("kullanıcı doğrulanmıştır.: " + username);
        }
        return userPrincipal;
    }

    @Override
    public User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException {

        validateNewUsernameAndEmail(StringUtils.EMPTY,username,email);
        User user=new User();
            user.setUserId(generateUserId());
            String password=generatePassword();
            String encodedPassword=encodePassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);
            user.setJoinDate(new Date());
            user.setPassword(encodedPassword);
            user.setActive(true);
            user.setNotLocked(true);
            user.setRole(Role.ROLE_USER.name());
            user.setAuthorities(Role.ROLE_USER.getAuthorities());
            user.setProfileImageUrl(getTemporaryProfileImageUrl());
            userRepository.save(user);
            LOGGER.info("yeni kullanıcı şifresi ="+password);

        return user;
    }

    private String getTemporaryProfileImageUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH).toUriString();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private User validateNewUsernameAndEmail(String currentUsername,String newUsername,String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {

        User currentUser= userRepository.findByUsername(currentUsername);
        User userByNewEmail= userRepository.findByEmail(newEmail);
        User userByNewUsername= userRepository.findByUsername(newUsername);

        if(StringUtils.isNotBlank(currentUsername)){
            if(currentUser == null){
                throw new UserNotFoundException("Girmiş olduğun kullanıcı geçerli değildir"+ currentUsername);
            }
            if(userByNewUsername != null && !(currentUser.getId().equals(userByNewUsername.getId()))) {
               throw new UsernameExistException("Girmiş olduğun KULLANICI ADI sistemimize tanımlıdır ");
            }
            if(userByNewEmail != null && !(currentUser.getId().equals(userByNewEmail.getId()))) {
                throw new EmailExistException("Girmiş olduğun E-POSTA adresi sistemimize tanımlıdır ");
            }
        return currentUser;
        }else {
            if(userByNewUsername != null ) {
                throw new UsernameExistException("Girmiş olduğun KULLANICI ADI sistemimize tanımlıdır ");
            }

            if(userByNewEmail != null) {
                throw new EmailExistException("Girmiş olduğun E-POSTA adresi sistemimize tanımlıdır ");
            }
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
