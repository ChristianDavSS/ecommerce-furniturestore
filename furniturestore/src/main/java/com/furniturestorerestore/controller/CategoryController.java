package com.furniturestorerestore.controller;

import com.furniturestorerestore.component.CategoryMapper;
import com.furniturestorerestore.dto.CategoryDto;
import com.furniturestorerestore.repository.entity.Category;
import com.furniturestorerestore.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @PutMapping
    public CategoryDto updateCategoryById(@RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }
}
