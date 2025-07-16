package com.furniturestorerestore.component;

import com.furniturestorerestore.dto.OrderDto;
import com.furniturestorerestore.repository.entity.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(OrderRequest order) {
        return OrderDto.builder()
                .id(order.getId())
                .creationDate(order.getCreationDate())
                .updateDate(order.getUpdateDate())
                .status(order.getStatus())
                .userId(order.getUser().getId())
                .build();
    }
}
