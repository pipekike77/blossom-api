package com.ecommerce.blossom.api.blossom_api.controllers;

import com.ecommerce.blossom.api.blossom_api.dtos.CreateOrderRequestDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.OrderResponseDTO;
import com.ecommerce.blossom.api.blossom_api.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Orders", description = "Endpoints for managing orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Create a new order with a simulated asycronous payment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid order data")
    })
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody CreateOrderRequestDTO request) {
        OrderResponseDTO createdOrder = orderService.createOrder(request);
        return ResponseEntity.ok(createdOrder);
    }
}
