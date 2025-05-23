package com.paradise.eventnotificator.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtTokenManager {


    private final SecretKey key;



    public JwtTokenManager(
            @Value("${jwt.secret}") String key
    ) {

        this.key = Keys.hmacShaKeyFor(key.getBytes());

    }



    public String getLoginFromToken(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Long getUserIdFromToken(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id",Long.class);
    }
}
