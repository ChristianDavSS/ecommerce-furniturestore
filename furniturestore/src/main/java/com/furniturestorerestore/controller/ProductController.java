package com.furniturestorerestore.controller;

import com.furniturestorerestore.dto.ProductDto;
import com.furniturestorerestore.dto.request.ProductRequest;
import com.furniturestorerestore.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductRequest product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductRequest product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
