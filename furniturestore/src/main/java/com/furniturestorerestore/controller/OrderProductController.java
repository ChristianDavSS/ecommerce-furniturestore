package com.furniturestorerestore.controller;

import com.furniturestorerestore.dto.OrderProductDto;
import com.furniturestorerestore.service.OrderProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-product")
public class OrderProductController {
    private final OrderProductService orderProductService;
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @PostMapping
    public OrderProductDto saveOrderProduct(@RequestBody OrderProductDto orderProduct) {
        return orderProductService.saveOrderProduct(orderProduct);
    }

    @GetMapping
    public List<OrderProductDto> getAllOrderProduct() {
        return orderProductService.getAllOrderProduct();
    }
}
