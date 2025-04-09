package com.example.simplesecuredmessaging.controller;

import com.example.simplesecuredmessaging.config.AppTokenConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final AppTokenConfig appTokenConfig;

    @GetMapping("/check-backend")
    public String checkApi() {
        System.out.println(appTokenConfig.getAccessExpiration());
        return "Your fucking application is running...!";
    }

}
