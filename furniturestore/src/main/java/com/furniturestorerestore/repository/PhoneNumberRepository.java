package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    Optional<PhoneNumber> findByPhoneNumber(String phoneNumber);
}
