package com.furniturestorerestore.service;

import com.furniturestorerestore.component.ProductMapper;
import com.furniturestorerestore.dto.ProductDto;
import com.furniturestorerestore.dto.request.ProductRequest;
import com.furniturestorerestore.repository.CategoryRepository;
import com.furniturestorerestore.repository.ProductRepository;
import com.furniturestorerestore.repository.entity.Category;
import com.furniturestorerestore.repository.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository, ProductMapper productMapper,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    public ProductDto saveProduct(ProductRequest product) {
        Category category = categoryRepository.findByName(product.getCategory()).orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
                );
        if (productRepository.existsByName(product.getName())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This product already exists");
        };

        Product myProduct = Product.builder()
                .picture(product.getPicture())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(category)
                .build();

        return productMapper.toDto(productRepository.save(myProduct));
    }

    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(productMapper::toDto).toList();
    }

    public ProductDto findProductById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"))
        );
    }

    public ProductDto updateProduct(Long id, ProductRequest product) {
        Product myProduct = productRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        );
        Category category = categoryRepository.findByName(product.getCategory()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
        );
        myProduct.setPicture(product.getPicture());
        myProduct.setName(product.getName());
        myProduct.setDescription(product.getDescription());
        myProduct.setPrice(product.getPrice());
        myProduct.setStock(product.getStock());
        myProduct.setCategory(category);
        return productMapper.toDto(productRepository.save(myProduct));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
