package com.example.realtimemessaging.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @PreAuthorize(value = "hasAnyAuthority('ADD_ROLE')")
    @GetMapping
    public String checkApi() {
        return "Secured Messaging API is running...!";
    }

}
