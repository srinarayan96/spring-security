package com.example.spring_security.controller;

import com.example.spring_security.entity.User;
import com.example.spring_security.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;
    @PostMapping("/register")
    public String login(@RequestBody User user){
        return registerService.registerUser(user);
    }

    @GetMapping("/show-users")
    public List<User> showUsers(){
        System.out.println("22");
        return registerService.showUsers();
    }
}
