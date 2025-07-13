package com.furniturestorerestore.service;

import com.furniturestorerestore.repository.UserRepository;
import com.furniturestorerestore.repository.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public MyUser addUser(MyUser user) {
        return userRepository.save(user);
    }

    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }
}
