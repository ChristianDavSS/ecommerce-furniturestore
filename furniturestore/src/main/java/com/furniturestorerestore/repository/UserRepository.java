package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    public Boolean existsByEmail(String email);
    public Optional<MyUser> findByEmail(String email);
}
