package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
