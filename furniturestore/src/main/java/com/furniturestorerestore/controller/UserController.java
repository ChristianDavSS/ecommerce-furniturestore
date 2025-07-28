package com.furniturestorerestore.controller;

import com.furniturestorerestore.dto.UserDto;
import com.furniturestorerestore.dto.request.RegisterRequest;
import com.furniturestorerestore.dto.request.ProfileRequest;
import com.furniturestorerestore.repository.entity.MyUser;
import com.furniturestorerestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterRequest newUser) {
        return userService.registerUser(newUser);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/me")
    public UserDto getUserById(@AuthenticationPrincipal MyUser user) {
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return userService.getUserById(user.getId());
    }

    @PutMapping()
    public UserDto updateUserProfile(@AuthenticationPrincipal MyUser user, @RequestBody ProfileRequest userRequest) {
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return userService.updateUserProfile(user.getId(), userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
