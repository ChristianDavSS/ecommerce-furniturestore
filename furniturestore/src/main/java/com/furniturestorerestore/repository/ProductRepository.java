package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
