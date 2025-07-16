package com.furniturestorerestore.component;

import com.furniturestorerestore.dto.OrderProductDto;
import com.furniturestorerestore.repository.entity.OrderProduct;
import org.springframework.stereotype.Component;

@Component
public class OrderProductMapper {
    public OrderProductDto toDto(OrderProduct orderProduct) {
        return OrderProductDto.builder()
                .orderId(orderProduct.getOrderRequest().getId())
                .productId(orderProduct.getProduct().getId())
                .quantity(orderProduct.getQuantity())
                .build();
    }
}
