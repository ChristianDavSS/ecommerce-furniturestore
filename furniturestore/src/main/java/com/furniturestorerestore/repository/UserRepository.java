package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    public Boolean existsByEmail(String email);
    public MyUser findByEmail(String email);
}
