package com.furniturestorerestore.controller;

import com.furniturestorerestore.repository.entity.MyUser;
import com.furniturestorerestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public MyUser createUser(@RequestBody MyUser user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<MyUser> getAllUsers() {
        return userService.getAllUsers();
    }
}
