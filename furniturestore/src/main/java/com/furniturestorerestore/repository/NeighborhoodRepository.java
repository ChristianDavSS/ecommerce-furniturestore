package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.Neighborhood;
import com.furniturestorerestore.repository.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
    Optional<Neighborhood> findByName(String name);
}
