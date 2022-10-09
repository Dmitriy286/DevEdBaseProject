package com.example.devedbaseproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizeController {

    @GetMapping("/hello")
    public String hello() {
        return "authorize/Hello";
    }
    @GetMapping("/user")
    public String user() {
        return "authorize/User";
    }
    @GetMapping("/admin")
    public String admin() {
        return "authorize/Admin";
    }
}
