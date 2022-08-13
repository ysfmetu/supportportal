package com.ysf.supportportal.controller.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ysf.supportportal.constant.SecurityConstant;
import com.ysf.supportportal.domain.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtTokenProvider {
    @Value("jwt.secret")
    private String secret;

    public String generateJwtToken(UserPrincipal userPrincipal){
        String[] claims=getClaimsFromUser(userPrincipal);
        return JWT.create()
                .withIssuer(SecurityConstant.GET_ARRAYS_LLC)
                .withAudience(SecurityConstant.GET_ARRAYS_ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(SecurityConstant.AUTHORITIES,claims)
                .withExpiresAt(new Date(System.currentTimeMillis() +SecurityConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        return null;
    }
}
