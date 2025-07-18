package com.furniturestorerestore.service;

import com.furniturestorerestore.component.OrderMapper;
import com.furniturestorerestore.dto.OrderDto;
import com.furniturestorerestore.repository.OrderRepository;
import com.furniturestorerestore.repository.UserRepository;
import com.furniturestorerestore.repository.entity.MyUser;
import com.furniturestorerestore.repository.entity.OrderRequest;
import com.furniturestorerestore.repository.entity.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto saveOrder(Long userId) {
        // HERE THE FRONT WILL SEND THE BACKEND PRODUCTS
        MyUser user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));

        return orderMapper.toDto(
                orderRepository.save(
                        OrderRequest.builder()
                                .creationDate(LocalDateTime.now())
                                .updateDate(LocalDateTime.now())
                                .status(Status.PENDING)
                                .user(user)
                                .build()
                )
        );
    }

    public List<OrderDto> getAllOrders() {
        List<OrderRequest> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrderById(Long orderId) {
        return orderMapper.toDto(orderRepository.findById(orderId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        ));
    }

    public List<OrderDto> getOrdersByUserId(@PathVariable Long id) {
        List<OrderRequest> orders = orderRepository.findByUserId(id);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto updateOrder(Long orderId, Status status) {
        OrderRequest order = orderRepository.findById(orderId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        );
        order.setStatus(status);
        order.setUpdateDate(LocalDateTime.now());
        return orderMapper.toDto(orderRepository.save(order));

    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
