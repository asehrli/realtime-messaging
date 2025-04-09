package com.example.realtimemessaging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @GetMapping
    public String checkApi() {
        return "Secured Messaging API is running...!";
    }

}
