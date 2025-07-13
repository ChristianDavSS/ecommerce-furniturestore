package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
