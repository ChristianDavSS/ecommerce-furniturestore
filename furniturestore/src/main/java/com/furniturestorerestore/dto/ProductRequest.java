package com.furniturestorerestore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private Long id;
    private String picture;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
}
