package com.ecommerce.blossom.api.blossom_api.controllers;

import com.ecommerce.blossom.api.blossom_api.dtos.OrderHistoryDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.UserOrderCountDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.UserProfileDTO;
import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import com.ecommerce.blossom.api.blossom_api.services.OrderService;
import com.ecommerce.blossom.api.blossom_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfileByEmail(@RequestParam String email) {
        UserProfileDTO profile = userService.getUserProfile(email);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/top")
    public ResponseEntity<List<UserOrderCountDTO>> getUsersWithMostOrders() {
        List<UserOrderCountDTO> users = userService.getUsersWithMostOrders();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/order-history")
    public ResponseEntity<Page<OrderHistoryDTO>> getOrderHistory(
            @RequestParam String email,
            @RequestParam Optional<OrderStatus> status,
            Pageable pageable
    ) {
        Page<OrderHistoryDTO> history = orderService.getUserOrderHistory(email, status, pageable);
        return ResponseEntity.ok(history);
    }
}
