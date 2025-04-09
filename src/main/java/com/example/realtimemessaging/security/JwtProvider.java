package com.example.realtimemessaging.security;

import com.example.realtimemessaging.config.AppTokenConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {
    private final AppTokenConfig appTokenConfig;

    public String generateAccessToken(String subject) {
        return JWTUtil.generateToken(subject, appTokenConfig.getAccessSecretKey(), appTokenConfig.getAccessExpiration());
    }

    public String generateRefreshToken(String subject) {
        return JWTUtil.generateToken(subject, appTokenConfig.getRefreshSecretKey(), appTokenConfig.getRefreshExpiration());
    }

    public String getSubjectFromAccessToken(String token) {
        return JWTUtil.getSubjectFromToken(token, appTokenConfig.getAccessSecretKey());
    }

    public String getSubjectFromRefreshToken(String token) {
        return JWTUtil.getSubjectFromToken(token, appTokenConfig.getRefreshSecretKey());
    }
}
