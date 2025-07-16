package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderRequest, Long> {
    List<OrderRequest> findByUserId(Long userId);
}
