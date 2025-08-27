package com.furniturestorerestore.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProduct {
    @EmbeddedId
    private OrderProductPK orderProductId;

    @ManyToOne()
    @MapsId("orderId")
    private OrderRequest orderRequest;

    @ManyToOne()
    @MapsId("productId")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
