package com.furniturestorerestore.component;

import com.furniturestorerestore.dto.CategoryDto;
import com.furniturestorerestore.repository.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
