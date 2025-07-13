package com.furniturestorerestore.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderProduct {
    @EmbeddedId
    private OrderProductFK orderProductId;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("orderId")
    private OrderRequest orderRequest;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("productId")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
