package com.furniturestorerestore.repository;

import com.furniturestorerestore.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
