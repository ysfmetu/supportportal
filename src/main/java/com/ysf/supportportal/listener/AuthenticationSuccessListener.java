package com.ysf.supportportal.listener;

import com.ysf.supportportal.domain.User;
import com.ysf.supportportal.domain.UserPrincipal;
import com.ysf.supportportal.service.impl.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener {

    private LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }
    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event){
        Object principal=event.getAuthentication().getPrincipal();
        if(principal instanceof UserPrincipal){
            UserPrincipal user=(UserPrincipal) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }

    }
}
