package com.example.realtimemessaging.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@UtilityClass
public class JWTUtil {
    public static String generateToken(String subject, String secretKey, long expiration) {
        return JWT
                .create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000 + 1800000)) // + 5 hours
                .sign(Algorithm.HMAC256(secretKey));
    }

    public static String getSubjectFromToken(String token, String secretKey) {
        try {
            return JWT
                    .require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            log.error("TOKEN is expired :: {}", exception.getMessage());
            throw exception;
        }
    }
}
