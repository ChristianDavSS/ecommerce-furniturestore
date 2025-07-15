package com.furniturestorerestore.controller;

import com.furniturestorerestore.dto.register.UserDto;
import com.furniturestorerestore.dto.register.RegisterRequest;
import com.furniturestorerestore.dto.register.RegisterResponse;
import com.furniturestorerestore.dto.userProfile.ProfileRequest;
import com.furniturestorerestore.repository.entity.MyUser;
import com.furniturestorerestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserDto> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody ProfileRequest userRequest) {
        return userService.updateUserProfile(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
