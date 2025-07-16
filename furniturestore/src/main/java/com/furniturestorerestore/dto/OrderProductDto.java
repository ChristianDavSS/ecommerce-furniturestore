package com.furniturestorerestore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductDto {
    private Long orderId;
    private Long productId;
    private Integer quantity;
}
