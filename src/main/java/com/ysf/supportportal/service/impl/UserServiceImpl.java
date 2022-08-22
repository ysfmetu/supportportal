package com.ysf.supportportal.service.impl;

import com.ysf.supportportal.domain.User;
import com.ysf.supportportal.domain.UserPrincipal;
import com.ysf.supportportal.repository.UserRepository;
import com.ysf.supportportal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private Logger LOGGER= LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository userRepository;
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
}
