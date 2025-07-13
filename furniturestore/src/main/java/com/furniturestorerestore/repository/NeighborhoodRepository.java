package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.Neighborhood;
import com.furniturestorerestore.repository.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
    public Neighborhood findByName(String name);
}
