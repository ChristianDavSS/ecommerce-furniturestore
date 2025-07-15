package com.furniturestorerestore.component;

import com.furniturestorerestore.dto.CategoryDto;
import com.furniturestorerestore.dto.ProductDto;
import com.furniturestorerestore.repository.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        /*
        * Method to convert from Product to ProductDto (better response)
        * */
        return ProductDto.builder()
                .id(product.getId())
                .picture(product.getPicture())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(
                        CategoryDto.builder()
                                .id(product.getCategory().getId())
                                .name(product.getCategory().getName())
                                .build()
                )
                .build();
    }
}
