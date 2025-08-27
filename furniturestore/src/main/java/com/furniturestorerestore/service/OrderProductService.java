package com.furniturestorerestore.service;

import com.furniturestorerestore.component.OrderProductMapper;
import com.furniturestorerestore.dto.OrderProductDto;
import com.furniturestorerestore.repository.*;
import com.furniturestorerestore.repository.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductMapper orderProductMapper;
    public OrderProductService(OrderProductRepository orderProductRepository, OrderRepository orderRepository,
                               ProductRepository productRepository, OrderProductMapper orderProductMapper) {
        this.orderProductRepository = orderProductRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductMapper = orderProductMapper;
    }

    public OrderProductDto saveOrderProduct(OrderProductDto orderProductDto) {
        OrderRequest order = orderRepository.findById(orderProductDto.getOrderId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")
        );
        Product product = productRepository.findById(orderProductDto.getProductId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        );

        if (orderProductDto.getQuantity() > product.getStock()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Too many products");
        }

        OrderProductPK id = OrderProductPK.builder()
                .orderId(orderProductDto.getOrderId())
                .productId(orderProductDto.getProductId())
                .build();

        OrderProduct orderProduct = OrderProduct.builder()
                .orderProductId(id)
                .orderRequest(order)
                .product(product)
                .quantity(orderProductDto.getQuantity())
                .build();

        return orderProductMapper.toDto(orderProductRepository.save(orderProduct));
    }

    public List<OrderProductDto> getAllOrderProduct() {
        List<OrderProduct> orderProducts = orderProductRepository.findAll();
        return orderProducts.stream().map(orderProductMapper::toDto).toList();
    }
}
