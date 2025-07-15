package com.furniturestorerestore.service;

import com.furniturestorerestore.component.CategoryMapper;
import com.furniturestorerestore.dto.CategoryDto;
import com.furniturestorerestore.repository.CategoryRepository;
import com.furniturestorerestore.repository.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDto).toList();
    }

    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"))
        );
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
        );
        category.setName(categoryDto.getName());
        return categoryMapper.toDto(categoryRepository.save(category));
    }
}
