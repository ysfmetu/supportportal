package com.ysf.supportportal.controller.utility;

import com.ysf.supportportal.domain.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;

public class JwtTokenProvider {
    @Value("jwt.secret")
    private String secret;

    public String generateJwtToken(UserPrincipal userPrincipal){
        return "";
    }
}
