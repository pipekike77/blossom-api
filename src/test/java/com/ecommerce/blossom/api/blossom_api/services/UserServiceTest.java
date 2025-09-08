package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.dtos.UserProfileDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Admin;
import com.ecommerce.blossom.api.blossom_api.entities.Order;
import com.ecommerce.blossom.api.blossom_api.entities.OrderProduct;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import com.ecommerce.blossom.api.blossom_api.enums.OrderStatus;
import com.ecommerce.blossom.api.blossom_api.enums.Role;
import com.ecommerce.blossom.api.blossom_api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUserProfile() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setName("Admin User");
        admin.setEmail("admin@test.com");
        admin.setRole(Role.ADMIN);
        admin.setDepartment("IT");

        Order order = new Order();
        order.setId(101L);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PAID);

        Product product = new Product();
        product.setId(55L);
        product.setName("Laptop");
        product.setPrice(1200.0);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(1);

        order.setOrderProducts(List.of(orderProduct));
        admin.setOrders(List.of(order));

        when(userRepository.findByEmail("admin@test.com")).thenReturn(Optional.of(admin));

        UserProfileDTO profile = userService.getUserProfile("admin@test.com");

        assertEquals("Admin User", profile.getName());
        assertEquals("admin@test.com", profile.getEmail());
        assertEquals("ADMIN", profile.getRole());
        assertEquals("IT", profile.getDepartment());
        assertEquals(1, profile.getOrders().size());
        assertEquals("Laptop", profile.getOrders().get(0).getOrderProducts().get(0).getProductName());

    }

    @Test
    void getUsersWithMostOrders() {
    }
}