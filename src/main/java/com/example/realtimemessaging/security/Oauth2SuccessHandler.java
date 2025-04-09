package com.example.realtimemessaging.security;

import com.example.realtimemessaging.entity.User;
import com.example.realtimemessaging.repository.RoleRepository;
import com.example.realtimemessaging.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        String email = ((String) oAuth2User.getAttributes().get("email")).toLowerCase();
        String name = (String) oAuth2User.getAttributes().get("name");
        String picture = (String) oAuth2User.getAttributes().get("picture");

        // register and login at once
        Optional<User> userOptional = userRepository.findByEmail(email);

        User user = userOptional.orElseGet(() -> User.builder()
                .email(email)
                .roles(Collections.singletonList(roleRepository.findByName("USER").orElseThrow()))
                .build());

        user.setName(name);
        user.setPicture(picture);
        userRepository.save(user);

        String accessToken = jwtProvider.generateAccessToken(email);
        String refreshToken = jwtProvider.generateRefreshToken(email);

        getRedirectStrategy()
                .sendRedirect(
                        request,
                        response,
                        "http://localhost:5713/token?accessToken=%s&refreshToken=%s".formatted(accessToken, refreshToken)
                );
    }
}