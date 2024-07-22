package com.example.spring_security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class NormalController {
    @GetMapping("/normal")
    @Secured("ROLE_USER" )
    public String getNormal(){
        return "Normal User is here";
    }
    @GetMapping("/admin")
    @Secured("ROLE_ADMIN" )
    public String getAdmin(){
        return "Admin has logged in";
    }
}
