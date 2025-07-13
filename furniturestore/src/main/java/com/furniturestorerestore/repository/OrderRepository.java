package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderRequest, Long> {
}
