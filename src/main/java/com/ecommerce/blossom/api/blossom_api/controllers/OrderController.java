package com.ecommerce.blossom.api.blossom_api.controllers;

import com.ecommerce.blossom.api.blossom_api.dtos.CreateOrderRequestDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.OrderResponseDTO;
import com.ecommerce.blossom.api.blossom_api.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody CreateOrderRequestDTO request) {
        OrderResponseDTO createdOrder = orderService.createOrder(request);
        return ResponseEntity.ok(createdOrder);
    }
}
