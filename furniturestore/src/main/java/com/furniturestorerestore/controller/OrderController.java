package com.furniturestorerestore.controller;

import com.furniturestorerestore.dto.OrderDto;
import com.furniturestorerestore.repository.entity.MyUser;
import com.furniturestorerestore.repository.entity.enums.Status;
import com.furniturestorerestore.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto createOrder(@AuthenticationPrincipal MyUser user) {
        return orderService.saveOrder(user.getId());
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user")
    public List<OrderDto> getOrdersByUserId(@AuthenticationPrincipal MyUser user) {
        return orderService.getOrdersByUserId(user.getId());
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestParam Status status) {
        return orderService.updateOrder(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }
}
