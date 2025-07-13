package com.furniturestorerestore.controller;

import com.furniturestorerestore.repository.entity.MyUser;
import com.furniturestorerestore.request.RegisterRequest;
import com.furniturestorerestore.response.RegisterResponse;
import com.furniturestorerestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest newUser) {
        return userService.registerUser(newUser);
    }

    @GetMapping
    public List<MyUser> getAllUsers() {
        return userService.getAllUsers();
    }
}
