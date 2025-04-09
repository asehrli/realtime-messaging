package com.example.simplesecuredmessaging.security;

import com.example.simplesecuredmessaging.entity.User;
import com.example.simplesecuredmessaging.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;

    public Oauth2SuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        String email = ((String) oAuth2User.getAttributes().get("email")).toLowerCase();
        String name = (String) oAuth2User.getAttributes().get("name");
        String picture = (String) oAuth2User.getAttributes().get("picture");

        // register and login at once
        Optional<User> userOptional = userRepository.findByEmail(email);

        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new User();
            user.setEmail(email);
        }

        user.setName(name);
        user.setPicture(picture);
        userRepository.save(user);

        getRedirectStrategy()
                .sendRedirect(
                        request,
                        response,
                        "http://localhost:5713/access-token?token=this_is_a_token_for_email_" + email
                );
    }
}