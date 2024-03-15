package com.example.jwt.web;

import com.example.jwt.customuser.CurrentUser;
import com.example.jwt.customuser.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }

    @GetMapping("/user")
    public CustomUser user(@AuthenticationPrincipal CustomUser currentUser) {
        System.out.println("");
        return currentUser;
    }

}
